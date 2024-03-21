//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ERMySQLTool.com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WriteFile {
    private static String filenameTemp;

    public WriteFile() {
    }

    public static boolean createFile(String path, String fileName, String filecontent) {
        Boolean bool = false;
        filenameTemp = path + fileName + ".sql";
        File file = new File(filenameTemp);

        try {
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is " + filenameTemp);
                writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return bool;
    }

    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";
        String temp = "";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;

        try {
            File file = new File(filepath);
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            for(int i = 0; (temp = br.readLine()) != null; ++i) {
                buffer.append(temp);
                buffer = buffer.append(System.getProperty("line.separator"));
            }

            buffer.append(filein);
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }

            if (fos != null) {
                fos.close();
            }

            if (br != null) {
                br.close();
            }

            if (isr != null) {
                isr.close();
            }

            if (fis != null) {
                fis.close();
            }

        }

        return bool;
    }

    public static boolean delFile(String path, String fileName) {
        Boolean bool = false;
        filenameTemp = path + fileName + ".txt";
        File file = new File(filenameTemp);

        try {
            if (file.exists()) {
                file.delete();
                bool = true;
            }
        } catch (Exception var5) {
        }

        return bool;
    }
}
