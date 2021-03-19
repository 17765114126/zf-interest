package study.算法排序;

/**
 * @ClassName Java实现单向链表
 * @Author zhaofu
 * @Date 2021/1/20
 * @Version V1.0
 * @Url:https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484086&idx=1&sn=24127ceb5e0fed7f832f82579c4fbc19&chksm=ebd743b7dca0caa1dce912d47251548225aa59b4b48742a963ac52b05e13ec923a738ebcb836&scene=21#wechat_redirect 最近在回顾数据结构与算法，有部分的算法题用到了栈的思想，说起栈又不得不说链表了。
 * 数组和链表都是线性存储结构的基础，栈和队列都是线性存储结构的应用～
 * <p>
 * 本文主要讲解单链表的基础知识点
 * <p>
 * <p>
 * 说起链表，我们先提一下数组吧，跟数组比较一下就很理解链表这种存储结构了。
 * <p>
 * 2.1回顾数组
 * 数组我们无论是C、Java都会学过：
 * <p>
 * 数组是一种连续存储线性结构，元素类型相同，大小相等
 * <p>
 * 数组的优点： 存取速度快
 * <p>
 * 数组的缺点：
 * <p>
 * 1:事先必须知道数组的长度
 * 2:插入删除元素很慢
 * 3:空间通常是有限制的
 * 4:需要大块连续的内存块
 * 5:插入删除元素的效率很低
 */

/**
 * 说起链表，我们先提一下数组吧，跟数组比较一下就很理解链表这种存储结构了。
 *
 * 2.1回顾数组
 * 数组我们无论是C、Java都会学过：
 *
 * 数组是一种连续存储线性结构，元素类型相同，大小相等
 *
 * 数组的优点： 存取速度快
 *
 * 数组的缺点：
 *
 * 1:事先必须知道数组的长度
 * 2:插入删除元素很慢
 * 3:空间通常是有限制的
 * 4:需要大块连续的内存块
 * 5:插入删除元素的效率很低
 *
 * */

/**
 * 2.2链表说明
 * 看完了数组，回到我们的链表：
 *
 * 链表是离散存储线性结构
 *
 * n个节点离散分配，彼此通过指针相连，每个节点只有一个前驱节点，每个节点只有一个后续节点，首节点没有前驱节点，尾节点没有后续节点。
 *
 * 链表优点： 1:空间没有限制  2:插入删除元素很快
 *
 * 链表缺点： 存取速度很慢
 *
 *
 * 确定一个链表我们只需要头指针，通过头指针就可以把整个链表都能推导出来了～
 *
 * 链表又分了好几类：
 *
 * 单向链表 : 一个节点指向下一个节点
 *
 * 双向链表 : 一个节点有两个指针域
 *
 * 循环链表 ：能通过任何一个节点找到其他所有的节点，将两种(双向/单向)链表的最后一个结点指向第一个结点从而实现循环
 *
 * 操作链表要时刻记住的是 ：节点中指针域指向的就是一个节点了！
 * */
public class Java实现单向链表 {

    /**
     * 三、Java实现链表
     * 算法：
     *
     * - 遍历
     * - 查找
     * - 清空
     * - 销毁
     * - 求长度
     * - 算法排序
     * - 删除节点
     * - 插入节点
     *
     * 首先，我们定义一个类作为节点
     * - 数据域
     * - 指针域
     *
     * 为了操作方便我就直接定义成public了。
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
     * 3.1创建链表(增加节点)
     * 向链表中插入数据：
     *
     * - 找到尾节点进行插入
     *
     * - 即使头节点.next为null，不走while循环，也是将头节点与新节点连接的(我已经将head节点初始化过了，因此没必要判断头节点是否为null)～
     * */
    /**
     * 向链表添加数据
     *
     * @param value 要添加的数据
     */
    public void addData(int value) {

        //初始化要加入的节点
        Node newNode = new Node(value);

        //临时节点
        Node temp = newNode;

        // 找到尾节点
        while (temp.next != null) {
            temp = temp.next;
        }

        // 已经包括了头节点.next为null的情况了～
        temp.next = newNode;

    }

