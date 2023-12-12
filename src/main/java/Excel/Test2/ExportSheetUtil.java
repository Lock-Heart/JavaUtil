package Excel.Test2;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @description: excel导出多个sheet工具类
 * @author: ***
 * @date: 2022/9/15
 */
public class ExportSheetUtil {

    /**
     * 拆解并导出多重Excel
     */
    public static void exportManySheetExcel(String fileName, List<ExcelSheet> mysheets, HttpServletResponse response) {
        //创建工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        //表头样式
        HSSFCellStyle style = wb.createCellStyle();
        // 垂直
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 水平
        style.setAlignment(HorizontalAlignment.CENTER);
        //字体样式
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontName("微软雅黑");
        fontStyle.setFontHeightInPoints((short) 12);
        style.setFont(fontStyle);
        for (ExcelSheet excel : mysheets) {
            //新建一个sheet
            //获取该sheet名称
            HSSFSheet sheet = wb.createSheet(excel.getFileName());
            //获取sheet的标题名
            String[] handers = excel.getHanders();
            //第一个sheet的第一行为标题
            HSSFRow rowFirst = sheet.createRow(0);
            //写标题
            for (int i = 0; i < handers.length; i++) {
                //获取第一行的每个单元格
                HSSFCell cell = rowFirst.createCell(i);
                //往单元格里写数据
                cell.setCellValue(handers[i]);
                //加样式
                cell.setCellStyle(style);
                //设置每列的列宽
                sheet.setColumnWidth(i, 4000);
            }
            //写数据集
            List<String[]> dataset = excel.getDataset();
            for (int i = 0; i < dataset.size(); i++) {
                //获取该对象
                String[] data = dataset.get(i);
                //创建数据行
                HSSFRow row = sheet.createRow(i + 1);
                for (int j = 0; j < data.length; j++) {
                    //设置对应单元格的值
                    row.createCell(j).setCellValue(data[j]);
                }
            }
        }

        // 下载文件谷歌文件名会乱码,用IE
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
            response.setHeader("Cache-Control", "No-cache");
            response.flushBuffer();
            wb.write(response.getOutputStream());
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
