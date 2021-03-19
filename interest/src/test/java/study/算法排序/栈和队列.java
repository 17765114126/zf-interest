package study.算法排序;

import org.junit.Test;

/**
 * @ClassName 栈和队列
 * @Author zhaofu
 * @Date 2021/1/20
 * @Version V1.0
 * @Url：https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484090&idx=1&sn=d46205eed74e9b253ec08796124192c1&chksm=ebd743bbdca0caad2129f0d76f002b7e14e125900ecac1fa9a3a433e3a9bcd2831275a1e260c&scene=21#wechat_redirect
 *
 * 上一篇已经讲过了链表【Java实现单向链表】了，它跟数组都是线性结构的基础，
 *
 * 本文主要讲解线性结构的应用：栈和队列
 *
 *
 * 二、数据结构【栈】就是这么简单
 *
 * 2.1数据结构【栈】介绍
 *
 * 数据结构的栈长的是这个样子：
 *
 * 其实非常好理解，我们将栈可以看成一个箱子
 *
 * 往箱子里面放东西叫做入栈
 * 往箱子里面取东西叫做出栈
 * 箱子的底部叫做栈底
 * 箱子的顶部叫做栈顶
 *
 * 说到栈的特性，肯定会有一句经典的言语来概括：先进后出(LIFO, Last In First Out)
 *
 * 往箱子里边放苹果，箱子底部的苹果想要拿出来，得先把箱子顶部的苹果取走才行
 *
 **/
public class 栈和队列 {

    /**
     *2.2数据结构【栈】 代码实现
     * 栈的分类有两种：
     *
     * - 静态栈(数组实现)
     *
     * - 动态栈(链表实现)
     *
     * 从上一篇写链表我就认知到我的算法是有多渣了，普通的单链表操作也能把我绕得晕晕的。
     *
     * 由于我的链表还不是很熟，栈又不是很难，那么我就用链表来创建动态栈了！
     *
     * 既然是用链表，我们还是把上一篇节点的代码拿过来吧：
     * */

    public class Node {
        //数据域
        public int data;

        //指针域，指向下一个节点
        public Node next;

        public Node() {
        }
        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * 要链表用来表示栈，这次会有两个指针：
     *
     * - 栈顶
     * - 栈底
     * */
    public class Stack {
        public Node stackTop;
        public Node stackBottom;

        public Stack(Node stackTop, Node stackBottom) {
            this.stackTop = stackTop;
            this.stackBottom = stackBottom;
        }
        public Stack() {
        }
    }

    /**
     * 进栈
     *
     * @param stack 栈
     * @param value 要进栈的元素
     *
     * 将原本栈顶指向的节点交由新节点来指向，栈顶指向新加入的节点
     */
    public void pushStack(Stack stack, int value) {
        // 封装数据成节点
        Node newNode = new Node(value);
        // 栈顶本来指向的节点交由新节点来指向
        newNode.next = stack.stackTop;
        // 栈顶指针指向新节点
        stack.stackTop = newNode;
    }

    /**
     * 遍历栈(只要栈顶指针不指向栈底指针，就一直输出)
     *
     * @param stack
     */
    public void traverse(Stack stack) {
        Node stackTop = stack.stackTop;
        while (stackTop != stack.stackBottom) {
            System.out.println("--------->：" + stackTop.data);
            stackTop = stackTop.next;
        }
    }

    @Test
    public void Test() {
        //初始化栈(无元素)
        Stack stack = new Stack(new Node(), new Node());

        //栈顶和栈尾是同一指向
        stack.stackBottom = stack.stackTop;

        //指向null
        stack.stackTop.next = null;

        //进栈
        pushStack(stack, 3);
        pushStack(stack, 4);
        pushStack(stack, 5);
        traverse(stack);
    }

    /**
     * 判断该栈是否为空
     * @param stack
     */
    public static Boolean isEmpty(Stack stack) {
        if (stack.stackTop == stack.stackBottom) {
            System.out.println("---->该栈为空");
            return true;
        } else {
            System.out.println("---->该栈不为空");
            return false;
        }
    }

    /**
     * 出栈(将栈顶的指针指向下一个节点)
     * @param stack
     *
     * 出栈之前看看该栈是否为空，不为空才出栈…
     * 将栈顶的元素的指针(指向下一个节点)赋值给栈顶指针(完成出栈)
     */
    public static void popStack(Stack stack) {

        // 栈不为空才能出栈
        if (!isEmpty(stack)) {
            //栈顶元素
            Node top = stack.stackTop;

            // 栈顶指针指向下一个节点
            stack.stackTop = top.next;

            System.out.println("关注公众号：Java3y---->出栈的元素是：" + top.data);

        }
    }



    /**
     * 清空栈
     * @param stack
     *
     * 当时学C的时候需要释放内存资源，可是Java不用呀，所以栈顶指向栈底，就清空栈了
     */
    public static void clearStack(Stack stack) {
        stack.stackTop = null;
        stack.stackBottom = stack.stackTop;
    }


