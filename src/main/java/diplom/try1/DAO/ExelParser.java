package diplom.try1.DAO;

import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public ArrayList<Teachers> parsertecher(MultipartFile multipartFile){
        InputStream inputStream = null;
        HSSFWorkbook workbook = null;

        Class<Teachers> TeachersClass = Teachers.class;
        ArrayList<Teachers> datalist = new ArrayList<>();
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
                    it.next();
                    while (it.hasNext()) {
                        Row row = it.next();
                        Iterator<Cell> cells = row.iterator();
                        int i = 0;
                        Teachers obj = new Teachers();
                        Field[] declaredFields = TeachersClass.getDeclaredFields();
                        while (cells.hasNext()) {
                            Cell cell = cells.next();
                            declaredFields[i+1].setAccessible(true);
                            if (i == 3) {
                                declaredFields[i+1].set(obj,cell.getNumericCellValue());
                            } else {
                                declaredFields[i+1].set(obj,cell.getStringCellValue());
                            }
                            i++;
                        }
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

    public void download(Teachers teacher, List<all_data> dataList1, List<all_data> dataList2) throws IOException {
        InputStream inputStream = null;
        HSSFWorkbook workbook = null;
        Class<all_data> all_dataClass = all_data.class;
        Field[] declaredFields = all_dataClass.getDeclaredFields(); //  помощью рефлексии получаем поля класса


        File from = new File("SHABLON.xls");
        File to = new File(teacher.getName()+".xls");

        try {
            Files.copy(from.toPath(), to.toPath());
        } catch (Exception e){
            if (to.delete()){
                Files.copy(from.toPath(), to.toPath());
            }
        }

        int sem11 = 8;
        int sem21 = 115;
        int count = 0;


        try {
            inputStream = new FileInputStream(to);
            workbook = new HSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> it = sheet.iterator();
            all_data allData;


            for (int i=0; i<sem11; i++){
                it.next();
                count++;
            }





            int schetchik1 = 0;
            int schetchik2 = 0;
            int semestr = 1;
            while (it.hasNext()) {
                if (schetchik2 == dataList2.size()) break;
                if (schetchik1 == dataList1.size()) {
                    semestr = 2;
                    System.out.println("Начался 2 семестр");
                    for (int n=0; n<sem21-count; n++){
                        it.next();
                    }
                    count=sem21;
                    schetchik1=0;
                }



                if (semestr == 1){
                    allData = dataList1.get(schetchik1);
                    schetchik1++;
                } else {
                    allData = dataList2.get(schetchik2);
                    schetchik2++;
                }


                System.out.println(count);


                Row row = it.next();
                Iterator<Cell> cells = row.iterator();
                cells.next();
                int i = 1; // от какого поля двигаться
                while (cells.hasNext()) {
                    if (i>55) break; // важный счёткий до  какого поля нам двигаться в таблице
                    Cell cell = cells.next();
                    declaredFields[i+1].setAccessible(true);
                        if ((i+1) == 2) {
                            String s = (String) declaredFields[i+1].get(allData);
                            cell.setCellValue(new HSSFRichTextString(s));
                            System.out.print((String) declaredFields[i+1].get(allData)+" |");
                        } else {
                            cell.setCellValue(declaredFields[i+1].getDouble(allData));
                            System.out.print(declaredFields[i+1].getDouble(allData)+" |");
                        }

                    i++;
                }
                count++;
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("try2");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
