package diplom.try1.DAO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

@Component
public class ExelParser {

    public void parser(MultipartFile multipartFile){
        InputStream inputStream = null;
        HSSFWorkbook workbook = null;
        System.out.println(multipartFile.getOriginalFilename());

        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            if(convFile.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(convFile);
                fos.write(multipartFile.getBytes());
                fos.close(); //IOUtils.closeQuietly(fos);
                try {
                    inputStream = new FileInputStream(convFile);

                    workbook = new HSSFWorkbook(inputStream);
                    Sheet sheet = workbook.getSheetAt(0);
                    Iterator<Row> it = sheet.iterator();
                    for (int i=0; i<15; i++){
                        it.next();
                    }
                    while (it.hasNext()) {
                        Row row = it.next();
                        Iterator<Cell> cells = row.iterator();
                        int i = 0;
                        while (cells.hasNext()) {
                            if (i>55) break;
                            Cell cell = cells.next();
                            try {
                                System.out.println(cell.getNumericCellValue());
                            } catch (Exception e){
                                System.out.println(cell.getStringCellValue());
                            }
//                            switch (i){
//                                case 1:
//                                    System.out.println(cell.getStringCellValue());
//                                    break;
//                                default:
//                                    break;
//                            }
                            i++;
                        }
                        break; // 1 запись тестим
                    }
                } catch (IOException e) {
                    System.out.println("try2");
                    e.printStackTrace();
                }
            } else System.out.println("Файл существует");
        } catch (IOException e) {
            System.out.println("try1");
            convFile = null;
        }
    }
}
