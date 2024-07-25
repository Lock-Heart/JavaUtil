package Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

// 从同一个Excel文档中读取多个value值
public class MultiValueMap {

    public static void main(String[] args) throws IOException {

            // 创建一个Map对象
            Map<String, List<String>> dataMap = new HashMap();
            dataMap.keySet();
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
                String value1 = valueCell.getStringCellValue();

                Cell valueCell2 = row.getCell(2);
                String value2 = valueCell2.getStringCellValue();
                // 将键值对存储到Map中
                // 添加多个值到同一个键
                dataMap.put(key, new ArrayList<>());
                dataMap.get(key).add(value1);
                dataMap.get(key).add(value2);
            }
            // 输出Map中的内容
            //System.out.println(dataMap);

            //遍历key值
            Set<String> keys = dataMap.keySet();
            int count = 0;
            for (String key : keys) {
                System.out.println(key);

                List value = dataMap.get(key);

                String u= (String) value.get(0);
                String i= (String) value.get(1);
                System.out.println(u+","+i);
            }

            System.out.println("--已更新[" + count + "]条数据。");
            // 关闭Excel文件
            file.close();

    }

}
