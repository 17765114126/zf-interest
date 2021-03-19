package study.springStudy.代理.动态代理;

/**
 * @ClassName TalkProxy
 * @Author zhaofu
 * @Date 2019/8/6
 * @Version V1.0
 **/
public class TalkProxy implements Italk {
    Italk talker;

    public TalkProxy(Italk talker) {
        //super();
        this.talker = talker;
    }

    public void talk(String msg) {
        talker.talk(msg);
    }

    public void talk(String msg, String singname) {
        talker.talk(msg);
        sing(singname);
    }

    private void sing(String singname) {
        System.out.println("唱歌：" + singname);
    }
}
