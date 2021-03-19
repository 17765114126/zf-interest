package study.算法排序;

import org.junit.Test;


/**
 * @ClassName 二叉树
 * @Author zhaofu
 * @Date 2021/1/19
 * @Version V1.0
 * @url：https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484721&idx=4&sn=a4492e34d1be97546d5ccd419b811e0e&chksm=ebd74430dca0cd26f8b7a08fea3c49bca65bb9d2d7d6be792e46a79c7a6df817c263d59c9bd6&token=1676899695&lang=zh_CN&scene=21#wechat_redirect 首先，我们来讲讲什么是树：
 * 树是一种非线性的数据结构，相对于线性的数据结构(链表、数组)而言，树的平均运行时间更短(往往与树相关的排序时间复杂度都不会高)
 * <p>
 * 在编程的世界中，我们一般把树“倒”过来看，这样容易我们分析：
 * <p>
 * 一般的树是有很多很多个分支的，分支下又有很多很多个分支，如果在程序中研究这个会非常麻烦。
 * 因为本来树就是非线性的，而我们计算机的内存是线性存储的，太过复杂的话我们无法设计出来的。
 * <p>
 * 因此，我们先来研究简单又经常用的---> 二叉树
 * <p>
 * 二叉树的意思就是说：每个节点不能多于有两个儿子，上面的图就是一颗二叉树。
 * <p>
 * 一棵树至少会有一个节点(根节点)
 * <p>
 * 树由节点组成
 * <p>
 * 因此，我们定义树的时候往往是->定义节点->节点连接起来就成了树，而节点的定义就是：
 * 一个数据、两个指针(如果有节点就指向节点、没有节点就指向null)
 **/
public class 二叉树 {

    /**
     * 静态创建二叉树
     * <p>
     * 上面说了，树是由若干个节点组成，节点连接起来就成了树，而节点由一个数据、两个指针组成
     * <p>
     * 因此，创建树实际上就是创建节点，然后连接节点
     */
    public class TreeNode {
        // 左节点(儿子)
        private TreeNode lefTreeNode;
        // 右节点(儿子)
        private TreeNode rightNode;
        // 数据
        private int value;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode getLefTreeNode() {
            return lefTreeNode;
        }

        public void setLefTreeNode(TreeNode lefTreeNode) {
            this.lefTreeNode = lefTreeNode;
        }

        public TreeNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @Test
    public void Test() {
        //根节点-->10
        TreeNode treeNode1 = new TreeNode(10);
        //左孩子-->9
        TreeNode treeNode2 = new TreeNode(9);
        //右孩子-->20
        TreeNode treeNode3 = new TreeNode(20);
        //20的左孩子-->15
        TreeNode treeNode4 = new TreeNode(15);
        //20的右孩子-->35
        TreeNode treeNode5 = new TreeNode(35);

        //根节点的孩子
        treeNode1.setLefTreeNode(treeNode2);
        treeNode1.setRightNode(treeNode3);
        //20结点的孩子
        treeNode3.setLefTreeNode(treeNode4);
        treeNode3.setRightNode(treeNode5);

        preTraverseBTree(treeNode1);

        System.out.println("-------------------");
        System.out.println("getHeight:" + getHeight(treeNode1));
        System.out.println("-------------------");
        System.out.println("getMax:" + getMax(treeNode1));


    }

