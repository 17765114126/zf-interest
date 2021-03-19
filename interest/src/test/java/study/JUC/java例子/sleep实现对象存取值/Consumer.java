package study.JUC.java例子.sleep实现对象存取值;

class Consumer implements Runnable{
    Person person;
    public Consumer(Person person){
        this.person = person;
    }
    public void run() {
        while(true){
            person.get();
        }
    }
}