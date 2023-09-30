package com.example.springbatch.service;

import com.example.springbatch.entity.UserWage;
import com.example.springbatch.repository.UserWageRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.plaf.ListUI;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExcelService {

    private final UserWageRepository userWageRepository;

    @Transactional
    public ByteArrayInputStream getUserWageExcel() throws IOException {

        List<UserWage> userWageList = this.userWageRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Sheet sheet = workbook.createSheet("UserWage");
        createUserWageSheet(userWageList, sheet, headerCellStyle);

        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    private void createUserWageSheet(List<UserWage> list, Sheet sheet, CellStyle headerCellStyle) {
       Row headerRow = sheet.createRow(0);
       String[] headers = {"UserName", "Wage"};

       for (int i = 0; i < headers.length; i++) {
           Cell headerCell = headerRow.createCell(i);
           headerCell.setCellValue(headers[i]);
           headerCell.setCellStyle(headerCellStyle);
       }

       int idx = 1;
       for (UserWage userWage: list) {
           Row bodyRow = sheet.createRow(idx++);
           Cell bodyCell = bodyRow.createCell(0);
           bodyCell.setCellValue(userWage.getUser().getName());
           bodyCell = bodyRow.createCell(1);
           bodyCell.setCellValue(userWage.getWage());
       }

       for (int i = 0; i < headers.length; i++) {
           sheet.autoSizeColumn(i);
           sheet.setColumnWidth(i, (sheet.getColumnWidth(i) + 1024));
       }
    }

}
