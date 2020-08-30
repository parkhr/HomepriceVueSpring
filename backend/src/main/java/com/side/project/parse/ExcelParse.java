package com.side.project.parse;

import com.side.project.model.Bubjungdong;
import com.side.project.repository.BubjungdongRepository;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelParse {

    // 법정동 엑셀 파일 파싱하여 database insert
    public void bubjungdong(BubjungdongRepository bubjungdongRepository) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/java/com/side/project/parse/bubjungdong.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0); // 해당 엑셀파일의 시트(Sheet) 수

        int rows = sheet.getPhysicalNumberOfRows(); // 해당 시트의 행의 개수
        for (int rowIndex = 20557; rowIndex < rows; rowIndex++) {
            XSSFRow row = sheet.getRow(rowIndex); // 각 행을 읽어온다
            if (row != null) {
                Bubjungdong bubjungdong = new Bubjungdong();
                for (int columnIndex = 0; columnIndex < 5; columnIndex++) {
                    System.out.println(columnIndex);
                    XSSFCell cell = row.getCell(columnIndex); // 셀에 담겨있는 값을 읽는다.
                    if(cell == null) break;
                    if(columnIndex == 0){
                        long code = Long.valueOf(cell.getStringCellValue());
                        bubjungdong.setCode(code);
                    }
                    else if(columnIndex == 1 && !StringUtils.isEmpty(cell.getStringCellValue())) bubjungdong.setA1(cell.getStringCellValue());
                    else if(columnIndex == 2 && !StringUtils.isEmpty(cell.getStringCellValue())) bubjungdong.setA2(cell.getStringCellValue());
                    else if(columnIndex == 3 && !StringUtils.isEmpty(cell.getStringCellValue())) bubjungdong.setA3(cell.getStringCellValue());
                    else if(columnIndex == 4 && !StringUtils.isEmpty(cell.getStringCellValue())) bubjungdong.setA4(cell.getStringCellValue());
                }

                System.out.println(bubjungdong.toString());
                bubjungdongRepository.save(bubjungdong);
            }
        }
    }
}
