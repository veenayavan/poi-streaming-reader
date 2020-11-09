package com.app.rest;

import com.app.exception.InternalServerException;
import com.app.properties.ExcelTemplateProperties;
import com.app.rest.response.ResponseMessage;
import com.app.service.ExcelPropertiesService;
import com.app.service.IExcelParsingService;
import com.app.util.ExcelTemplateUtil;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api")
public class ExcelParserController {

    @Autowired
    private IExcelParsingService excelParsingService;

    @Autowired
    private ExcelPropertiesService excelPropertiesService;

    private static final String template = "template." + XSSFWorkbookType.XLSX.getExtension();


    @PostMapping(value = "/do-upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> upload(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "templateId") Long templateId, HttpServletResponse response) {
        StopWatch sw = new StopWatch();
        sw.start();
        response.addHeader("fileName", file.getOriginalFilename());
        excelParsingService.parse(file, templateId);
        sw.stop();
        System.out.println("time taken - " + sw.getTime(TimeUnit.SECONDS));
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK, "Success"));
    }

    @GetMapping(value = "/templates/{templateId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> getTemplate(@RequestParam(name = "templateId") Long templateId) {
        ByteArrayResource resource;
        ExcelTemplateProperties templateProperties = excelPropertiesService.getTemplateProperties(templateId);
        XSSFWorkbook workbook = ExcelTemplateUtil.generateWorkBook(templateProperties);
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            resource = new ByteArrayResource(bos.toByteArray());
        } catch (IOException e) {
            throw new InternalServerException("error generating template");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%d_%s", templateId, template));
        return ResponseEntity.ok().headers(httpHeaders).body(resource);
    }

}
