package UtilTools;

import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;

public class ConcertEncodeing {

    public static void main(String[] args) {

        convertCharset("D:\\Company\\mps-core\\src\\main\\java\\com\\hylandtec",Charset.forName("UTF-8"),Charset.forName("GBK"),"java");

    }

    /**
     * 转换文件编码格式
     * @param path 需要转换的文件或文件夹路径
     * @param fromCharset 原编码格式
     * @param toCharset   目标编码格式
     * @param expansion      需要转换的文件扩展名,如需全部转换则传 null
     */
    private static void convertCharset(String path,Charset fromCharset,Charset toCharset,String expansion ) {
        if (StrUtil.isBlank(path)) {
            return;
        }
        File file = FileUtil.file(path);
        File[] listFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (StrUtil.isBlank(expansion)) {
                    return true;
                }
                if (FileUtil.isDirectory(pathname)||FileUtil.extName(pathname).equals("java")) {
                    return true;
                }
                return false;
            }
        });
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                String canonicalPath = FileUtil.getCanonicalPath(listFiles[i]);
                //每个文件夹分个线程处理,提高点儿效率
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        convertCharset(canonicalPath,fromCharset,toCharset,expansion);
                    }
                }).start();
            }else {
                FileUtil.convertCharset(listFiles[i], fromCharset,  toCharset);
                Console.log("转换文件完成",listFiles[i].getName());
            }
        }
    }
}
