package study.JUC.java例子.银行存取钱问题;

class Women implements Runnable{
    private Bank bank = new Bank();

    public void run() {
        int m = 100;
        int i=0;
        //bank.getMoney()>0
        while (i<5) {
            bank.drawMoney(m,i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

}
