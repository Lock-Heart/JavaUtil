class Channel {
    public void connect() {
        System.out.println("[Channel父类]进行资源的连接。") ;
    }
    public void fun() {
        this.connect() ; // 调用本类方法
    }
}
class DatabaseChannel extends Channel { // 要进行数据库连接
    // 此时并不是一个覆写，因为父类的connect()方法不可见，那么这个方法对于子类而言就相当于一个新定义的方法
    public void connect() {
        System.out.println("[子类]进行数据库资源的连接") ;
    }
}
public class JavaDemo {
    public static void main(String args[]) {
        DatabaseChannel channel = new DatabaseChannel() ;
        channel.fun() ;
    }
}
