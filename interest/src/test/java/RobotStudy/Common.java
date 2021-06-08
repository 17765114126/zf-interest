package RobotStudy;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 * @ClassName Common
 * @Author zhaofu
 * @Date 2021/6/8
 * @Version V1.0
 * Robot帮助类，实现基本的功能
 *
 * 一、Robot主要的功能
 *
 * 1. BufferedImage createScreenCapture(Rectangle screenRect)
 *
 * 说明：该方法提供类似于键盘上的PrintScreen键的功能，将指定矩形区域内的屏幕像素copy下来产生一个BufferedImage。
 * 应用：我们可以将这个方法用在图形程序中，或是用它来实现远端屏幕传输，可做成远端电脑监控程序等.
 *
 * 2. void delay(int ms)
 *
 * 说明：用来将当前的程序(thread)休眠(sleep)若干毫秒(ms)。
 * 应用：可用来控制程序的延时。这个一般是必须的，因为你在两次间隔操作中肯定有延时。
 *
 * 3. Color getPixelColor(int x, int y)
 *
 * 说明：取得给定屏幕坐标像素位置的颜色值。
 * 应用：就是取颜色RGB值，就不多说了。
 *
 * 4. void keyPress(int keycode)
 * 　 void keyRelease(int keycode)
 *
 * 说明：这两个方法的作用一看便知，用来产生指定键的按键按下与抬起动作,相当于Win32 API的keyb_event函数，即模拟键盘操作咯，
 * 具体keycode值就是KeyEvent.VK_C、KeyEvent.VK_D、KeyEvent.VK_CONTROL什么的，具体应用时直接看Eclipse提示就知道了。
 * 应用：可用于程序的自动演示、测试等，非常有用。
 *
 * 5. void mouseMove(int x, int y)
 * 说明：将鼠标光标移动到指定的屏幕坐标。
 * 应用：可用于程序的自动演示、测试等，配合其他的方法使用，是不可缺少的。
 *
 * 6. void mousePress(int buttons)
 * 　 void mouseRelease(int buttons)
 * 　 void mouseWheel(int wheelAmt)
 *
 * 说明：上面的三种方法，产生指定鼠标按钮的按下，抬起，及滚轮动作，就是模拟鼠标操作咯，具体buttons的值有InputEvent.BUTTON1_MASK（鼠标左键）、InputEvent.BUTTON3_MASK（鼠标右键，如果是双键鼠标,请改用InputEvent.BUTTON2_MASK）等。
 *
 * 应用：一样也可用于程序的自动演示、测试等，配合其他方法使用，很重要。
 *
 *
 **/
public class Common {

    /**
     * 鼠标单击（左击）,要双击就连续调用
     *
     * @param r
     * @param x
     *      x坐标位置
     * @param y
     *      y坐标位置
     * @param delay
     *      该操作后的延迟时间
     */
    public static void clickLMouse(Robot r, int x, int y, int delay) {
        r.mouseMove(x, y);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.delay(10);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.delay(delay);

    }

    /**
     * 鼠标右击,要双击就连续调用
     *
     * @param r
     * @param x
     *      x坐标位置
     * @param y
     *      y坐标位置
     * @param delay
     *      该操作后的延迟时间
     */
    public static void clickRMouse(Robot r, int x, int y, int delay) {
        r.mouseMove(x, y);
        r.mousePress(InputEvent.BUTTON3_MASK);
        r.delay(10);
        r.mouseRelease(InputEvent.BUTTON3_MASK);
        r.delay(delay);

    }

    /**
     * 键盘输入（一次只能输入一个字符）
     *
     * @param r
     * @param ks
     *      键盘输入的字符数组
     * @param delay
     *      输入一个键后的延迟时间
     */
    public static void pressKeys(Robot r, int[] ks, int delay) {
        for (int i = 0; i < ks.length; i++) {
            r.keyPress(ks[i]);
            r.delay(10);
            r.keyRelease(ks[i]);
            r.delay(delay);
        }
    }

    /**
     * 复制
     *
     * @param r
     * @throws InterruptedException
     */
    void doCopy(Robot r) throws InterruptedException {
        Thread.sleep(3000);
        r.setAutoDelay(200);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_C);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_C);
    }

    /**
     * 粘贴
     *
     * @param r
     * @throws InterruptedException
     */
    void doParse(Robot r) throws InterruptedException {
        r.setAutoDelay(500);
        Thread.sleep(2000);
        r.mouseMove(300, 300);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
    }

    /**
     * 捕捉全屏慕
     *
     * @param r
     * @return
     */
    public Icon captureFullScreen(Robot r) {
        BufferedImage fullScreenImage = r.createScreenCapture(new Rectangle(
                Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIcon icon = new ImageIcon(fullScreenImage);
        return icon;
    }

    /**
     * 捕捉屏幕的一个矫形区域
     *
     * @param r
     * @param x
     *      x坐标位置
     * @param y
     *      y坐标位置
     * @param width
     *      矩形的宽
     * @param height
     *      矩形的高
     * @return
     */
    public Icon capturePartScreen(Robot r, int x, int y, int width, int height) {
        r.mouseMove(x, y);
        BufferedImage fullScreenImage = r.createScreenCapture(new Rectangle(
                width, height));
        ImageIcon icon = new ImageIcon(fullScreenImage);
        return icon;
    }

}
