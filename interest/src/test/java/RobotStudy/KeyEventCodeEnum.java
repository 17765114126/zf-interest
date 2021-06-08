package RobotStudy;

import java.awt.event.KeyEvent;


/**
 *
 * Java按键事件KeyEvent
 *
 * 按键事件可以利用键盘来控制和执行一些动作，或者从键盘上获取输入，只要按下，释放一个键或者在一个组件上敲击，就会触发按键事件。
 *
 * KeyEvent对象描述事件的特性（按下，放开，或者敲击一个键）和对应的值。
 *
 * java提供KeyListener接口处理按键事件。
 *
 *      当按下一个键时会调用KeyPressed处理器，当松开一个键时会调用KeyReleased处理器，当输入一个统一编码时会调用KeyTyped处理器。
 *
 *      如果这个键不是统一码(如功能键，修改键，动作键和控制键)
 *
 *      每个按键事件有一个相关的按键字符和按键代码，分别由KeyEvent中的getKeyChar()和getKeyCode()方法返回
 *
 *      getKeyChar():  char           返回这个事件中和键相关的字符
 *      getKeyCode():  int             返回这个事件中和键相关的整数键
 *
 *      keyPressed(e: KeyEvent)         在源组件上按下一个键后被调用
 *      KeyReleased(e: KeyEvent)       在源组件上释放一个键后被调用
 *      KeyTyped(e: KeyEvent)           在源组件上按下一个键然后释放该键后被调用
 *
 *      getKeyCode()返回定义在表中的值
 *      getKeyChar()返回输入的字符
 *
 * */
public enum KeyEventCodeEnum {
    HOME(KeyEvent.VK_HOME, "Home键"),
    END(KeyEvent.VK_END, "End键"),
//    PGUP(KeyEvent.VK_PGUP, "page up键"),
//    PGDN(KeyEvent.VK_PGDN, "page down键"),
    up(KeyEvent.VK_UP, "上箭头"),
    DOWN(KeyEvent.VK_DOWN, "下箭头"),
    LEFT(KeyEvent.VK_LEFT, "左箭头 "),
    RIGHT(KeyEvent.VK_RIGHT, "右箭头"),
    ESC(KeyEvent.VK_ESCAPE, "Esc键"),
    TAB(KeyEvent.VK_TAB, "Tab键"),
    CONTROL(KeyEvent.VK_CONTROL, "控制键"),
    SHIFT(KeyEvent.VK_SHIFT, "shift键"),
    BACK_SPACE(KeyEvent.VK_BACK_SPACE, "退格键"),
    CAPS_LOCK(KeyEvent.VK_CAPS_LOCK, "大小写锁定键"),
    NUM_LOCK(KeyEvent.VK_NUM_LOCK, "小键盘锁定键"),
    ENTER(KeyEvent.VK_ENTER, "回车键"),
    UNDEFINED(KeyEvent.VK_UNDEFINED, "未知键"),


    F1(KeyEvent.VK_F1, "F1"),
    F12(KeyEvent.VK_F12, "F12"),
    ZERO(KeyEvent.VK_0, "0"),
    NINE(KeyEvent.VK_9, "9"),
    A(KeyEvent.VK_A, "A"),
    Z(KeyEvent.VK_Z, "Z");

    private Integer code;

    private String msg;

    KeyEventCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public KeyEventCodeEnum setMessage(String msg) {
        this.msg = msg;
        return this;
    }

}
