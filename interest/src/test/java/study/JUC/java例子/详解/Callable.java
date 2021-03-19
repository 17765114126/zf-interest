package study.JUC.java例子.详解;
/**
 *
 *     （3）通过Callable和Future创建线程
 *
 * 通过Callable和Future创建线程的具体步骤和具体代码如下：
 *
 *    • 创建Callable接口的实现类，并实现call()方法，该call()方法将作为线程执行体，并且有返回值。
 *    • 创建Callable实现类的实例，使用FutureTask类来包装Callable对象，该FutureTask对象封装了该Callable对象的call()方法的返回值。
 *    • 使用FutureTask对象作为Thread对象的target创建并启动新线程。
 *    • 调用FutureTask对象的get()方法来获得子线程执行结束后的返回值其中，Callable接口(也只有一个方法)定义如下：
 *
 * */
public interface Callable   {
     //call() throws Exception;
}
/**  步骤1：创建实现Callable接口的类SomeCallable(略);
          步骤2：创建一个类对象：
          Callable oneCallable = new SomeCallable();
          步骤3：由Callable创建一个FutureTask对象：
          FutureTask oneTask = new FutureTask(oneCallable);
          注释： FutureTask是一个包装器，它通过接受Callable来创建，它同时实现了 Future和Runnable接口。
          步骤4：由FutureTask创建一个Thread对象：
          Thread oneThread = new Thread(oneTask);
          步骤5：启动线程：
          oneThread.start(); */
