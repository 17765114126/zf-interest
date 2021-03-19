package study.JUC.java例子.sleep实现对象存取值;

class Person{
    private String name = "张杰";
    private String sex = "男";
    public synchronized void put(String name,String sex){
        this.name = name;
        this.sex = sex;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //方法加锁
    public synchronized void get(){
        System.out.println(name+"----->"+sex);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
