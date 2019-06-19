package com.util;

import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtil {
    public static List importExcel(File file, Class clazz) {
        ImportParams importParams = new ImportParams();
        importParams.setHeadRows(1);

        return ExcelImportUtil.importExcel(file, clazz, importParams);
    }

    public static void exportExcel(List list, OutputStream outputStream, Class clazz) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), clazz, list);
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportExcel(List list, HttpServletResponse response, Class clazz, String fileName) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), clazz, list);

        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
