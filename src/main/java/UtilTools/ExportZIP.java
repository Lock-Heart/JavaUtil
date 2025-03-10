package UtilTools;

import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ExportZIP {

    /**
     * 导出到本目录下
     * @param params
     */
    public void export(@RequestBody Map<String, Object> params) {
        ArrayList<String> msgTexts = (ArrayList<String>) params.get("texts");
        // 创建一个临时目录来存储这些文件
        File tempDir = new File("tempFiles");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        // 保存每个文本内容为独立的 .txt 文件
        try {
            for (int i = 0; i < msgTexts.size(); i++) {
                File txtFile = new File(tempDir, "file" + (i + 1) + ".txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile))) {
                    writer.write(msgTexts.get(i));
                }
                System.out.println("文件 " + txtFile.getName() + " 已保存！");
            }
            // 将文件夹压缩为一个 zip 文件
            File zipFile = new File("texts.zip");
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
                // 遍历 tempDir 目录中的所有文件并将它们添加到 zip 文件中
                for (File file : tempDir.listFiles()) {
                    if (file.isFile()) {
                        try (FileInputStream fis = new FileInputStream(file)) {
                            ZipEntry zipEntry = new ZipEntry(file.getName());
                            zos.putNextEntry(zipEntry);

                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = fis.read(buffer)) > 0) {
                                zos.write(buffer, 0, length);
                            }

                            zos.closeEntry();
                        }
                    }
                }
                System.out.println("所有文件已压缩为 'texts.zip'");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 删除临时目录和文件
            for (File file : tempDir.listFiles()) {
                file.delete();
            }
            tempDir.delete();
        }
        return;
    }


    /**
     * 浏览器下载
     * @param params
     * @param response
     */
    public void export(@RequestBody Map<String, Object> params, HttpServletResponse response) {
        ArrayList<String> msgTexts = (ArrayList<String>) params.get("texts");
        if (msgTexts == null || msgTexts.isEmpty()) {
            throw new IllegalArgumentException("文本内容不能为空");
        }
        // 生成唯一的临时文件夹
        String tempDirPath = System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID();
        File tempDir = new File(tempDirPath);
        tempDir.mkdirs();
        File zipFile = new File(tempDir, "texts.zip");
        try {
            // 创建 txt 文件
            for (int i = 0; i < msgTexts.size(); i++) {
                File txtFile = new File(tempDir, i + ".sf2");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile))) {
                    writer.write(msgTexts.get(i));
                }
            }

            // 压缩文件
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
                for (File file : Objects.requireNonNull(tempDir.listFiles())) {
                    if (file.isFile() && !file.getName().equals("texts.zip")) {
                        try (FileInputStream fis = new FileInputStream(file)) {
                            ZipEntry zipEntry = new ZipEntry(file.getName());
                            zos.putNextEntry(zipEntry);

                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = fis.read(buffer)) > 0) {
                                zos.write(buffer, 0, length);
                            }
                            zos.closeEntry();
                        }
                    }
                }
            }

            // 处理文件下载
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=\"texts.zip\"");
            try (FileInputStream fis = new FileInputStream(zipFile);
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            // 清理临时文件
            for (File file : Objects.requireNonNull(tempDir.listFiles())) {
                file.delete();
            }
            tempDir.delete();
        }
    }


    /**
     * 前端接收
     */



}

