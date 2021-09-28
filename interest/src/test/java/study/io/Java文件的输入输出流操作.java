package study.io;

import org.junit.Test;

import java.io.*;

public class Java文件的输入输出流操作 {

    //在介绍输入输出流之前，首先需要了解如何创建文件，创建文件夹以及遍历文件夹等各种操作，这里面不在一一介绍，主要介绍的是文件的输入输出流操作。
    //
    //在起初学习文件操作之前，总是喜欢将输入输出弄混淆，后来通过看一些书以及一些视频，有了一些自己的心得体会，随后介绍一下，以便在我以后忘记的时候可以温习一下。
    //
    //1.流的概念及分类
    //
    //Java将所有传统的流模型(类或抽象类)，都放在了Java.io包中，用来实现输入输出的功能。
    //
    //这里面我们将流分成两种：输入流和输出流。
    //
    //输入流：只能从中读取数据，而不能向其写入数据。代表：InputStream(字节输入流),Reader(字符输入流)。
    //
    //输出流：只能向其写入数据，而不能从中读取数据。代表：OutputStream(字节输出流),Writer(字符输出流)。
    //
    //流按照操作类型也可以分成两种：
    //
    //字节流 : 字节流可以操作任何数据,因为在计算机中任何数据都是以字节的形式存储的 。它操作的数据单元是8位的字节。
    //
    //字符流 : 字符流只能操作纯字符数据，比较方便。它操作的数据单元是16位的字符。
    //
    //在这个地方，我们需要弄清楚的就是什么是输入，什么是输出。如下图所示：
    //

    //从Java程序指向文件，这个过程我们称为输出或者写入文件，从文件指向Java程序，这个过程我们称为输入或者读出文件。
    //
    //在输入流和输出流中，我们主要掌握4个流文件的操作：InputStream,OutputStream,Reader,Writer。这四个类都是抽象类，前两个代表了字节的输入输出流，后两个代表字符的输入输出流，当我们调用的时候通常使用他们的子类来实现我们的类方法。
    //

