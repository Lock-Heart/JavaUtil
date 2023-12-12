package Excel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 测试多个导出sheet
 */
//@RestController
//@RequestMapping("/mps/mmg/msgin")
//@Api("收报查询")
public class exportTeenagerMoreSheet {
//    @ApiOperation(value = "测试多个导出sheet", notes = "测试多个导出sheet", httpMethod = "GET")
//    @RequestMapping(value = "/exportTeenagerMoreSheet", method = RequestMethod.GET)
public static void main(String[] args) throws Exception {
    // 定义学生信息
    List<Map<String, Object>> list1 = new ArrayList();
    for (int i = 0; i < 3; i++) {
        Map<String, Object> map1 = new HashMap();
        map1.put("name", "张三"+i);
        map1.put("sex", "男"+i);
        map1.put("age", "23"+i);
        map1.put("isSchool", "是"+i);
        list1.add(map1);
    }

    // 定义学校信息
    List<Map<String, Object>> list2 = new ArrayList();
    for (int i = 0; i < 4; i++) {
        Map<String, Object> map2 = new HashMap();
        map2.put("name", "杭州天长小学"+i);
        map2.put("adress", "浙江省杭州市"+i);
        map2.put("concat", "王五"+i);
        list2.add(map2);
    }

    // 定义其他信息
    List<Map<String, Object>> list3 = new ArrayList();
    for (int i = 0; i < 10; i++) {
        Map<String, Object> map3 = new HashMap();
        map3.put("protity", "其他1"+i);
        map3.put("remark", "我是备注"+i);
        list3.add(map3);
    }


    // 封装需要导出的数据
    List<ExcelDataVo> excelDataVoList = new ArrayList();

    // 封装学生信息
    ExcelDataVo excelDataSheetOne = new ExcelDataVo();
    excelDataSheetOne.setSheetName("学生信息");
    excelDataSheetOne.setSheetTopColHeaderTitle("学生信息");
    excelDataSheetOne.setSheetTopColHeaderName(new String[] {"姓名","性别","年龄", "学生"});
    excelDataSheetOne.setSheetTopColHeaderAttribute(new String[] {"name","sex","age", "isSchool"});
    excelDataSheetOne.setSheetDataList(list1);
    excelDataVoList.add(excelDataSheetOne);

    // 封装学校信息
    ExcelDataVo excelDataSheetTwo = new ExcelDataVo();
    excelDataSheetTwo.setSheetName("学校信息");
    excelDataSheetTwo.setSheetTopColHeaderTitle("学校信息");
    excelDataSheetTwo.setSheetTopColHeaderName(new String[] {"学校","地址","联系人"});
    excelDataSheetTwo.setSheetTopColHeaderAttribute(new String[] {"name","adress","concat"});
    excelDataSheetTwo.setSheetDataList(list2);
    excelDataVoList.add(excelDataSheetTwo);

    // 封装其他信息
    ExcelDataVo excelDataSheetThree = new ExcelDataVo();
    excelDataSheetThree.setSheetName("其他信息");
    excelDataSheetThree.setSheetTopColHeaderTitle("其他信息");
    excelDataSheetThree.setSheetTopColHeaderName(new String[] {"描述","备注"});
    excelDataSheetThree.setSheetTopColHeaderAttribute(new String[] {"protity","remark"});
    excelDataSheetThree.setSheetDataList(list3);
    excelDataVoList.add(excelDataSheetThree);

    // 导出数据
    try {
        new PackExcelSheetsDataUtil().packExcelSheetsData(null, "学生基础信息", excelDataVoList);
    } catch (Exception e) {
        throw new Exception("导出异常!");
    }
}


    public void exportTeenagerMoreSheet(HttpServletResponse response) throws Exception {
        // 定义学生信息
        List<Map<String, Object>> list1 = new ArrayList();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map1 = new HashMap();
            map1.put("name", "张三"+i);
            map1.put("sex", "男"+i);
            map1.put("age", "23"+i);
            map1.put("isSchool", "是"+i);
            list1.add(map1);
        }

        // 定义学校信息
        List<Map<String, Object>> list2 = new ArrayList();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> map2 = new HashMap();
            map2.put("name", "杭州天长小学"+i);
            map2.put("adress", "浙江省杭州市"+i);
            map2.put("concat", "王五"+i);
            list2.add(map2);
        }

        // 定义其他信息
        List<Map<String, Object>> list3 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map3 = new HashMap();
            map3.put("protity", "其他1"+i);
            map3.put("remark", "我是备注"+i);
            list3.add(map3);
        }


        // 封装需要导出的数据
        List<ExcelDataVo> excelDataVoList = new ArrayList();

        // 封装学生信息
        ExcelDataVo excelDataSheetOne = new ExcelDataVo();
        excelDataSheetOne.setSheetName("学生信息");
        excelDataSheetOne.setSheetTopColHeaderTitle("学生信息");
        excelDataSheetOne.setSheetTopColHeaderName(new String[] {"姓名","性别","年龄", "学生"});
        excelDataSheetOne.setSheetTopColHeaderAttribute(new String[] {"name","sex","age", "isSchool"});
        excelDataSheetOne.setSheetDataList(list1);
        excelDataVoList.add(excelDataSheetOne);

        // 封装学校信息
        ExcelDataVo excelDataSheetTwo = new ExcelDataVo();
        excelDataSheetTwo.setSheetName("学校信息");
        excelDataSheetTwo.setSheetTopColHeaderTitle("学校信息");
        excelDataSheetTwo.setSheetTopColHeaderName(new String[] {"学校","地址","联系人"});
        excelDataSheetTwo.setSheetTopColHeaderAttribute(new String[] {"name","adress","concat"});
        excelDataSheetTwo.setSheetDataList(list2);
        excelDataVoList.add(excelDataSheetTwo);

        // 封装其他信息
        ExcelDataVo excelDataSheetThree = new ExcelDataVo();
        excelDataSheetThree.setSheetName("其他信息");
        excelDataSheetThree.setSheetTopColHeaderTitle("其他信息");
        excelDataSheetThree.setSheetTopColHeaderName(new String[] {"描述","备注"});
        excelDataSheetThree.setSheetTopColHeaderAttribute(new String[] {"protity","remark"});
        excelDataSheetThree.setSheetDataList(list3);
        excelDataVoList.add(excelDataSheetThree);

        // 导出数据
        try {
            new PackExcelSheetsDataUtil().packExcelSheetsData(response, "学生基础信息", excelDataVoList);
        } catch (Exception e) {
            throw new Exception("导出异常!");
        }
    }

}
