package game.snake;

import javax.swing.*;
import java.net.URL;
@SuppressWarnings("all")
public class Data {
    //头部图片
    public static URL headerUrl = Data.class.getResource("/snakeImg/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);
    //头部：上下左右
    public static URL upUrl = Data.class.getResource("/snakeImg/up.png");
    public static URL downUrl = Data.class.getResource("/snakeImg/down.png");
    public static URL leftUrl = Data.class.getResource("/snakeImg/left.png");
    public static URL rightUrl = Data.class.getResource("/snakeImg/right.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);
    //身体
    public static URL bodyUrl = Data.class.getResource("/snakeImg/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //食物
    public static URL foodUrl = Data.class.getResource("/snakeImg/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}
