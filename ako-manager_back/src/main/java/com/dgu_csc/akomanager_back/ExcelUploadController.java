package com.dgu_csc.akomanager_back;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelUploadController {
    private final List<String[][]> excels = new ArrayList<String[][]>();

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded.");
        }
        try {
            String[][] data = readExcel(file);
            excels.add(data);
            return ResponseEntity.ok("File processed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing file.");
        }
    }

    public String[][] readExcel(MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();
        String[][] data = new String[rows][cols];

        for (int r = 0; r < rows; r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c < cols; c++) {
                Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                data[r][c] = (cell == null) ? "" : cell.toString();
            }
        }
        workbook.close();
        return data;
    }
}
