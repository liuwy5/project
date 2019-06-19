package com.util;

import com.entity.PersonEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilTest {
    public static void main(String[] args) {
//        importExcelTest();
        exportExcelTest();
    }

    private static void importExcelTest() {
        File file = new File("file/person1.xlsx");
        List list = ExcelUtil.importExcel(file, PersonEntity.class);
        System.out.println(list);
    }

    private static void exportExcelTest() {
        List<PersonEntity> list = new ArrayList<>(4);
        list.add(new PersonEntity("1", "a", "001", "a dep", "131", "女"));
        list.add(new PersonEntity("2", "b", "002", "a dep", "1312", "女"));
        list.add(new PersonEntity("3", "c", "003", "as dep", "131", "男"));
        list.add(new PersonEntity("4", "d", "004", "a dep", "1315", "女"));

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("file/person_target.xlsx");
            ExcelUtil.exportExcel(list, fileOutputStream, PersonEntity.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
