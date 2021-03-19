package study.算法排序;

import java.util.Arrays;

/**
 * @ClassName 快速
 * @Author zhaofu
 * @Date 2021/1/19
 * @Version V1.0
 *
 * 快速排序由C. A. R. Hoare在1962年提出。
 * 它的基本思想是：
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 **/
public class 快速 {
    /**
     * 快速排序是面试出现的可能性比较高的，也是经常会用到的一种排序，应该重点掌握。
     * 前面一个章节已经讲了递归了，那么现在来看快速排序就非常简单了。
     *
     * 一、第一趟快速排序
     * 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小
     *
     * 百度百科的话并没有说到重点，更简单的理解是这样的：
     * 在数组中找一个支点(任意),经过一趟排序后，支点左边的数都要比支点小，支点右边的数都要比支点大！
     *
     * 现在我们有一个数组：int arr[]={1,4,5,67,2,7,8,6,9,44};
     * 经过一趟排序之后，如果我选择数组中间的数作为支点：7(任意的)，那么第一趟排序后的结果是这样的：{1,4,5,6,2,7,8,67,9,44}
     * 那么就实现了支点左边的数比支点小，支点右边的数比支点大
     *
     *
     * 二、递归分析与代码实现
     * 现在我们的数组是这样的：{1,4,5,6,2,7,8,67,9,44}，既然我们比7小的在左边，比7大的在右边，
     * 那么我们只要将”左边“的排好顺序，又将”右边“的排好序，那整个数组是不是就有序了？想一想，是不是？
     *
     * 又回顾一下递归：”左边“的排好顺序，”右边“的排好序，跟我们第一趟排序的做法是不是一致的？
     *
     * 只不过是参数不一样：第一趟排序是任选了一个支点，比支点小的在左边，比支点大的在右边。
     * 那么，我们想要”左边“的排好顺序，只要在”左边“部分找一个支点，比支点小的在左边，比支点大的在右边。\
     *
     * …………..
     *
     * 在数组中使用递归依照我的惯性，往往定义两个变量：L和R，L指向第一个数组元素，R指向在最后一个数组元素
     * 递归出口也很容易找到：如果数组只有一个元素时，那么就不用排序了
     *
     * 所以，我们可以写出这样的代码：
     * */
    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 67, 2, 7, 8, 6, 9, 44};
        quickSort(arr, 0, arr.length-1);
        System.out.println("arr:   " + Arrays.toString(arr));
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param L   指向数组第一个元素
     * @param R   指向数组最后一个元素
     */
    public static void quickSort(int[] arr, int L, int R) {
        int i = L;
        int j = R;

        //支点
        int pivot = arr[(L + R) / 2];
        //左右两端进行扫描，只要两端还没有交替，就一直扫描
        while (i <= j) {
            //寻找直到比支点大的数
            while (pivot > arr[i])
                i++;
            //寻找直到比支点小的数
            while (pivot < arr[j])
                j--;
            //此时已经分别找到了比支点小的数(右边)、比支点大的数(左边)，它们进行交换
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        //上面一个while保证了第一趟排序支点的左边比支点小，支点的右边比支点大了。

        //“左边”再做排序，直到左边剩下一个数(递归出口)
        if (L < j)
            quickSort(arr, L, j);
        //“右边”再做排序，直到右边剩下一个数(递归出口)
        if (i < R)
            quickSort(arr, i, R);
    }

    /**
     * 快速排序优化
     * 来源：http://www.cnblogs.com/noKing/archive/2017/11/29/7922397.html
     *
     * 我这里简单概括一下思路，有兴趣的同学可到上面的链接上阅读：
     *
     * 随机选取基准值base(支点随机选取)
     * 配合着使用插入排序(当问题规模较小时，近乎有序时，插入排序表现的很好)
     * 当大量数据，且重复数多时，用三路快排
     * */

    /**
     *扩展阅读
     * 原理都是一样的，在细节上有些变化而已
     *
     * 它是交换完毕后记录支点的角标，然后再劈开两半进行递归调用
     *
     * C语言代码实现：
     *
     *         void QuickSort ( int*arr,int low, int high);
     *         int FindPos ( int*arr,int low, int high);
     *
     *
     *         int FindPos ( int*arr,int low, int high)
     *         {
     *             int val = arr[low];
     *
     *             while (low < high) {
     *                 while (low < high && arr[high] >= val)
     *                     --high;
     *                 arr[low] = arr[high];
     *                 while (low < high && arr[low] <= val)
     *                     ++low;
     *                 arr[high] = arr[low];
     *             }
     *             arr[low] = val;
     *             return low;
     *         }
     *
     *
     *         void QuickSort ( int arr[], int low, int high)
     *         {
     *             int pos;
     *             if (low < high) {
     *                 pos = FindPos(arr, low, high);
     *                 QuickSort(arr, low, pos - 1);//劈两半，左边
     *                 QuickSort(arr, pos + 1, high); //右边
     *             }
     *             return;
     *         }
     *
     *         int main ()
     *         {
     *             int arr[ 6]={ 5, 3, -88, 77, 44, -1 } ;
     *             int i;
     *             QuickSort(arr, 0, 5);
     *             for (i = 0; i < 6; i++)
     *                 printf("%d   ", arr[i]);
     *             printf("\n");
     *             return 0;
     *         }
     * */
}
