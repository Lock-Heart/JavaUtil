import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test  {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        scan.delimiter();
        /*读取本地文件*/
        Scanner sc = new Scanner(new File("myNumbers"));
        while (sc.hasNextLong()) {
            long aLong = sc.nextLong();
        }
    }
}
