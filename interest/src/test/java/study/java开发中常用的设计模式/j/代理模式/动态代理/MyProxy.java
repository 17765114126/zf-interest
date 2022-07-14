package study.java开发中常用的设计模式.j.代理模式.动态代理;


import java.lang.reflect.*;

public class MyProxy {
    //一个接口
    public interface IHello{
        void sayHello();
    }


    //目标类实现接口
    static class Hello implements IHello{
        public void sayHello() {
            System.out.println("sayHello......");
        }
    }


    //自定义代理类需要实现InvocationHandler接口
    static  class HWInvocationHandler implements InvocationHandler {
        //目标对象
        private Object target;
        public HWInvocationHandler(Object target){
            this.target = target;
        }
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("------插入前置通知代码-------------");
            //执行相应的目标方法
            Object rs = method.invoke(target,args);
            System.out.println("------插入后置处理代码-------------");
            return rs;
        }
    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //获取动态代理类
        Class<?> proxyClazz = Proxy.getProxyClass(IHello.class.getClassLoader(),IHello.class);
        //获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor<?> constructor = proxyClazz.getConstructor(InvocationHandler.class);
        //通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        IHello iHello = (IHello) constructor.newInstance(new HWInvocationHandler(new Hello()));
        //通过代理对象调用目标方法
        iHello.sayHello();
    }


//    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//
//        IHello  ihello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),  //加载接口的类加载器
//                new Class[]{IHello.class},      //一组接口
//                new HWInvocationHandler(new Hello())); //自定义的InvocationHandler
//        ihello.sayHello();
//    }
}
