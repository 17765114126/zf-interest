package study.JUC.java例子.生产与销售;

import java.util.LinkedList;
/**
 *
 * 工厂
 *
 * */
public class MachineFactoryImpl implements MachineFactory {

    // 最大库存量(爆仓啦)
    private final int MAX_STORAGE = 100;
    // 仓库空间
    private LinkedList list = new LinkedList();

    @Override
    public void production(int num) {
        // 负责生产产品
        // 生产前需要判断仓库满了没有，如果仓库放不下，则等待被消费后，满足空间条件再生产

        // 生产时需要持有list的锁，避免因为同时生产和消费时，产生队列下标异常的错误。
        synchronized (list){
            while (list.size() + num > MAX_STORAGE){
                System.out.println("【当前仓库库存量: "+list.size()+"】，【即将生产的货量: "+num+"】 等待仓库位置空间！");
                try {
                    Thread.sleep(3000);
                    // 线程释放锁，进入等待状态
                    list.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            new WorkShop().doWork(num);
            // 满足生产条件进行生产
            for (int i = 0; i<num; i++){
                list.add(new Object());
            }
            System.out.println("【生产的数量："+num + "】"+"【当前仓库的库存："+list.size() + "】");
            // 此时可能存在消费者在等待工厂生产产品，所以要通知所有处于wait状态的消费线程
            // 他们将收到状态改变的通知进行判断是否执行程序代码
            list.notifyAll();
        }
    }

    @Override
    public void consume(int num) {
        // 负责消费产品
        // 消费的前提是拥有足够的产品，若是仓库不存在这么多的产品，则进行等待，直到有这么多的产品进行消费
        synchronized (list){
            while (list.size() < num){
                System.out.println("【当前仓库库存量: "+list.size()+"】，【即将消费的数量: "+num+"】 等待工厂生产！");
                try {
                    Thread.sleep(2000);
                    // 让出锁，进入等待状态
                    list.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            new Store().sell(num);
            // 满足条件
            for (int i = 0; i<num; i++){
                list.remove();
            }
            System.out.println("【消耗的数量："+num +"】,【当前仓库剩余："+list.size() + "】");
            // 此时仓库可能已经爆仓，需要通知生产线程进行过了一次消费，此时生产现场收到状态会进行重新的检查
            list.notifyAll();
        }
    }
}