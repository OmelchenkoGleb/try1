package diplom.try1.DAO;

import diplom.try1.Model.all_data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

@Component
public class ExelParser {

    public ArrayList<all_data> parser(MultipartFile multipartFile, int sem11, int sem12, int sem21, int sem22){
        InputStream inputStream = null;
        HSSFWorkbook workbook = null;
        Class<all_data> all_dataClass = all_data.class;
        ArrayList<all_data> datalist = new ArrayList<>();
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            if(convFile.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(convFile);
                fos.write(multipartFile.getBytes());
                fos.close();
                try {
                    inputStream = new FileInputStream(convFile);
                    workbook = new HSSFWorkbook(inputStream);
                    Sheet sheet = workbook.getSheetAt(0);
                    Iterator<Row> it = sheet.iterator();
                    for (int i=0; i<sem11-1; i++){
                        it.next();
                    }
                    int count = 0;
                    while (it.hasNext()) {
                        if (count == (sem12-sem11+1)){ // эти ифы для того чтобы вписать какой это семестр исходя из границ
                            System.out.println("Начался 2 семестр");
                            for (int n=0; n<sem21-sem12; n++){
                                it.next();
                            }
                            count=sem21;
                        } else if (count == sem22) break;
                        Row row = it.next();
                        Iterator<Cell> cells = row.iterator();
                        cells.next();
                        int i = 1; // от какого поля двигаться
                        all_data obj = new all_data(); // объект в котором будут храниться данные
                        if(count<=sem12) obj.setSemestr(1); // вписываем семестр
                        if (count>=sem21) obj.setSemestr(2);
                        Field[] declaredFields = all_dataClass.getDeclaredFields(); //  помощью рефлексии получаем поля класса
                        while (cells.hasNext()) {
                            if (i>55) break; // важный счёткий до  какого поля нам двигаться в таблице
                            Cell cell = cells.next();
                            declaredFields[i+1].setAccessible(true);
                            try {
                                declaredFields[i+1].setDouble(obj,cell.getNumericCellValue());
                            } catch (Exception e){
                                if ((i+1) == 2) declaredFields[i+1].set(obj,cell.getStringCellValue()); // вписываем имя отдельно так как остальные налл а мы даём значение 0
                                else declaredFields[i+1].setDouble(obj,0);
                            }
                            i++;
                        }
                        System.out.println();
                        count++;
                        datalist.add(obj);
//                        break; // 1 запись тестим
                    }

                } catch (IOException | IllegalAccessException e) {
                    System.out.println("try2");
                    e.printStackTrace();
                }
                if(convFile.delete()){
                    System.out.println("Файл был удален с корневой папки проекта");
                    return datalist;
                }else {
                    System.out.println("Файл не был найден в корневой папке проекта");
                    return null;
                }
            } else {
                System.out.println("Файл существует");
                return null;
            }
        } catch (IOException e) {
            System.out.println("try1");
            convFile = null;
        }

        return null;
    }

}
