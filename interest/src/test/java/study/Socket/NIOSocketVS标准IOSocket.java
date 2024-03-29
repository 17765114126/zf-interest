package study.Socket;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class NIOSocketVS标准IOSocket {
//简介
//Java NIO 从 JDK1.4 引入，它提供了与标准 IO 完全不同的工作方式。

//NIO 包（java.nio.*）引入了四个关键的抽象数据类型，它们共同解决传统的 I/O 类中的一些问题。

//Buffer：它是包含数据且用于读写的线形表结构。其中还提供了一个特殊类用于内存映射文件的 I/O 操作。
//Charset：它提供 Unicode 字符串影射到字节序列以及逆影射的操作。
//Channels：包含 socket，file 和 pipe 三种管道，它实际上是双向交流的通道。
//Selector：它将多元异步 I/O 操作集中到一个或多个线程中。

//比较
//数据的读写操作
//标准的 IO 是基于字节流和字符流进行操作的，它不能前后移动流中的数据，而 NIO 是基于通道（Channel）和缓冲区（Buffer）进行操作的，数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中，需要时可以在缓冲区中前后移动所保存的数据。

//非阻塞
//在标准 IO 的 Socket 编程中，套接字的某些操作可能会造成阻塞：accept()方法的调用可能会因为等待一个客户端连接而阻塞，read()方法也可能会因为没有数据可读而阻塞，write()方法在数据没有完全写入时也可能会发生阻塞，阻塞发生时，该线程被挂起，什么也干不了。

//阻塞式网络 IO 的特点
//多线程处理多个连接。每个线程拥有自己的栈空间并且占用一些 CPU 时间。

//每个线程遇在外部未准备好的时候，都会阻塞。
// 阻塞的结果就是会带来大量的上下文切换。
// 且大部分上下文切换可能是无意义的。比如假设一个线程监听一个端口，一天只会有几次请求进来，但是该 cpu 不得不为该线程不断做上下文切换尝试，大部分的切换以阻塞告终。

//NIO 则具有非阻塞的特性，可以通过对 channel 的阻塞行为的配置，实现非阻塞式的信道。
// 在非阻塞情况下，线程在等待连接，写数据等（标准 IO 中的阻塞操作）的同时，也可以做其他事情，这便实现了线程的异步操作。

//非阻塞式网络 IO 的特点
//把整个过程切换成小的任务，通过任务间协作完成。
//由一个专门的线程来处理所有的 IO 事件，并负责分发。
//事件驱动机制：事件到的时候触发，而不是同步的去监视事件。
//线程通讯：线程之间通过 wait,notify 等方式通讯。保证每次上下文切换都是有意义的。减少无谓的进程切换。
//选择器
//Java NIO 引入了选择器的概念，选择器可以监听多个通道的事件（比如：连接打开，数据到达）。
// 因此，单个的线程可以监听多个数据通道，这也是非阻塞 IO 的核心。而在标准 IO 的 Socket 编程中，单个线程则只能在一个端口监听。
}
