import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

 /**
   * @Description 读取Excel文件到map
   *
   * @Author guhaohua
   * @Date 2023/5/4
   **/

public class ExcelReader {
    public static void main(String[] args) {
        try {
            // 创建一个Map对象
            Map<String, String> dataMap = new HashMap<String, String>();
            // 打开Excel文件并获取工作簿对象
            FileInputStream file = new FileInputStream(new File("D:\\Company\\MX.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 遍历每一行
            for (Row row : sheet) {
                // 获取第一列中的数据作为Map键
                Cell keyCell = row.getCell(0);
                String key = keyCell.getStringCellValue();
                // 获取第二列中的数据作为Map值
                Cell valueCell = row.getCell(1);
                String value = valueCell.getStringCellValue();
                // 将键值对存储到Map中
                dataMap.put(key, value);
            }
            // 输出Map中的内容
            System.out.println(dataMap);

            //遍历key值
            Set<String> keys = dataMap.keySet();
            for (String key : keys) {
                System.out.println(key);

                String value = dataMap.get("key");

                String sqlUpdate = "update nmx_tpl_elem_test set sex = " + value + " where age = "+key;


            }
            // 关闭Excel文件
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void ExcelReader (){

        Connection conn = this.getConnection();
        PreparedStatement pstmt = null,pstmtUpdate = null;;
        ResultSet resultSet = null;
        try {
            // 创建一个Map对象
            Map<String, String> dataMap = new HashMap<String, String>();
            // 打开Excel文件并获取工作簿对象
            FileInputStream file = new FileInputStream(new File("D:\\Company\\NMX.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 遍历每一行
            for (Row row : sheet) {
                // 获取第一列中的数据作为Map键
                Cell keyCell = row.getCell(0);
                String key = keyCell.getStringCellValue();
                // 获取第二列中的数据作为Map值
                Cell valueCell = row.getCell(1);
                String value = valueCell.getStringCellValue();
                // 将键值对存储到Map中
                dataMap.put(key, value);
            }
            // 输出Map中的内容
            //System.out.println(dataMap);

            //遍历key值
            Set<String> keys = dataMap.keySet();
            int count = 0;
            for (String key : keys) {
                //System.out.println(key);

                String value = dataMap.get(key);

//				String sql = "SELECT DISTINCT ELEM_NAME  FROM nmx_tpl_elem_test WHERE TPL_ID LIKE 'PACS008%'";
////			sql = "select * from (" + sql + ") where rownum <=10";
//				pstmt = conn.prepareStatement(sql);
//				resultSet = pstmt.executeQuery();

//				String sqlUpdate = "update nmx_tpl_elem_test set FULL_NAME_CN=? where FULL_NAME_EN=? and TPL_ID like 'PACS008%'";
//				pstmt = conn.prepareStatement(sqlUpdate);
//				pstmt.executeUpdate();

                conn.setAutoCommit(false);
//				String sql = "SELECT DISTINCT FULL_NAME_CN,FULL_NAME_EN FROM nmx_tpl_elem_test WHERE TPL_ID LIKE 'PACS008%'";
//				pstmt = conn.prepareStatement(sql);
//				resultSet = pstmt.executeQuery();

                String sqlUpdate = "update nmx_tpl_elem set FULL_NAME_CN=? where FULL_NAME_EN=? and TPL_ID like 'PACS008%'";

                //String sqlUpdate = "update nmx_tpl_app_head_elem set FULL_NAME_CN=? where FULL_NAME_EN=?";
                pstmtUpdate = conn.prepareStatement(sqlUpdate);

                pstmtUpdate.setString(1, value);
                pstmtUpdate.setString(2, key);
                pstmtUpdate.addBatch();
                count++;
                pstmtUpdate.executeBatch();
                conn.commit();

//				while (resultSet.next()) {
//					String ELEM_NAME = resultSet.getString("ELEM_NAME");
//					System.out.println(ELEM_NAME);
//				}
            }

            System.out.println("--已更新[" + count + "]条数据。");
            // 关闭Excel文件
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //数据库链接
    private Connection getConnection() {
        return null;
    }

}