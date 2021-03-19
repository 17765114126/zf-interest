package study.数组集合.collections和Arrays;

import java.util.*;

/**
 * @ClassName ArraysTest
 * @Author zhaofu
 * @Date 2019/11/27
 * @Version V1.0
 **/
public class ArraysTest {
    public static void main(String[] args) {
/**
 *     此类包含用来操作数组（比如排序和搜索）的各种方法。此类还包含一个允许将数组作为列表来查看的静态工厂。
 *         除非特别注明，否则如果指定数组引用为 null，则此类中的方法都会抛出 NullPointerException。
 *
 *         1. asList 获取List集合.
 *         1.1 JDK 文档
 *         返回一个受指定数组支持的固定大小的列表。
 *        （对返回列表的更改会“直接写”到数组。）此方法同 Collection.toArray() 一起，充当了基于数组的 API 与基于 collection 的 API 之间的桥梁。
 *         返回的列表是可序列化的，并且实现了 RandomAccess。
 *
 *         1.2 说明
 *         Arrays.asList() 方法返回的是一个固定大小的数组 , 也就是说不可以进行**add **和 remove 操作.否则会产生如下错误 :
 *
 *         Exception in thread "main" java.lang.UnsupportedOperationException
 *         at java.utils.AbstractList.add(AbstractList.java:148)
 *         at java.utils.AbstractList.add(AbstractList.java:108)
 *         at com.wsj.utils.ArraysTest.main(ArraysTest.java:19)
 *  方法原型
 *     public static <T> List<T> asList(T... a)
 *
 *     1.3 示例代码
 */
        List<String> arrays = Arrays.asList("C", "C++", "Java");
        System.out.println("arrays:"+arrays);
        List<Object> arrayList = new ArrayList<>();
        arrayList.add("c");
        arrayList.add("c++");
        arrayList.add("java");
        arrayList.toString();
        System.out.println("arrayList:"+arrayList.toString());

/**
 *  2. sort 数组排序
 *         2.1 JDK 文档
 *         对指定的 char 型数组按数字升序进行排序。该排序算法是一个经过调优的快速排序法 .
 *                 (1) 函数原型
 *
 *  对数组内所有元素进行排序.
 *         public static void sort(char[] a);
 *  对指定范围内的数据进行排序. 包括fromIndex 不包括 toIndex
 *         public static void sort(char[] a,int fromIndex,int toIndex);
 *  其他的类型的...
 *
 *  2.2 示例代码
 */
// 1. 排序所有元素  ,   结果 : [1, 2, 3, 6, 7, 8, 9, 10]
        int[] testArray = {2, 1, 9, 8, 3, 7, 6, 10};
        Arrays.sort(testArray);
        System.out.println("testArray:"+testArray.toString());
        System.out.println("testArray:"+Arrays.toString(testArray));
// 2. 算法排序 [2-5)     ,  结果 :  [2, 1, 3, 8, 9, 7, 6, 10]
        byte[] testArray1 = {2, 1, 9, 8, 3, 7, 6, 10};
        Arrays.sort(testArray1, 2, 5);
        System.out.println("testArray1:"+Arrays.toString(testArray1));
//        Arrays.sort();
/**
 *         3. binarySearch 二分查找
 *         3.1 JDK 文档
 *         使用二分搜索法来搜索指定的 byte 型数组，以获得指定的值。必
 *         须在进行此调用之前对数组进行排序（通过 sort(byte[]方法）。
 *         如果没有对数组进行排序，则结果是不确定的。
 *         如果数组包含多个带有指定值的元素，则无法保证找到的是哪一个。
 *
 *         在所有元素中查找
 *         public static int binarySearch(byte[] a,byte key);
 *         在 [fromIndex , toIndex) 区间内查找.
 *         public static int binarySearch(byte[] a, int fromIndex,int toIndex,byte key);
 *
 *         3.2 示例代码
 */
        int i = Arrays.binarySearch(testArray, 6);
        System.out.println("i"+i);
/**         4. copyOf 数组复制.
 *         4.1 JDK 文档
 *         复制指定的数组，截取或用 0 填充（如有必要），以使副本具有指定的长度。
 *         对于在原数组和副本中都有效的所有索引，这两个数组将包含相同的值。对于在副本中有效而在原数组无效的所有索引，副本将包含 (byte)0。
 *         当且仅当指定长度大于原数组的长度时，这些索引存在。
 *
 *         4.2 函数原型
 *         创建新数组并用原始数组填充.
 *         public static byte[] copyOf(byte[] original,int newLength);
 *
 */
        byte[] bytes = Arrays.copyOf(testArray1, testArray1.length-2);
        System.out.println("bytes"+Arrays.toString(bytes));

/**        5. copyOfRange 复制数组指定范围.
 *         5.1 JDK文档
 *         将指定数组的指定范围复制到一个新数组。
 *         该范围的初始索引 (from) 必须位于 0 和 original.length（包括）之间。
 *         original[from] 处的值放入副本的初始元素中（除非 from == original.length 或 from == to）。
 *         原数组中后续元素的值放入副本的后续元素。
 *         该范围的最后索引 (to) （必须大于等于 from）可以大于 original.length，在这种情况下，
 *         false 被放入索引大于等于 original.length - from 的副本的所有元素中。返回数组的长度为 to - from。
 *
 *         5.2 函数原型
 *         拷贝指定范围的数据.
 *         public static boolean[] copyOfRange(boolean[] original, int from, int to);
 */
        int[] ints = Arrays.copyOfRange(testArray, 1, 6);
        System.out.println("ints:"+Arrays.toString(ints));


 /**       6. equals 数组比较
 *         6.1 JDK文档
 *         如果两个指定的 boolean 型数组彼此相等，则返回 true。
  *        如果两个数组包含相同数量的元素，并且两个数组中的所有相应元素对都是相等的，则认为这两个数组是相等的。
  *        换句话说，如果两个数组以相同顺序包含相同的元素，则两个数组是相等的。
  *        此外，如果两个数组引用都为 null，则认为它们是相等的。
 *
 *         6.2 函数原型
 *         public static boolean equals(boolean[] a,boolean[] a2)
 */
        boolean equals = Arrays.equals(testArray, ints);
        System.out.println("equals:"+equals);

/**
 *         7. fill 数组填充
 *         7.1 JDK 文档
 *         将指定的 boolean 值分配给指定 boolean 型数组指定范围中的每个元素。
 *         填充的范围从索引 fromIndex（包括）一直到索引 toIndex（不包括）。（
 *         如果 fromIndex==toIndex，则填充范围为空。）
 *
 *         7.2 函数原型
 *         用指定的数据填充数组.
 *         public static void fill(boolean[] a,boolean val)
 *         用指定的数据填充指定的区间
 *         public static void fill(boolean[] a,int fromIndex,int toIndex, boolean val);
 */
            int[] testArray3 = {1, 2, 3, 4, 5};
            Arrays.fill(testArray3, 0,2,0);
            System.out.println("testArray3:"+Arrays.toString(testArray3));
 /**
 *         8. toString 转换字符串.
 *         8.1 JDK 文档
 *         返回指定数组内容的字符串表示形式。字符串表示形式由数组的元素列表组成，括在方括号（"[]"）中。相邻元素用字符 ", "（逗号加空格）分隔。
 *        这些元素通过 String.valueOf(double) 转换为字符串。如果 a 为 null，则返回 "null"。
 *
 *         8.2 函数原型
 *         转换成字符串
 *         public static String toString(double[] a)
 *       //8.2 示例代码
 */
        // 输出结果 : [2, 1, 9, 8, 3, 7, 6, 10]
        byte[] testArray2 = {2, 1, 9, 8, 3, 7, 6, 10};
        System.out.println("testArray2:"+Arrays.toString(testArray2));
    }

}
