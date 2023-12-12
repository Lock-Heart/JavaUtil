package Excel;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zdj
 * @version 1.0
 * @date 2021/8/31 14:14
 */
public class PackExcelSheetsDataUtil {
    /*
     * 封装需要导出的数据
     * */
    public void packExcelSheetsData(HttpServletResponse response, String excelName, List<ExcelDataVo> excelDataVoList) throws Exception{
        try {
            // 对excel进行判断
            for(int j = 0; j < excelDataVoList.size(); j++){
                String[] headerName = excelDataVoList.get(j).getSheetTopColHeaderName();
                String[] headerAttribute = excelDataVoList.get(j).getSheetTopColHeaderAttribute();
                if(headerName.length != headerAttribute.length){
                    throw new Exception("列头长度与属性长度不对应！");
                }
            }

            // 创建一个excel对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            OutputStream out = response.getOutputStream();

            //定义标题以及设置响应头信息
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(excelName+".xls", "UTF-8"));

            // 循环生成指定数量的sheet表
            for(int j = 0; j < excelDataVoList.size(); j++){
                // 获取单个sheet的数据
                String sheetName = excelDataVoList.get(j).getSheetName();
                String sheetTopColHeaderTitle = excelDataVoList.get(j).getSheetTopColHeaderTitle();
                String[] headers = excelDataVoList.get(j).getSheetTopColHeaderName();
                String[] headerCol = excelDataVoList.get(j).getSheetTopColHeaderAttribute();
                List<Map<String, Object>> sheetDataList = excelDataVoList.get(j).getSheetDataList();

                // 申请一个最后的list集合并封装数据
                List<Object[]> dataList = new ArrayList<Object[]>();
                Object[] objs = null;
                for(int i = 0; i < sheetDataList.size(); i++){
                    // 封装lie数据
                    objs = new Object[headers.length];
                    for (int k = 0; k < headers.length; k++) {
                        objs[k] = sheetDataList.get(i).get(headerCol[k]);
                    }

                    //数据添加到excel表格
                    dataList.add(objs);
                }

                //使用流将数据导出
                new ExcelSheetsUtil(sheetName, sheetTopColHeaderTitle, headers, dataList).export(workbook);
            }

            // 最后将整个excel全部写到浏览器
            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
