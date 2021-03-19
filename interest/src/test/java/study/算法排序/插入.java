package study.算法排序;

import java.util.Arrays;

/**
 * @ClassName 插入
 * @Author zhaofu
 * @Date 2021/1/18
 * @Version V1.0
 * <p>
 * 插入排序的基本操作就是将一个数据插入到已经排好序的有序数据中，从而得到一个新的、个数加一的有序数据，算法适用于少量数据的排序，时间复杂度为O(n^2)。
 * 是稳定的排序方法。
 * <p>
 * 将一个数据插入到已经排好序的有序数据中
 * <p>
 * 将要排序的是一个乱的数组int[] arrays = {3, 2, 1, 3, 3};
 * <p>
 * 在未知道数组元素的情况下，我们只能把数组的第一个元素作为已经排好序的有序数据，也就是说，把{3}看成是已经排好序的有序数据
 **/
public class 插入 {

    public static void main(String[] args) {
        /**
         *
         * 第一趟排序
         * 用数组的第二个数与第一个数(看成是已有序的数据)比较
         *
         * 如果比第一个数大，那就不管他
         *
         * 如果比第一个数小，将第一个数往后退一步，将第二个数插入第一个数去
         *
         * */
        int[] arrays = {3, 2, 1, 3, 3};
        int temp;
        if (arrays[1] > arrays[0]) {
            //如果第二个数比第一个数大，直接跟上
        } else {
            //如果第二个数比第一个数小，将第一个数后退一个位置(将第二个数插进去)
            temp = arrays[1];
            arrays[1] = arrays[0];
            arrays[0] = temp;
        }
        System.out.println("array" + Arrays.toString(arrays));
        /**
         * 第二趟排序
         * 用数组的第三个数与已是有序的数据{2,3}(刚才在第一趟排的)比较
         * 如果比2大，那就不管它
         * 如果比2小，那就将2退一个位置，让第三个数和1比较
         * 如果第三个数比1小，那么将1后退一步，将第三个数插入到1的位置上
         * */

        //第二趟排序--------------------
        if (arrays[2] > arrays[1]) {
            //如果第三个数比第二个数大，直接跟上
        } else {
            //如果第三个数比第二个数小，将第二个数往后退一个位置，让第三个数跟第一个数比
            temp = arrays[2];
            arrays[2] = arrays[1];
            //如果第三个数比第一个大，那就插入到第二个数中
            if (temp > arrays[0]) {
                arrays[1] = temp;
            } else {
                //如果第三个数比第一个小，将第三个数插入到第一个数前面
                int swapTemp = arrays[0];
                arrays[0] = temp;
                arrays[1] = swapTemp;
            }
        }
        System.out.println("array" + Arrays.toString(arrays));
        System.out.println("----------------------");
        /**
         *
         * 简化代码
         * 从前两趟排序我们可以摸出的规律：
         *
         * 首先将已排序的数据看成一个整体
         * 一个数组是需要n-1趟排序的，总是用后一位跟已排序的数据比较(第一趟：第二位跟已排序的数据比，第二趟：第三位跟已排序的数据比）
         * 用第三位和已排序的数据比，实际上就是让第三位数跟两个数比较，只不过这两个数是已经排好序的而已。
         * 而正是因为它排好序的，我们可以使用一个循环就可以将我们比较的数据插入进去
         *
         * */
//        //临时变量
//        int temp1;
//        int[] arrays1 = {3, 2, 1, 3, 3};
//        //外层循环控制需要排序的趟数(从1开始因为将第0位看成了有序数据)
//        for (int i = 1; i < arrays1.length; i++) {
//            temp1 = arrays1[i];
//            //如果前一位(已排序的数据)比当前数据要大，那么就进入循环比较[参考第二趟排序]
//            while (arrays1[i - 1] > temp1) {
//                //往后退一个位置，让当前数据与之前前位进行比较
//                arrays1[i] = arrays1[i - 1];
//                //不断往前，直到退出循环
//                i--;
//            }
//            //退出了循环说明找到了合适的位置了，将当前数据插入合适的位置中
//            arrays1[i] = temp1;
//            System.out.println("array1：" + Arrays.toString(arrays1));
//            System.out.println("----------------------");


        /**
         *
         * 上面的代码还缺少了一个条件：如果当前比较的数据比已排序的数据都要小，那么while中的arrays[i - 1]会比0还要小，这会报错的。
         *
         * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: -1
         *     at Main.main(Main.java:61)
         * 我们应该加上一个条件：i>=1时才可以，如果i=1了下次再进去的时候就退出循环，让当前数据插入到[0]的位置上
         *
         * 所以完整的代码是这样的：
         * */

        //临时变量
        int temp2;
        int[] arrays2 = {3, 2, 1, 3, 3};
        //外层循环控制需要排序的趟数(从1开始因为将第0位看成了有序数据)
        for (int i = 1; i < arrays2.length; i++) {
            temp2 = arrays2[i];
            //如果前一位(已排序的数据)比当前数据要大，那么就进入循环比较[参考第二趟排序]
            while (i >= 1 && arrays2[i - 1] > temp2) {
                //往后退一个位置，让当前数据与之前前位进行比较
                arrays2[i] = arrays2[i - 1];
                //不断往前，直到退出循环
                i--;
            }
            //退出了循环说明找到了合适的位置了，将当前数据插入合适的位置中
            arrays2[i] = temp2;
        }
        System.out.println("array2：" + Arrays.toString(arrays2));

        /**
         *
         * 插入排序优化
         * 二分查找插入排序的原理：是直接插入排序的一个变种，区别是：在有序区中查找新元素插入位置时，为了减少元素比较次数提高效率，采用二分查找算法进行插入位置的确定。
         *
         * 参考资料：http://www.cnblogs.com/heyuquan/p/insert-sort.html
         *
         * */
        /**
         *
         * 扩展阅读
         * C语言实现第一种方式:
         *
         *         void InsertSortArray ( int arr[], int n)
         *         {
         *
         *             //int arr[]={2,99,3,1,22,88,7,77,54};
         *             for (int i = 1; i < n; i++)// 循环从第二个数组元素开始
         *             {
         *                 int temp = arr[i];//temp标记为未排序的第一个元素
         *                 while (i >= 0 && arr[i - 1] > temp) //将temp与已排序元素从大到小比较，寻找temp应插入的元素
         *                 {
         *                     arr[i] = arr[i - 1];
         *                     i--;
         *                 }
         *                 arr[i] = temp;
         *             }
         *
         *         }
         * C语言实现第二种方式:
         *
         *         void insert ( int arr[], int n)
         *         {
         *             int key = arr[n];
         *             int i = n;
         *             while (arr[i - 1] > key) {
         *                 arr[i] = arr[i - 1];
         *                 i--;
         *                 if (i == 0)
         *                     break;
         *             }
         *             arr[i] = key;
         *         }
         *
         *         void insertionSort ( int arr[], int n)
         *         {
         *             int i;
         *             for (i = 1; i < n; i++) {
         *                 insert(arr, i);
         *             }
         *         }
         * 测试代码:
         *
         *   main()
         *         {
         *             int arr[] = {99, 2, 3, 1, 22, 88, 7, 77, 54};
         *             int i;
         *             insertionSort(arr, 9);
         *             for (int i = 0; i < 9; i++)
         *                 cout << arr[i] << endl;
         *             return 0;
         *         }
         *
         * */
    }
}


