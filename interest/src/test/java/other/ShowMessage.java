package other;

import javax.swing.*;

/**
 * @ClassName showMessage
 * @Author zhaofu
 * @Date 2021/6/8
 * @Version V1.0
 **/
public class ShowMessage {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "普通提示框！", "哈哈",JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null, "警告提示框！", "哈哈",JOptionPane.WARNING_MESSAGE);

        JOptionPane.showMessageDialog(null, "错误提示框！", "哈哈",JOptionPane.ERROR_MESSAGE);

        JOptionPane.showConfirmDialog(null,"yes or no","标题",JOptionPane.YES_NO_OPTION);

        Object[] options = {"不说","说"};
        JOptionPane.showOptionDialog(null,"你说话呀！！","标题",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

        Object[] objects = {"甲", "乙", "丙"};
        String s = (String) JOptionPane.showInputDialog(null, "请选择你的身份\n", "身份",
                JOptionPane.PLAIN_MESSAGE, new ImageIcon("icon.png"), objects, "足球");
        System.out.println(s);

        String title = JOptionPane.showInputDialog(null, "请输入：\n", "title", JOptionPane.PLAIN_MESSAGE);
        System.out.println(title);

    }
}
