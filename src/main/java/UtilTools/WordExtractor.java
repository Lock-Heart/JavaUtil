package UtilTools;

import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordExtractor {
    private static final Logger log = LoggerFactory.getLogger(WordExtractor.class);
    public static void main(String[] args) {

        String data = "Status                                Active;Statuse                                Activee;";
        String[] parts = data.split(";");
        String[] a=parts[1].split(" ");
        String b = a[0];
        int i = a.length;
        String c = a[a.length-1];
        System.out.println(Arrays.toString(a));
        System.out.println(b);
        System.out.println(c);

//        String input = "Hello, how are you?";
//        Pattern p = Pattern.compile("\\w+");
//        Matcher m = p.matcher(input);
//        String[] str = input.split("\\w+");
//        for(int i=0;i < str.length; i++){
//            System.out.println(str[i]);
//        }
//        String ee = str[0];
//        System.out.println(ee);

//        while (m.find()) {
//            String word = m.group();
//            System.out.println(word);
//        }
    }


    /**
     * 文件分割
     *
     * @param path
     * @throws Exception
     */
    public void importBICData(String path) throws Exception {
        BufferedInputStream in = null;
        LineNumberReader reader = null;
        StringBuffer sb = new StringBuffer();
        int importNum = 0;
        try {
            log.info("PAR param import ... start");
            StringBuilder SBdata = new StringBuilder();
            //in = getAsStream(path);
            reader = new LineNumberReader(new InputStreamReader(in));
            String lineData = reader.readLine();
            while (lineData != null) {
                //if (StringUtil.isEmpty(lineData)) {
                    lineData = reader.readLine();
                    //continue;
                //}
                SBdata.append(lineData.trim() + ";");
                if (lineData.trim().startsWith("Profile")) {
                    SBdata.delete(0, SBdata.length());
                    lineData = reader.readLine();
                    continue;
                }
                if (lineData.trim().startsWith("Exit Point")) {
                    Map mps = new HashMap();
                    mps.put("Data", SBdata.toString());
                    importNum++;
                    SBdata.delete(0, SBdata.length());
                    importDataBas(mps);
                }
                lineData = reader.readLine();
            }

            log.info("PAR param import successed --- record num: " + importNum);
            if(importNum == 0){
                throw new Exception("导入记录失败",new Exception("文件可能不匹配"));
            }
        }catch (Exception e) {
            // log.error("===========================> 导入记录错误", e);
            log.error("PAR param import failed --- exception quit", e);
            throw new Exception("导入记录失败", e);
        } finally {
            try {

                reader.close();
            } catch (Exception e) {
            }
            try {
                in.close();
            } catch (Exception e) {
            }
        }

    }

    public void importDataBas(Map mps) {
        //MpsParBicCodeVO bicCodeVO = new MpsParBicCodeVO();
        Map<String, String> dataMap = new HashMap<String, String>();
        String str = (String) mps.get("Data");
        String[] parts = str.split(";");
        for(int i = 0; i < parts.length; i++){
            String[] a=parts[i].split("   ");
            String key = a[0].trim();
            String val = a[a.length-1].trim();
            dataMap.put(key,val);
        }
        Set<String> keys = dataMap.keySet();

        for (String key : keys) {
            String value = dataMap.get(key);
            //bicCodeVO.setBranchCode(value);
            //basDAO.create(bicCodeVO);
        }
    }


}
