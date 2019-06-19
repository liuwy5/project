package com.controller;

import com.entity.PersonEntity;
import com.util.ExcelUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response) {
        List<PersonEntity> list = new ArrayList<>(4);
        list.add(new PersonEntity("1", "a", "001", "a dep", "131", "女"));
        list.add(new PersonEntity("2", "b", "002", "a dep", "1312", "女"));
        list.add(new PersonEntity("3", "c", "003", "as dep", "131", "男"));
        list.add(new PersonEntity("4", "d", "004", "a dep", "1315", "女"));

        String fileName = "person_target.xlsx";
        ExcelUtil.exportExcel(list, response, PersonEntity.class, fileName);
    }
}
