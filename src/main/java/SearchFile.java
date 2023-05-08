import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchFile {

    public static void main(String[] args) {
        //鍒濆鐩綍
        File dir = new File("D:\\Company\\elc");
        Date beginDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginDateStr = simpleDateFormat.format(beginDate);
        System.out.println("寮�濮嬫椂闂达細" + beginDateStr);
        printDirByRecursive(dir,0);
        //searchDir(dir);
        Date endDate = new Date();
        String endDateStr = simpleDateFormat.format(endDate);
        System.out.println("缁撴潫鏃堕棿锛�" + endDateStr);
    }

    /**
     *  閬嶅巻鏂囦欢澶癸紙閫掑綊锛�
     * @param dir
     * @param level
     */
    public static void printDirByRecursive(File dir,int level) {
        System.out.println(dir.getAbsolutePath());
        //杈撳嚭灞傛鏁�
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        //鑾峰彇杩欎釜鐩綍涓嬫墍鏈夌殑瀛愭枃浠跺拰瀛愮洰褰曠殑鏁扮粍銆�
        File[] files = dir.listFiles();
        //閬嶅巻杩欎釜鏁扮粍锛屽彇鍑烘瘡涓狥ile瀵硅薄
        if (files != null) {
            for (File f : files) {
                //鍒ゆ柇杩欎釜File鏄惁鏄竴涓枃浠讹紝鏄細
                if (f.isFile()) {
                    System.out.println(f);
                } else {//鍚﹀垯灏辨槸涓�涓洰褰曪紝缁х画閫掑綊
                    //閫掑綊璋冪敤
                    printDirByRecursive(f,level+1);
                }
            }
        }
    }
}