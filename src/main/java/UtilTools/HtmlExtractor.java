package UtilTools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.io.IOException;

public class HtmlExtractor {
    public static void main(String[] args) {
        String url = "https://blog.csdn.net/weixin_58079523/article/details/129928808"; // 替换为实际网址
        String outputFilePath = "extracted_content.html"; // 输出 HTML 文件路径

        try {
            // 获取文档内容
            Document document = Jsoup.connect(url).get();

            // 提取 id 为 content_views 且 class 为 htmledit_views 的 div
            Element contentDiv = document.selectFirst("div#content_views.htmledit_views");

            if (contentDiv != null) {
                // 获取 div 内的 HTML
                String contentHtml = contentDiv.html();

                // 包裹成完整的 HTML 文档格式
                String fullHtml = "<html><head><title>Extracted Content</title></head><body>"
                        + contentHtml
                        + "</body></html>";

                // 将 HTML 保存到文件
                try (FileWriter writer = new FileWriter(outputFilePath)) {
                    writer.write(fullHtml);
                }

                System.out.println("Content saved successfully to " + outputFilePath);
            } else {
                System.out.println("No matching div found.");
            }
        } catch (IOException e) {
            System.out.println("Error fetching or saving HTML content: " + e.getMessage());
        }
    }
}



