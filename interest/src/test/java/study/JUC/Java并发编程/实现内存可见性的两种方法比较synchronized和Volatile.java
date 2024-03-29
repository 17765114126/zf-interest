package study.JUC.Java并发编程;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 实现内存可见性的两种方法比较synchronized和Volatile {
//在《synchronized 的另个一重要作用：内存可见性》这篇文中，讲述了通过同步实现内存可见性的方法，在《Volatile 关键字（上）》这篇文中，讲述了通过 volatile 变量实现内存可见性的方法，这里比较下二者的区别。
//
//volatile 变量是一种稍弱的同步机制在访问 volatile 变量时不会执行加锁操作，因此也就不会使执行线程阻塞，因此 volatile 变量是一种比 synchronized 关键字更轻量级的同步机制。
//从内存可见性的角度看，写入 volatile 变量相当于退出同步代码块，而读取 volatile 变量相当于进入同步代码块。
//在代码中如果过度依赖 volatile 变量来控制状态的可见性，通常会比使用锁的代码更脆弱，也更难以理解。仅当 volatile 变量能简化代码的实现以及对同步策略的验证时，才应该使用它。一般来说，用同步机制会更安全些。
//加锁机制（即同步机制）既可以确保可见性又可以确保原子性，而 volatile 变量只能确保可见性，原因是声明为 volatile 的简单变量如果当前值与该变量以前的值相关，那么 volatile 关键字不起作用，也就是说如下的表达式都不是原子操作：count++、count = count+1。
//当且仅当满足以下所有条件时，才应该使用 volatile 变量：
//
//对变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值。
//该变量没有包含在具有其他变量的不变式中。
//总结：在需要同步的时候，第一选择应该是 synchronized 关键字，这是最安全的方式，尝试其他任何方式都是有风险的。尤其在、jdK1.5 之后，对 synchronized 同步机制做了很多优化，如：自适应的自旋锁、锁粗化、锁消除、轻量级锁等，使得它的性能明显有了很大的提升。
}
