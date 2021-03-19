package study.JUC.java例子.生产者与消费者问题;

class  ProducerInt implements Runnable{
    private Clerk clerk;
    public ProducerInt(Clerk clerk){
        this.clerk = clerk;
    }
    public void run() {
        System.out.println("生产者开始生产整数了..................");
        for (int product = 1; product <= 10; product++) {
            try {
                Thread.sleep((int)Math.random()*300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.setProduct(product);
        }
    }

}