    //2.字节输出流(OutputStream)
    //
    //字节输出流它有以下几个方法可以提供给它的子类使用(在输入输出流中，子类几乎没什么方法，都是调用父类的方法)
    //
    //void close(): 关闭此输出流并释放与此流有关的所有系统资源。
    //
    //void write(byte[] b)： 将 b.length 个字节从指定的 byte 数组写入此输出流
    //
    //void write(byte[] b, int off, int len) ：将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此输出流。
    //
    //abstract void write(int b) ： 将指定的字节写入此输出流。
    //
    //由于OutputStream是抽象类，因此我们在向文件写入(输出)字节的时候，需要用到它的子类FileOutputStream,接下来通过使用上面的方法，来实现字节流的输出操作。
    //
    //关于FileOutputStream:
    //
    //(1).写入数据文件，通过使用父类的方法，调用子类的对象
    //
    //(2).FileOutputStream构造方法：
    //
    //作用：绑定输出的输出目的
    //
    //FileOutputStream(File file)
    //
    //创建一个向指定 File 对象表示的文件中写入数据的文件输出流。
    //
    //FileOutputStream(File file, boolean append)
    //
    //创建一个向指定 File 对象表示的文件中写入数据的文件输出流，以追加的方式写入。
    //
    //FileOutputStream(String name)
    //
    //创建一个向具有指定名称的文件中写入数据的输出文件流。
    //
    //FileOutputStream(String name, boolean append)
    //
    //创建一个向具有指定 name 的文件中写入数据的输出文件流，以追加的方式写入。
    //
    //(3).流对象的使用步骤:
    //
    //　　1).创建流子类的对象，绑定数据目的。
    //
    //　　2).调用流对象的方法write写
    //
    //　　3).close释放资源
    //
    //(4).注意事项：流对象的构造方法，可以用来创建文件，如果文件存在的情况下，直接覆盖原文件。
    //
    //接下来通过代码来实现字节输出流的方法：
    //
    //(1).输出单个字节


    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //创建字节输出流的对象fos，并且指定了将直接写到哪里
        FileOutputStream fos = new FileOutputStream("d:\\output.txt");
        //写入一个字节，但是在文件中显示的是ASCII表中对应的值。
        fos.write(97);
        //关闭系统资源
        fos.close();
    }

    //(2).输出字节数组

    @Test
    public void test1() throws IOException {
        // TODO Auto-generated method stub
        //创建字节输出流的对象fos，并且指定了将直接写到哪里
        FileOutputStream fos = new FileOutputStream("d:\\output.txt");
        byte[] b = {97, 98, 99, 100, 101, 102, 103};
        //流对象的方法write写数据
        //将整个数组都写入到文件中
        fos.write(b);
        //写入了数组的一部分，从索引1的位置开始，写入了两个字节
        fos.write(b, 1, 2);
        //关闭资源
        fos.close();
    }


    //3.字节输入流(InputStream)
    //
    //字节输入流它有以下几个方法可以提供给它的子类使用(在输入输出流中，子类几乎没什么方法，都是调用父类的方法)
    //
    //　　* abstract int read() ： * 从输入流中读取数据的下一个字节，返回-1表示文件结束
    //
    //　　* int read(byte[] b) * 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。读入缓冲区的字节总数，如果因为已经到达文件末尾而没有更多的数据，则返回 -1。
    //
    //　　* int read(byte[] b, int off, int len) * 将输入流中最多 len 个数据字节读入 byte 数组。 * void close() * 关闭此输入流并释放与该流关联的所有系统资源。
    //
    //由于InputStream是抽象类，因此我们在从文件读取(输入)字节的时候，需要用到它的子类FileInputStream,接下来通过使用上面的方法，来实现字节流的输入操作。
    //
    //关于FileInputStream:
    //
    //在使用FileInputStream创建对象的时候，我们也需要为这个类绑定数据源(我们要读取的文件名)
    //
    //FileInputStream的构造方法与上述的构造方法相似，里面的参数有两种类型：File类型对象,String类型对象。
    //
    //输入流读取文件的操作步骤：
    //
    //(1).创建字节输入流的子类对象
    //
    //(2).调用读取方法read读取
    //
    //(3).关闭资源
    //
    //注意事项：read()方法每执行一次，就会自动的读取下一个字节。它的返回值是读取到的字节，如果没有字节可以读取了，那么返回-1。
    //
    //下面用代码来实现FileInputStream的操作
    //
    //(1).按照每个字节进行读取(效率相对低)


    ////字节输入流
    @Test
    public void test2() throws IOException {
        FileInputStream fis = new FileInputStream("d:\\output.txt");
        //必须添加这个变量，直接使用fis.read作为输出，因为每次执行read()方法的时候，读取文件字节的指针都会向后移动一位。
        int hasRead = 0;
        //因为hasRead是每个字节的值，当为-1的时候，代表了整个文件都被读取。
        while ((hasRead = fis.read()) != -1) {
            System.out.println(hasRead);
        }
        //关闭文件资源
        fis.close();
    }

    //(2).读取字节数组
    //
    //方法介绍：
    //
    //　　1).int read(byte[] b):从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。读入缓冲区的字节总数，如果因为已经到达文件末尾而没有更多的数据，则返回 -1。
    //
    //　　2).int read(byte[] b,int off,int len):将输入流中最多 len 个数据字节读入 byte 数组。
    //
    //代码实现：

    ////字节输入流

    @Test
    public void test3() throws IOException {
        FileInputStream fis = new FileInputStream("d:\\output.txt");
        //创建字节数组，相当于每次从文件中读取1024个字节保存到这个数据中。这里面一般都写1024
        byte[] b = new byte[1024];
        //必须添加这个变量，直接使用fis.read作为输出，因为每次执行read()方法的时候，读取文件字节的指针都会向后移动一位。
        int len = 0;
        //因为hasRead是每个字节的值，当为-1的时候，代表了整个文件都被读取。
        while ((len = fis.read(b)) != -1) {
            System.out.print(len);
        }
        fis.close();
    }
    //最终的答案跟上述的结果相同，但是使用数组的执行效率比单个字节的效率高很多。

    //4.字符输出流(Writer)
    //
    //由于Writer也是一个抽象类，因此我们用它的子类FileWriter来实现字符流的输出操作。
    //
    //关于FileWriter:
    //
    //(1).方法介绍：
    //
    //　　void write(int c):写入单个字符。
    //
    //　　void write(String str):写入字符串。
    //
    //　　void write(String str, int off, int len):写入字符串的某一部分。
    //
    //　　void write(char[] cbuf):写入字符数组。
    //
    //　　abstract void write(char[] cbuf, int off, int len):写入字符数组的某一部分。

    @Test
    public void test5() throws IOException {
        // TODO Auto-generated method stub
        FileWriter fw = new FileWriter("d://filewriter.txt");
        fw.write("123");
        //必须要加fw.flush()，代表刷新，不加这个语句不能写入到文件中
        fw.flush();
        fw.close();
    }

    //5.字符输入流(Reader)
    //
    //由于Reader也是一个抽象类，因此我们用它的子类FileReader来实现字符流的输出操作。
    //
    // (1)方法介绍：
    //
    //　　 int read() * 读取单个字符
    //
    //　　 int read(char[] cbuf) * 将字符读入数组
    //
    //　　 abstract int read(char[] cbuf, int off, int len) * 将字符读入数组的某一部分。

    @Test
    public void test6() throws IOException {
        // TODO Auto-generated method stub
        FileReader fr = new FileReader("d://output.txt");
        int hasRead = 0;
        while ((hasRead = fr.read()) != -1) {
            System.out.print((char) hasRead);
        }
        fr.close();
    }

    // 字符流的输入输出与字节流的差不多，但是字符流只能操作文本文件，不能操作除了文本文件以外的文件，而字节流可以操作文本文件以及音乐，视频等文件，因此在平时的IO流中，我们使用字节流的操作更多一些。
    //
    //6.文件的复制操作。(这里面我使用字节流的方式实现)
    //
    //文件的复制操作简单的说就是将一个文件上的内容读取出来，并且将读取到的内容写入到另一个文件上，因此在文件的复制操作中，需要我们使用文件的输入和输出流操作。
    //
    //注意事项：在复制文件的过程中，我们需要先读后写。
    @Test
    public void test7() throws IOException {
        // TODO Auto-generated method stub
        //文件复制，先读后写

        FileInputStream fis = new FileInputStream("d://output.txt");
        FileOutputStream fos = new FileOutputStream("d://1.txt");
        int hasRead = 0;
        while ((hasRead = fis.read()) != -1) {
            fos.write(hasRead);
        }

        fos.close();
        fis.close();
    }
    //最终结果，将input.txt文件中的数据复制到了1.txt文件中。

}
