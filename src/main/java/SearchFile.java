import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchFile {

    public static void main(String[] args) {
        //初始目录
        File dir = new File("d:/Dev");
        Date beginDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginDateStr = simpleDateFormat.format(beginDate);
        System.out.println("开始时间：" + beginDateStr);
        printDirByRecursive(dir,0);
        //searchDir(dir);
        Date endDate = new Date();
        String endDateStr = simpleDateFormat.format(endDate);
        System.out.println("结束时间：" + endDateStr);
    }

    /**
     *  遍历文件夹（递归）
     * @param dir
     * @param level
     */
    public static void printDirByRecursive(File dir,int level) {
        System.out.println(dir.getAbsolutePath());
        //输出层次数
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        //获取这个目录下所有的子文件和子目录的数组。
        File[] files = dir.listFiles();
        //遍历这个数组，取出每个File对象
        if (files != null) {
            for (File f : files) {
                //判断这个File是否是一个文件，是：
                if (f.isFile()) {
                    System.out.println(f);
                } else {//否则就是一个目录，继续递归
                    //递归调用
                    printDirByRecursive(f,level+1);
                }
            }
        }
    }
}