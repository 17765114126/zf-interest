package RobotStudy;

/**
 * Robot类   ：
 * <p>
 * exec 函数声明：
 * public Process exec (String command) throws IOException ,参数及功能说明：
 * command: 一条指定的系统命令
 * 功能：在单独的进程中执行指定的字符串命令
 * <p>
 * keyPress 函数说明：
 * public void keyPress(int keycode),参数及功能说明：
 * keycode：要按下的键（例如，KeyEvent.VK_A）
 * 功能：模拟按下指定键
 * <p>
 * keyRelease 函数说明：
 * public void keyRelease(int keycode),参数及功能说明：
 * keycode：要释放的键
 * 功能：模拟释放指定键
 *
 * @param tovep
 */

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Exce {

    public static void main(String[] args) {
        try {
            //创建自动操作类
            Robot robot = new Robot();
            /**
             *
             *   利用Runtime类运行Word程序的方法为：
             *   Runtime.getRuntime().exec("cmd /c start winword");
             *   括号里的是系统命令
             *
             */
            //启动记事本程序
            Runtime.getRuntime().exec("cmd /c start notepad");

            //延缓几秒钟，等待记事本程序启动成功
            robot.delay(3000);

            //模拟按下"Ctrl + Space" 组合键，启动输入法
            pressKeyWithCtrl(robot, KeyEvent.VK_SPACE);

            //模拟随机按下100个字母，输入汉字
            for (int i = 0; i < 100; i++) {
                pressKey(robot, (int) (Math.random() * 25) + 'A');
                pressKey(robot, KeyEvent.VK_SPACE);
            }

            //延缓5秒钟，一共观察
            robot.delay(5000);

            //关闭记事本
            closeApplication(robot);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //模拟按下键盘字符键
    public static void pressKey(Robot robot, int keyvalue) {
        //模拟按下
        robot.keyPress(keyvalue);

        //模拟弹起
        robot.keyRelease(keyvalue);
    }

    //模拟同时按下"Ctrl"键和字符键
    public static void pressKeyWithCtrl(Robot robot, int keyvalue) {
        //模拟按下
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(keyvalue);

        //模拟弹起
        robot.keyPress(keyvalue);
        robot.keyRelease(KeyEvent.VK_CONTROL);

    }

    //模拟按下"Alt + F4"组合键，关闭当前应用程序
    public static void closeApplication(Robot robot) {
        //模拟按下"Alt + F4"组合键
        //模拟按下
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);

        //模拟弹起
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);

        //模拟按下"N"，不保存文件退出记事本程序
        //模拟按下
        robot.keyPress(KeyEvent.VK_N);

        //模拟弹起
        robot.keyRelease(KeyEvent.VK_N);
    }

}

