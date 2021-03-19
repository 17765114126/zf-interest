package study.JUC.java例子.sleep实现对象存取值;

class  Producer implements Runnable {
    Person person;

    public Producer(Person person) {
        this.person = person;
    }

    public void run() {
        int i = 0;
        while (true) {
            if (i == 0) {
                person.put("谢娜", "女");
            } else {
                person.put("张杰", "男");
            }
            i = (i + 1) % 2;//奇数和偶数
        }

    }
}
