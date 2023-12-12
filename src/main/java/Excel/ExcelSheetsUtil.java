package Excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import java.util.List;

/**
 * @author zdj
 * @version 1.0
 * @date 2021/8/31 14:14
 */
public class ExcelSheetsUtil {

    //导出表的列名
    private String[] rowName;

    //导出表的头部标题
    private String oneheaders;

    //sheet表表名
    private String sheettitle;

    // 需要导出的数据集合
    private List<Object[]> dataList;

    /**
     * @param sheettitle 单个sheet名称
     * @param oneheaders 单个sheet头部名称
     * @param rowName  单个sheet对应的列头
     * @param dataList  单个sheet对应的数据
     */
    public ExcelSheetsUtil(String sheettitle, String oneheaders, String[] rowName, List<Object[]> dataList){
        this.dataList = dataList;
        this.oneheaders = oneheaders;
        this.rowName = rowName;
        this.sheettitle = sheettitle;
    }

    /*
     * 导出数据
     * */
    public void export(HSSFWorkbook workbook) throws Exception{
        try{
            // 创建工作表
            HSSFSheet sheet = workbook.createSheet(sheettitle);

            //获取列头样式对象
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);

            //单元格样式对象
            HSSFCellStyle style = this.getStyle(workbook);

            //第一行
            HSSFRow rowfirstName = sheet.createRow(0);
            //创建列头对应个数的单元格
            HSSFCell oneCellRowName = rowfirstName.createCell(0);
            //设置列头单元格的数据类型
            oneCellRowName.setCellType(CellType.STRING);
            HSSFRichTextString onetext = new HSSFRichTextString(oneheaders);
            //设置列头单元格的值
            oneCellRowName.setCellValue(onetext);
            //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
            sheet.addMergedRegion(new CellRangeAddress(0,0,0, rowName.length - 1));
            //设置列头单元格样式
            oneCellRowName.setCellStyle(columnTopStyle);


            // 定义所需列数
            int columnNum = rowName.length;

            /***   绑定列头   ****/
            // 在索引2的位置创建行(最顶端的行开始的第二行)
            HSSFRow rowRowName = sheet.createRow(1);
            //单元格样式对象
            HSSFCellStyle styleHearer = this.getStyle(workbook);
            Font headerFont = workbook.createFont();
            headerFont.setFontName("微软雅黑");
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLACK.getIndex());
            styleHearer.setFont(headerFont);
            for(int n = 0; n < columnNum; n++){
                //创建列头对应个数的单元格
                HSSFCell  cellRowName = rowRowName.createCell(n);
                //设置列头单元格的数据类型
                cellRowName.setCellType(CellType.STRING);
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                //设置列头单元格的值
                cellRowName.setCellValue(text);
                //设置列头单元格样式
                cellRowName.setCellStyle(styleHearer);
            }


            /***   绑定数据   ****/
            //将查询出的数据设置到sheet对应的单元格中
            for(int i=0;i<dataList.size();i++){
                Object[] obj = dataList.get(i);
                //创建所需的行数（从第二行开始写数据）
                HSSFRow row = sheet.createRow(i+2);
                for(int j = 0; j < obj.length; j++){
                    //设置单元格的数据类型
                    HSSFCell  cell = row.createCell(j, CellType.STRING);;
                    if(!"".equals(obj[j]) && obj[j] != null){
                        //设置单元格的值
                        cell.setCellValue(obj[j].toString());
                    }
                    //设置单元格样式
                    cell.setCellStyle(style);
                }
            }

            /***   让列宽随着导出的列长自动适应   ****/
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == CellType.STRING) {
                            int length = 0;
                            try {
                                length = currentCell.getStringCellValue().getBytes().length;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if(colNum == 0){
                    sheet.setColumnWidth(colNum, (columnWidth-2) * 256);
                }else{
                    sheet.setColumnWidth(colNum, (columnWidth+4) * 256);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * 列头单元格样式
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("宋体");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    /*
     * 列数据信息单元格样式
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体名字
        font.setFontName("宋体");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        //设置左边框;
        style.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        //设置右边框;
        style.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        //设置顶边框;
        style.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
}
