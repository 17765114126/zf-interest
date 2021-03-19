package study.springStudy.静态代理;

/**
 * @ClassName Host
 * @Author zhaofu
 * @Date 2020/12/31
 * @Version V1.0
 * 真实角色: 房东，房东要出租房子
 **/

public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房屋出租");
    }
}
