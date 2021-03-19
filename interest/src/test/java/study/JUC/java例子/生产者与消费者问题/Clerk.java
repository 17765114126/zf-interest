package study.JUC.java例子.生产者与消费者问题;

public class Clerk {
    private int product = -1;

    //这个方法由生产者调用
    public synchronized void setProduct(int product){
        if (this.product != -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.product = product;
        System.out.printf("生产者设定 (%d)%n",this.product);
        notify();
    }

    //这个方法由消费者调用
    public synchronized int getProduct(){
        if (this.product==-1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int p = this.product;
        System.out.printf("消费者取走 (%d)%n",this.product);
        this.product = -1;
        notify();
        return p;
    }

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        new Thread(new ProducerInt(clerk)).start();
        new Thread(new ConsumerInt(clerk)).start();
    }

}
