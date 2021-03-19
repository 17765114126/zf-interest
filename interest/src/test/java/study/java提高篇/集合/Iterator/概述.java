package study.java提高篇.集合.Iterator;

/**
 * @ClassName 概述
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class 概述 {
    //迭代对于我们搞 Java 的来说绝对不陌生。我们常常使用 JDK 提供的迭代接口进行 Java 集合的迭代。
    //
    //
    //    Iterator iterator = list.iterator();
    //            while(iterator.hasNext()){
    //                String string = iterator.next();
    //                //do something
    //            }
    //迭代其实我们可以简单地理解为遍历，是一个标准化遍历各类容器里面的所有对象的方法类，它是一个很典型的设计模式。Iterator 模式是用于遍历集合类的标准访问方法。它可以把访问逻辑从不同类型的集合类中抽象出来，从而避免向客户端暴露集合的内部结构。 在没有迭代器时我们都是这么进行处理的。如下：
    //
    //对于数组我们是使用下标来进行处理的:
    //
    //
    //    int[] arrays = new int[10];
    //       for(int i = 0 ; i < arrays.length ; i++){
    //           int a = arrays[i];
    //           //do something
    //       }
    //对于 ArrayList 是这么处理的:
    //
    //
    //    List<String> list = new ArrayList<String>();
    //       for(int i = 0 ; i < list.size() ;  i++){
    //          String string = list.get(i);
    //          //do something
    //       }
    //对于这两种方式，我们总是都事先知道集合的内部结构，访问代码和集合本身是紧密耦合的，无法将访问逻辑从集合类和客户端代码中分离出来。同时每一种集合对应一种遍历方法，客户端代码无法复用。 在实际应用中如何需要将上面将两个集合进行整合是相当麻烦的。所以为了解决以上问题， Iterator 模式腾空出世，它总是用同一种逻辑来遍历集合。使得客户端自身不需要来维护集合的内部结构，所有的内部状态都由 Iterator 来维护。客户端从不直接和集合类打交道，它总是控制 Iterator，向它发送”向前”，”向后”，”取当前元素”的命令，就可以间接遍历整个集合。
    //
    //上面只是对 Iterator 模式进行简单的说明，下面我们看看 Java 中 Iterator 接口，看他是如何来进行实现的。
    //
    //一、java.utils.Iterator
    //在 Java 中 Iterator 为一个接口，它只提供了迭代了基本规则，在 JDK 中他是这样定义的：对 collection 进行迭代的迭代器。迭代器取代了 Java Collections Framework 中的 Enumeration。迭代器与枚举有两点不同：
    //
    //1、迭代器允许调用者利用定义良好的语义在迭代期间从迭代器所指向的 collection 移除元素。
    //
    //2、方法名称得到了改进。
    //
    //其接口定义如下：
    //
    //
    //    public interface Iterator {
    //     　　boolean hasNext();
    //     　　Object next();
    //     　　void remove();
    //    }
    //其中：
    //
    //Object next()：返回迭代器刚越过的元素的引用，返回值是 Object，需要强制转换成自己需要的类型
    //
    //boolean hasNext()：判断容器内是否还有可供访问的元素
    //
    //void remove()：删除迭代器刚越过的元素
    //
    //对于我们而言，我们只一般只需使用 next()、hasNext() 两个方法即可完成迭代。如下：
    //
    //
    //    for(Iterator it = c.iterator(); it.hasNext(); ) {
    //     　　Object o = it.next();
    //     　　 //do something
    //    }
    //前面阐述了 Iterator 有一个很大的优点,就是我们不必知道集合的内部结果,集合的内部结构、状态由 Iterator 来维持，通过统一的方法 hasNext()、next() 来判断、获取下一个元素，至于具体的内部实现我们就不用关心了。但是作为一个合格的程序员我们非常有必要来弄清楚 Iterator 的实现。下面就 ArrayList 的源码进行分析分析。
    //
    //二、各个集合的 Iterator 的实现
    //下面就 ArrayList 的 Iterator 实现来分析，其实如果我们理解了 ArrayList、Hashset、TreeSet 的数据结构，内部实现，对于他们是如何实现 Iterator 也会胸有成竹的。因为 ArrayList 的内部实现采用数组，所以我们只需要记录相应位置的索引即可，其方法的实现比较简单。
    //
    //2.1、ArrayList 的 Iterator 实现
    //在 ArrayList 内部首先是定义一个内部类 Itr，该内部类实现 Iterator 接口，如下：
    //
    //
    //    private class Itr implements Iterator<E> {
    //        //do something
    //    }
    //而 ArrayList 的 iterator() 方法实现：
    //
    //
    //    public Iterator<E> iterator() {
    //            return new Itr();
    //        }
    //所以通过使用 ArrayList.iterator() 方法返回的是 Itr() 内部类，所以现在我们需要关心的就是 Itr() 内部类的实现：
    //
    //在 Itr 内部定义了三个 int 型的变量：cursor、lastRet、expectedModCount。其中 cursor 表示下一个元素的索引位置，lastRet 表示上一个元素的索引位置
    //
    //
    //    int cursor;
    //            int lastRet = -1;
    //            int expectedModCount = modCount;
    //从 cursor、lastRet 定义可以看出，lastRet 一直比 cursor 少一所以 hasNext() 实现方法异常简单，只需要判断 cursor 和 lastRet 是否相等即可。
    //
    //
    //    public boolean hasNext() {
    //                return cursor != size;
    //            }
    //对于 next() 实现其实也是比较简单的，只要返回 cursor 索引位置处的元素即可，然后修改 cursor、lastRet 即可，
    //
    //
    //    public E next() {
    //                checkForComodification();
    //                int i = cursor;    //记录索引位置
    //                if (i >= size)    //如果获取元素大于集合元素个数，则抛出异常
    //                    throw new NoSuchElementException();
    //                Object[] elementData =  ArrayList.this.elementData;
    //                if (i >= elementData.length)
    //                    throw new ConcurrentModificationException();
    //                cursor = i + 1;      //cursor + 1
    //                return (E) elementData[lastRet = i];  //lastRet + 1 且返回cursor处元素
    //            }
    //checkForComodification() 主要用来判断集合的修改次数是否合法，即用来判断遍历过程中集合是否被修改过。在 Java 提高篇（二一）—–ArrayList 中已经阐述了。modCount 用于记录 ArrayList 集合的修改次数，初始化为 0，，每当集合被修改一次（结构上面的修改，内部update不算），如 add、remove 等方法，modCount + 1，所以如果 modCount 不变，则表示集合内容没有被修改。该机制主要是用于实现 ArrayList 集合的快速失败机制，在 Java 的集合中，较大一部分集合是存在快速失败机制的，这里就不多说，后面会讲到。所以要保证在遍历过程中不出错误，我们就应该保证在遍历过程中不会对集合产生结构上的修改（当然 remove 方法除外），出现了异常错误，我们就应该认真检查程序是否出错而不是 catch 后不做处理。
    //
    //
    //    final void checkForComodification() {
    //                if (modCount != expectedModCount)
    //                    throw new ConcurrentModificationException();
    //            }
    //对于 remove() 方法的是实现，它是调用 ArrayList 本身的 remove() 方法删除 lastRet 位置元素，然后修改 modCount 即可。
    //
    //
    //    public void remove() {
    //                if (lastRet < 0)
    //                    throw new IllegalStateException();
    //                checkForComodification();
    //
    //                try {
    //                    ArrayList.this.remove(lastRet);
    //                    cursor = lastRet;
    //                    lastRet = -1;
    //                    expectedModCount = modCount;
    //                } catch (IndexOutOfBoundsException ex) {
    //                    throw new ConcurrentModificationException();
    //                }
    //            }
    //这里就对 ArrayList 的 Iterator 实现讲解到这里，对于 Hashset、TreeSet 等集合的 Iterator 实现，各位如果感兴趣可以继续研究，个人认为在研究这些集合的源码之前，有必要对该集合的数据结构有清晰的认识，这样会达到事半功倍的效果！！！
}
