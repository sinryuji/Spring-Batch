package com.example.springbatch.controller;

import com.example.springbatch.service.ExcelService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExcelController {

    private final ExcelService excelService;

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> getUserWageExcel() {
        ByteArrayInputStream result;

        try {
            result = this.excelService.getUserWageExcel();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/vnd.ms-excel");
        headers.add("Content-Disposition", "attachment; filename=userWage.xlsx");

        return ResponseEntity
            .ok()
            .headers(headers)
            .body(new InputStreamResource(result));
    }
}