    /**
     * 遍历二叉树
     * 上面说我们的树创建完成了，那怎么证明呢？？我们如果可以像数组一样遍历它(看它的数据)，那就说明它创建完成了～
     *
     * 值得说明的是：二叉树遍历有三种方式
     *
     * 先序遍历
     * 先访问根节点，然后访问左节点，最后访问右节点(根->左->右)
     *
     * 中序遍历
     * 先访问左节点，然后访问根节点，最后访问右节点(左->根->右)
     *
     * 后序遍历
     * 先访问左节点，然后访问右节点，最后访问根节点(左->右->根)
     *
     * 以上面的二叉树为例：
     *
     * 如果是先序遍历： 10->9->20->15->35
     * 如果是中序遍历： 9->10->15->20->35
     *
     * 可能需要解释地方：访问完10节点过后，去找的是20节点，但20下还有子节点，因此先访问的是20的左儿子15节点。
     * 由于15节点没有儿子了。
     * 所以就返回20节点，访问20节点。最后访问35节点
     *
     * 如果是后序遍历： 9->15->35->20->10
     *
     * 可能需要解释地方：先访问9节点，随后应该访问的是20节点，但20下还有子节点，因此先访问的是20的左儿子15节点。
     * 由于15节点没有儿子了。
     * 所以就去访问35节点，由于35节点也没有儿子了，所以返回20节点，最终返回10节点
     *
     *
     * 一句话总结：先序(根->左->右)，中序(左->根->右)，后序(左->右->根)。
     * 如果访问有孩子的节点，先处理孩子的，随后返回
     *
     * 无论先中后遍历，每个节点的遍历如果访问有孩子的节点，先处理孩子的(逻辑是一样的)
     *
     * 因此我们很容易想到递归
     *
     * 递归的出口就是：当没有子节点了，就返回
     *
     * 因此，我们可以写出这样的先序遍历代码：
     * */

    /**
     * 先序遍历
     *
     * @param rootTreeNode 根节点
     */
    public static void preTraverseBTree(TreeNode rootTreeNode) {
        if (rootTreeNode != null) {
            //访问根节点
            System.out.println(rootTreeNode.getValue());
            //访问左根结
            System.out.println(rootTreeNode.getLefTreeNode().getValue());
            //访问右根结
            System.out.println(rootTreeNode.getRightNode().getValue());
        }
    }


    /**
     * 中序遍历
     *
     * @param rootTreeNode 根节点
     */
    public static void inTraverseBTree(TreeNode rootTreeNode) {
        if (rootTreeNode != null) {
            //访问左根结
            System.out.println(rootTreeNode.getLefTreeNode().getValue());
            //访问根节点
            System.out.println(rootTreeNode.getValue());
            //访问右根结
            System.out.println(rootTreeNode.getRightNode().getValue());
        }
    }

    /**
     * 有意思的是：通过先序和中序或者中序和后序我们可以还原出原始的二叉树，但是通过先序和后序是无法还原出原始的二叉树的
     *
     * 也就是说：通过中序和先序或者中序和后序我们就可以确定一颗二叉树了！
     * */


    /**
     * 二、动态创建二叉树
     * <p>
     * 上面我们是手动创建二叉树的，一般地：都是给出一个数组给你，让你将数组变成一个二叉树，此时就需要我们动态创建二叉树了。
     * <p>
     * 二叉树中还有一种特殊的二叉树：二叉查找树(binary search tree)
     * <p>
     * 定义：当前根节点的左边全部比根节点小，当前根节点的右边全部比根节点大。
     * <p>
     * 明眼人可以看出，这对我们来找一个数是非常方便快捷的
     * <p>
     * 往往我们动态创建二叉树都是创建二叉查找树
     * <p>
     * <p>
     * 动态创建二叉树体验
     * 假设我们有一个数组： int[]arrays={3,2,1,4,5};
     * <p>
     * 那么创建二叉树的步骤是这样的：
     * <p>
     * 首先将3作为根节点
     * 随后2进来了，我们跟3做比较，比3小，那么放在3的左边
     * 随后1进来了，我们跟3做比较，比3小，那么放在3的左边，此时3的左边有2了，因此跟2比，比2小，放在2的左边
     * 随后4进来了，我们跟3做比较，比3大，那么放在3的右边
     * 随后5进来了，我们跟3做比较，比3大，那么放在3的右边，此时3的右边有4了，因此跟4比，比4大，放在4的右边
     * 那么我们的二叉查找树就建立成功了，无论任何一颗子树，左边都比根要小，右边比根要大
     * <p>
     * 代码实现
     * <p>
     * 我们的代码实现也很简单，如果比当前根节点要小，那么放到当前根节点左边，如果比当前根节点要大，那么放到当前根节点右边。
     * <p>
     * 因为是动态创建的，因此我们得用一个类来表示根节点
     */