    /**
     * 3.2遍历链表
     * 上面我们已经编写了增加方法，现在遍历一下看一下是否正确～～～
     *
     * 从首节点开始，不断往后面找，直到后面的节点没有数据：
     * */
    /**
     * 遍历链表
     *
     * @param head 头节点
     */
    public static void traverse(Node head) {

        //临时节点，从首节点开始
        Node temp = head.next;

        while (temp != null) {
            System.out.println("--------：" + temp.data);
            //继续下一个
            temp = temp.next;
        }
    }


    /**
     * 3.3插入节点
     *
     * - 插入一个节点到链表中，首先得判断这个位置是否是合法的，才能进行插入～
     * - 找到想要插入的位置的上一个节点就可以了～
     * */
    /**
     * 插入节点
     *
     * @param head  头指针
     * @param index 要插入的位置
     * @param value 要插入的值
     */
    public void insertNode(Node head, int index, int value) {
        //首先需要判断指定位置是否合法，
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("插入位置不合法。");
            return;
        }

        //临时节点，从头节点开始
        Node temp = head;

        //记录遍历的当前位置
        int currentPos = 0;

        //初始化要插入的节点
        Node insertNode = new Node(value);

        while (temp.next != null) {
            //找到上一个节点的位置了
            if ((index - 1) == currentPos) {
                //temp表示的是上一个节点

                //将原本由上一个节点的指向交由插入的节点来指向
                insertNode.next = temp.next;

                //将上一个节点的指针域指向要插入的节点
                temp.next = insertNode;
                return;
            }
            currentPos++;
            temp = temp.next;
        }
    }

    /**
     * 获取链表的长度
     * @param head 头指针
     *
     * 获取链表的长度就很简单了，遍历一下，每得到一个节点+1即可～
     */
    public static int linkListLength(Node head) {
        int length = 0;
        //临时节点，从首节点开始
        Node temp = head.next;

        // 找到尾节点
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 根据位置删除节点
     *
     * @param head  头指针
     * @param index 要删除的位置
     *
     * 删除某个位置上的节点其实是和插入节点很像的， 同样都要找到上一个节点。将上一个节点的指针域改变一下，就可以删除了～
     */
    public static void deleteNode(Node head, int index) {

        //首先需要判断指定位置是否合法，
        if (index < 1 || index > linkListLength(head) + 1) {
            System.out.println("删除位置不合法。");
            return;
        }
        //临时节点，从头节点开始
        Node temp = head;
        //记录遍历的当前位置
        int currentPos = 0;
        while (temp.next != null) {
            //找到上一个节点的位置了
            if ((index - 1) == currentPos) {
                //temp表示的是上一个节点
                //temp.next表示的是想要删除的节点
                //将想要删除的节点存储一下
                Node deleteNode = temp.next;

                //想要删除节点的下一个节点交由上一个节点来控制
                temp.next = deleteNode.next;
                //Java会回收它，设置不设置为null应该没多大意义了(个人觉得,如果不对请指出哦～)
                deleteNode = null;
                return;
            }
            currentPos++;
            temp = temp.next;
        }
    }

    /**
     * 对链表进行排序
     *
     * @param head
     *前面已经讲过了8种的排序算法了【八种排序算法总结】，这次挑简单的冒泡排序吧(其实我是想写快速排序的，尝试了一下感觉有点难…..)
     */
    public static void sortLinkList(Node head) {
        Node currentNode;
        Node nextNode;
        for (currentNode = head.next; currentNode.next != null; currentNode = currentNode.next) {
            for (nextNode = head.next; nextNode.next != null; nextNode = nextNode.next) {
                if (nextNode.data > nextNode.next.data) {
                    int temp = nextNode.data;
                    nextNode.data = nextNode.next.data;
                    nextNode.next.data = temp;
                }
            }
        }
    }

    /**
     * 找到链表中倒数第k个节点(设置两个指针p1、p2，让p2比p1快k个节点，同时向后遍历，当p2为空，则p1为倒数第k个节点
     *
     * @param head
     * @param k    倒数第k个节点
     *
     * 这个算法挺有趣的：设置两个指针p1、p2，让p2比p1快k个节点，同时向后遍历，当p2为空，则p1为倒数第k个节点
     */
    public static Node findKNode(Node head, int k) {
        if (k < 1 || k > linkListLength(head))
            return null;
        Node p1 = head;
        Node p2 = head;

        // p2比怕p1快k个节点
        for (int i = 0; i < k - 1; i++)
            p2 = p2.next;

        // 只要p2为null，那么p1就是倒数第k个节点了
        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1;
    }


    /**
     * 删除链表重复数据(跟冒泡差不多，等于删除就是了)
     *
     * @param head 头节点
     */
    public static void deleteDuplecate(Node head) {
        //临时节点，(从首节点开始-->真正有数据的节点)
        Node temp = head.next;
        //当前节点(首节点)的下一个节点
        Node nextNode = temp.next;
        while (temp.next != null) {
            while (nextNode.next != null) {
                if (nextNode.next.data == nextNode.data) {
                    //将下一个节点删除(当前节点指向下下个节点)
                    nextNode.next = nextNode.next.next;
                } else {
                    //继续下一个
                    nextNode = nextNode.next;
                }
            }
            //下一轮比较
            temp = temp.next;
        }
    }

    /**
     * 查询单链表的中间节点
     *
     * 这个算法也挺有趣的：一个每次走1步，一个每次走两步，走两步的遍历完，然后走一步的指针，那就是中间节点
     */

    public static Node searchMid(Node head) {
        Node p1 = head;
        Node p2 = head;

        // 一个走一步，一个走两步，直到为null，走一步的到达的就是中间节点
        while (p2 != null && p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return p1;
    }


    /**
     * 通过递归从尾到头输出单链表
     *
     * @param head 头节点
     */
    public  static  void printListReversely(Node head) {
        if (head != null) {
            printListReversely(head.next);
            System.out.println(head.data);
        }
    }


    /**
     * 实现链表的反转
     *
     * @param node 链表的头节点
     */
    public static Node reverseLinkList(Node node) {
        Node prev ;
        if (node == null || node.next == null) {
            prev = node;
        } else {
            Node tmp = reverseLinkList(node.next);
            node.next.next = node;
            node.next = null;
            prev = tmp;
        }
        return prev;
    }

    /**
     * 最后
     * 理解链表本身并不难，但做相关的操作就弄得头疼，head.next  next next next ....(算法这方面我还是薄弱啊..脑子不够用了…..)写了两天就写了这么点东西…
     *
     * 操作一个链表只需要知道它的头指针就可以做任何操作了
     *
     * 添加数据到链表中
     * 遍历找到尾节点，插入即可(只要while(temp.next != null)，退出循环就会找到尾节点)
     *
     * 遍历链表
     * 从首节点(有效节点)开始，只要不为null，就输出
     *
     * 给定位置插入节点到链表中
     * 将原本由上一个节点的指向交由插入的节点来指向
     * 上一个节点指针域指向想要插入的节点
     * 首先判断该位置是否有效(在链表长度的范围内)
     * 找到想要插入位置的上一个节点
     *
     * 图片
     * 获取链表的长度
     * 每访问一次节点，变量++操作即可
     *
     * 删除给定位置的节点
     * 将原本由上一个节点的指向后面一个节点
     * 首先判断该位置是否有效(在链表长度的范围内)
     * 找到想要插入位置的上一个节点
     * 图片
     *
     * 对链表进行排序
     * 使用冒泡算法对其进行排序
     *
     * 找到链表中倒数第k个节点
     * 设置两个指针p1、p2，让p2比p1快k个节点，同时向后遍历，当p2为空，则p1为倒数第k个节点
     *
     * 删除链表重复数据
     * 操作跟冒泡排序差不多，只要它相等，就能删除了～
     *
     * 查询链表的中间节点
     * 这个算法也挺有趣的：一个每次走1步，一个每次走两步，走两步的遍历完，然后走一步的指针，那就是中间节点
     *
     * 递归从尾到头输出单链表
     * 只要下面还有数据，那就往下找，递归是从最后往前翻。
     *
     * 反转链表
     * 有递归和非递归两种方式，我觉得是挺难的。可以到我给出的链接上查阅～
     *
     * PS：每个人的实现都会不一样，所以一些小细节难免会有些变动，也没有固定的写法，能够实现功能即可～
     *
     * 参考资料：
     * http://www.cnblogs.com/whgk/p/6589920.html
     * https://www.cnblogs.com/bywallance/p/6726251.html
     * */

}
