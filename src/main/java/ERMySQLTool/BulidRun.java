package ERMySQLTool;
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import ERMySQLTool.com.utils.ReadFile;
import ERMySQLTool.com.utils.WriteFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BulidRun {
    private static final String type_INTEGER = "INTEGER";
    private static final String type_VARCHAR = "VARCHAR";
    private static final String type_TIMESTAMP = "TIMESTAMP";
    private static final String type_TIMESTAMP_2_DATETIME = "DATETIME";
    private static final String type_CHAR = " CHAR";
    private static final String content_1 = "COMMENT ON COLUMN ";
    private static final String content_2 = " IS";
    private static final String mysql_content_1 = "alter table ";
    private static final String mysql_content_2 = " modify column ";
    private static final String mysql_content_3 = "   comment ";

    public BulidRun() {
    }

    public static void main(String[] args) {
        System.out.println("#################################################################");
        System.out.println("");
        System.out.println("此工具用于解决ER/Studio设置注释definition依然无法生成Mysql的列注释问题");
        System.out.println("整体步骤：");
        System.out.println("1、使用ER/Studio生成Mysql的sql在数据库建表");
        System.out.println("2、使用ER/Studio生成DB2的sql文件,用notepadd++转换转为UTF-8编码否则会乱码");
        System.out.println("3、通过java jar db2sqlConvert2MysqlComment.jar 启动运行此工具");
        System.out.println("4、按提示输入db2sql文件的路径，回车将会在同目录生成mysql添加注释的文件");
        System.out.println("5、在步骤1的数据库实例下执行此mysql添加注释的sql文件");
        System.out.println("另外注意：");
        System.out.println("1、目前只支持INTEGER、VARCHAR、TIMESTAMP、CHAR的注释生成，其他将忽略");
        System.out.println("2、ER/Studio中注释内容不要换行，否则请手动调整生成的文件补填单引号和缺失内容");
        System.out.println("");
        System.out.println("#################################################################");
        System.out.print("请输入db2sql路径：");
        Scanner scan = new Scanner(System.in);
        String db2patch = scan.nextLine();
        System.out.println("输入数据：" + db2patch);

//        String db2patch = "D:/Company/MySQLDB";
        String[] db2sql = ReadFile.readToString(db2patch).split("\n");
        StringBuffer sb = new StringBuffer();
        String mysqlcomment = "";
        Map<String, String> map = new HashMap();
        String[] var10 = db2sql;
        int var9 = db2sql.length;

        String line;
        int var8;
        for(var8 = 0; var8 < var9; ++var8) {
            line = var10[var8];
            String k;
            String v;
            if (line.indexOf("INTEGER") >= 0) {
                k = line.substring(0, line.indexOf("INTEGER")).trim();
                v = "INTEGER";
                map.put(k, v);
            }

            if (line.indexOf("VARCHAR") >= 0) {
                k = line.substring(0, line.indexOf("VARCHAR")).trim();
                v = line.substring(line.indexOf("VARCHAR"), line.indexOf(")") + 1).trim();
                map.put(k, v);
            }

            if (line.indexOf("TIMESTAMP") >= 0) {
                k = line.substring(0, line.indexOf("TIMESTAMP")).trim();
                v = "DATETIME";
                map.put(k, v);
            }

            if (line.indexOf(" CHAR") >= 0) {
                k = line.substring(0, line.indexOf(" CHAR")).trim();
                v = line.substring(line.indexOf(" CHAR"), line.indexOf(")") + 1).trim();
                map.put(k, v);
            }
        }

        var10 = db2sql;
        var9 = db2sql.length;

        for(var8 = 0; var8 < var9; ++var8) {
            line = var10[var8];
            if (line.indexOf("COMMENT ON COLUMN ") >= 0) {
                mysqlcomment = buildMysqlcomment(sb, line, map);
            }
        }

        File file = new File(db2patch);
        WriteFile.createFile(file.getParent() + File.separator, file.getName().replace(".", "_") + "_mysqlcomment_" + System.currentTimeMillis(), mysqlcomment);
    }

    private static String buildMysqlcomment(StringBuffer sb, String line, Map<String, String> map) {
        String tableName = line.substring(line.indexOf("COMMENT ON COLUMN ") + "COMMENT ON COLUMN ".length(), line.indexOf("."));
        String col = line.substring(line.indexOf(".") + 1, line.indexOf(" IS")) + " ";
        String v = (String)map.get(col.trim());
        if (v != null && !"".equals(v)) {
            String comment = line.substring(line.indexOf(" IS") + " IS".length(), line.length());
            return sb.append("alter table ").append(tableName).append(" modify column ").append(col).append(v).append("   comment ").append(comment).append("\n").append(";").toString();
        } else {
            return sb.toString();
        }
    }
}
