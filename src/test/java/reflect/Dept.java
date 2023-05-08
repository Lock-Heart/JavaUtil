package reflect;

class Dept {
    private long deptno;
    //private Emp emps[]; // 多个雇员信息
    private String dname;
    private String loc;

    public Dept(long deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

//    public void setEmps(Emp[] emps) {
//        this.emps = emps;
//    }

//    public Emp[] getEmps() {
//        return this.emps;
//    }

    // setter、getter无参构造略
    public String getInfo() {
        return "[部门信息]部门编号 = " + this.deptno + "、部门名称 = " + this.dname + "、部门位置 = " + this.loc;
    }
}




