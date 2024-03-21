//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ERMySQLTool.com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFile {
    private static final Integer ONE = 1;

    public ReadFile() {
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException var6) {
            System.err.println("The OS does not support " + encoding);
            var6.printStackTrace();
            return null;
        }
    }

    public List<String> readToList(String fileName) {
        Map<String, Integer> map = new HashMap();
        List<String> ls = new ArrayList();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "UTF-8"));
            String lineTxt = null;

            while((lineTxt = br.readLine()) != null) {
                String[] names = lineTxt.split(",");
                String[] var10 = names;
                int var9 = names.length;

                for(int var8 = 0; var8 < var9; ++var8) {
                    String name = var10[var8];
                    if (map.keySet().contains(name)) {
                        map.put(name, (Integer)map.get(name) + ONE);
                    } else {
                        map.put(name, ONE);
                    }
                }
            }

            br.close();
        } catch (Exception var11) {
            System.err.println("read errors :" + var11);
        }

        return ls;
    }
}