    /**
     * 三、数据结构【队列】就是这么简单
     *
     * 数据结构的队列长的是这个样子：
     *
     *
     * 其实队列非常好理解，我们将队列可以看成小朋友排队
     *
     * - 队尾的小朋友到指定的地点了-->出队
     *
     * - 有新的小朋友加入了-->入队
     *
     * 相对于栈而言，队列的特性是：先进先出
     *
     * - 先排队的小朋友肯定能先打到饭！
     *
     * 队列也分成两种：
     *
     * - 静态队列(数组实现)
     *
     * - 动态队列(链表实现)
     *
     * 这次我就使用数组来实现静态队列了。值得注意的是：往往实现静态队列，我们都是做成循环队列
     *
     *
     * 做成循环队列的好处是不浪费内存资源！
     * */

    /**
     * 3.1数据结构【队列】 代码实现
     * 这次我们使用的是数组来实现静态队列，因此我们可以这样设计：
     * */
    public class Queue {
        //数组
        public int [] arrays;
        //指向第一个有效的元素
        public int front;
        //指向有效数据的下一个元素(即指向无效的数据)
        public int rear;
    }


    /**
     * 从上面的设计我们可以发现：rear并不指向最后一个有效的元素，在循环队列中这样设计是非常方便的！
     * 因为这样设计可以让我们分得清队头和队尾(不然循环队列不断入队或出队，位置是变化很快的)
     *
     * 由于我们是循环队列，所以front和rear值会经常变动，我们得把front和rear的值限定在一个范围内，不然会超出队列的长度的。
     *
     * 有这么一个算法：rear=（rear+1)%数组长度
     *
     * 比如rear的下标是2，数组的长度是6，往后面移一位是3，那么rear = （rear+1) % 6，结果还是3
     * */

    /**
     * 3.1.2初始化队列
     * 此时队列为空，分配了6个长度给数组(只能装5个实际的数字，rear指向的是无效的位置的)
     * */
    @Test
    public void test1() {
        //初始化队列
        Queue queue = new Queue();

        queue.front = 0;
        queue.rear = 0;
        queue.arrays = new int[6];
    }

    /**
     * 3.1.3判断队列是否满了
     * 如果rear指针和front指针紧挨着，那么说明队列就满了
     * */

    /**
     * 判断队列是否满了，front和rear指针紧挨着，就是满了
     * @param queue
     * @return
     */
    public static boolean isFull(Queue queue) {
        if ((queue.rear + 1) % queue.arrays.length == queue.front) {
            System.out.println("--->此时队列满了！");
            return true;
        } else {
            System.out.println("--->此时队列没满了！");
            return false;
        }
    }


    /**
     * 3.1.4入队
     * 判断该队列是否满了
     *
     * 入队的值插入到队尾中(具体的位置就是rear指针的位置【再次声明：rear指向的是无效元素的位置】
     *
     * rear指针移动(再次指向无效的元素位置)
     * */
    /**
     * 入队
     *
     * @param queue
     */
    public static void enQueue(Queue queue,int value) {
        // 不是满的队列才能入队
        if (!isFull(queue)) {
            // 将新的元素插入到队尾中
            queue.arrays[queue.rear] = value;
            // rear节点移动到新的无效元素位置上
            queue.rear = (queue.rear + 1) % queue.arrays.length;
        }
    }

    /**
     * 遍历队列
     * @param queue
     *只要front节点不指向rear节点，那么就可以一直输出
     *
     */
    public static void traverseQueue(Queue queue) {
        // front的位置
        int i = queue.front;
        while (i != queue.rear) {
            System.out.println("--->" + queue.arrays[i]);

            //移动front
            i = (i + 1) % queue.arrays.length;
        }
    }

    /**
     * 队列没满时：
     *
     * 队列已满了就插入不了了(验证上面的方法是否正确)：
     * */

    /**
     * 判断队列是否空，front和rear指针相等，就是空了
     * @param queue
     * @return
     * 只要rear和front指针指向同一个位置，那该队列就是空的了
     */
    public static boolean isEmpty(Queue queue) {
        if (queue.rear  == queue.front) {
            System.out.println("--->此时队列空的！");
            return true;
        } else {
            System.out.println("--->此时队列非空！");
            return false;
        }
    }

    /**
     * 出队
     * @param queue
     *
     * 判断该队列是否为null
     * 如果不为null，则出队，只要front指针往后面移就是出队了!
     */
    public static void outQueue(Queue queue) {
        //判断该队列是否为null
        if (!isEmpty(queue)) {
            //不为空才出队
            int value = queue.arrays[queue.front];
            System.out.println("--->出队的元素是：" + value);
            // front指针往后面移
            queue.front = (queue.front + 1) % queue.arrays.length;
        }
    }


    /**
     * 总结
     * 数据结构的栈和队列的应用非常非常的多，这里也只是最简单的入门，理解起来也不困难。
     *
     * - 栈：先进后出
     *
     * - 队列：先进先出
     *
     * 关于数据结构这方面我就到暂时到这里为止了，都简单的入个门，以后遇到更加复杂的再继续开新的文章来写～
     * 毕竟现在水平不够，也无法理解更深层次的东西～数据结构这东西是必备的，等到研究集合的时候还会来回顾它，或者遇到新的、复杂的也会继续学习….
     *
     * 想要更加深入数据结构的同学就得去翻阅相关的书籍咯～这仅仅是冰山一角
     * */
}
