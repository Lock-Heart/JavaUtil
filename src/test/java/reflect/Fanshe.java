package reflect;

/**
 * 获取Class对象的三种方式
 * 1 Object ——> getClass();
 * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
 * 3 通过Class类的静态方法：forName（String  className）(常用)
 *
 */
public class Fanshe {
	public static void main(String[] args) {
		//第一种方式获取Class对象
		Dept stu1 = new Dept(10,"财务部","上海");//这一new 产生一个Student对象，一个Class对象。
		Class deptClass = stu1.getClass();//获取Class对象
		System.out.println(deptClass.getName());

		//第二种方式获取Class对象
		Class deptClass2 = Dept.class;
		System.out.println(deptClass == deptClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

		//第三种方式获取Class对象
		try {
			Class deptClass3 = Class.forName("reflect.Dept");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
			System.out.println(deptClass3 == deptClass2);//判断三种方式是否获取的是同一个Class对象
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}