    public class TreeRoot {

        private TreeNode treeRoot;

        public TreeNode getTreeRoot() {
            return treeRoot;
        }

        public void setTreeRoot(TreeNode treeRoot) {
            this.treeRoot = treeRoot;
        }
    }
    /**比较与根谁大，大的往右边，小的往左边：*/

    /**
     * 动态创建二叉查找树
     *
     * @param treeRoot 根节点
     * @param value    节点的值
     */
    public void createTree(TreeRoot treeRoot, int value) {
        //如果树根为空（第一次访问），将第一个值作为根节点
        if (treeRoot.getTreeRoot() == null) {
            TreeNode treeNode = new TreeNode(value);
            treeRoot.setTreeRoot(treeNode);
        } else {
            //当前树根
            TreeNode tempRoot = treeRoot.getTreeRoot();

            while (tempRoot != null) {
                //当前值大于根值，往右边走
                if (value > tempRoot.getValue()) {
                    //右边没有树根，那就直接插入
                    if (tempRoot.getRightNode() == null) {
                        tempRoot.setRightNode(new TreeNode(value));
                        return;
                    } else {
                        //如果右边有树根，到右边的树根去
                        tempRoot = tempRoot.getRightNode();
                    }
                } else {
                    //左没有树根，那就直接插入
                    if (tempRoot.getLefTreeNode() == null) {
                        tempRoot.setLefTreeNode(new TreeNode(value));
                        return;
                    } else {
                        //如果左有树根，到左边的树根去
                        tempRoot = tempRoot.getLefTreeNode();
                    }
                }
            }
        }
    }

    @Test
    public void Test1() {
        int[] array = {2, 3, 1, 4, 5,};

        //动态创建树
        TreeRoot root = new TreeRoot();
        for (int value : array) {
            createTree(root, value);
        }

        //中序遍历树
        inTraverseBTree(root.getTreeRoot());
        System.out.println("------------------");

        //先序遍历树
        preTraverseBTree(root.getTreeRoot());
        System.out.println("------------------");

    }

    /**
     *三、查询二叉查找树相关
     *
     * 3.1查询树的深度
     * 查询树的深度我们可以这样想：左边的子树和右边的字数比，谁大就返回谁，那么再接上根节点+1就可以了
     * */

    public static int getHeight(TreeNode treeNode){
        if (treeNode == null){
            return 0;
        }else {
            //左边的子树深度
            int left = getHeight(treeNode.getLefTreeNode());
            //右边的子树深度
            int right = getHeight(treeNode.getRightNode());

            int max = left;
            if (right > max){
                max = right;
            }
            return max + 1;
        }
    }

    /**
     * 查询树的最大值
     * 从上面先序遍历二叉查找树的时候，细心的同学可能会发现：中序遍历二叉查找树得到的结果是排好顺序的～
     *
     * 那么，如果我们的二叉树不是二叉查找树，我们要怎么查询他的最大值呢？
     *
     * 可以这样：
     *
     * 左边找最大值->递归
     *
     * 右边找最大值->递归
     *
     *
     *
     * 找出树的最大值
     * @param rootTreeNode
     * */

    public static int getMax(TreeNode rootTreeNode){
        if (rootTreeNode == null){
            return 1;
        }else {
            ////找出左边的最大值
            int left = getMax(rootTreeNode.getLefTreeNode());
            //找出右边最大值
            int right = getMax(rootTreeNode.getRightNode());
            //与当前根节点比较
            int currentRootValue  = rootTreeNode.getValue();

            //假设左边最大
            int max = left;

            if (right > left){
                max = right;
            }
            if (currentRootValue > max){
                max = currentRootValue;
            }
            return max;
        }
    }

    /**
     * 无论是在遍历树、查找深度、查找最大值都用到了递归，递归在非线性的数据结构中是用得非常多的...
     *
     * 树的应用也非常广泛，此篇简单地说明了树的数据结构，高级的东西我也没弄懂，可能以后用到的时候会继续深入..
     * */
}
