package study.springStudy.Ioc;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

/**
 * @ClassName Ioc
 * @Author zhaofu
 * @Date 2020/10/16
 * @Version V1.0
 * 扫描包生成类的工具
 * 思路就是利用文件名利用Class.forName()得到类的反射再生成实例
 **/
public class ClassScannerUtil {

    public static List<Object> find(String packName, ClassFilter classFilter) throws IOException {
        //获取当前路径
        Enumeration<URL> entity = Thread.currentThread().getContextClassLoader().getResources(packName);
        HashSet<String> classPaths = new HashSet<>();
        ArrayList<Object> classes = new ArrayList<>();
        //拿到处理后的路径，处理前为/..../target/classes
        //处理后为/..../target/classes
        if (entity.hasMoreElements()) {
            String path = entity.nextElement().getPath().substring(1);
            classPaths.add(path);
        }
        //这里跳转到我写的一个把路径下的.class文件生成为类名的方法，后面会讲述
        //set的元素为类名 比如Entity.Student
        Set<String> set = loadClassName(classPaths);
        for (String s : set) {
            try {
                Class<?> c = Class.forName(s);
                //利用过滤器判断需不需要生成实例
                if (classFilter.test(c)) {
                    //这里为了简单使用无参构造器
                    Constructor<?> constructor = c.getConstructor();
                    constructor.setAccessible(true);
                    //将生成的实例加入返回的list集合中
                    classes.add(constructor.newInstance());
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                System.err.println(e.getMessage());
            }
        }
        return classes;
    }

    /**
     * 核心函数loadClassName
     *
     * @param classPaths 路径名集合
     * @return 类名的集合
     */
    private static Set<String> loadClassName(HashSet<String> classPaths) {
        Queue<File> queue = new LinkedList<>();
        HashSet<String> classNames = new HashSet<>();
        //对每一个路径得到对应所有以.class结尾的文件
        classPaths.forEach(p -> {
            //迭代的方法，树的层次遍历
            queue.offer(new File(p));
            while (!queue.isEmpty()) {
                File file = queue.poll();
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (File file1 : files) {
                        queue.offer(file1);
                    }
                } else if (file.getName().endsWith(".class")) {
                    //对文件名处理得到类名
                    // ..../target/classes处理完为  \....\target\classes
                    String replace = p.replace("/", "\\");
                    //对于每个.class文件都是以....\target\classes开头，去掉开头，去掉后缀就是类名了
                    String className = file.getPath()
                            .replace(replace, "")
                            .replace(".class", "").replace("\\", ".");
                    classNames.add(className);
                }
            }
        });
        return classNames;
    }
    /**
     * 好了，现在就可以扫描包了
     */







//    ----------------------------------------

    public static <T> List<Object> findByAnnotation(String packName, Class<T> annotation) throws IOException {
        if (!annotation.isAnnotation()) {
            throw new RuntimeException("it not an annotation" + annotation.getTypeName());
        }
        ClassFilter classFilter = (c) -> c.getAnnotation(annotation) != null;
        return find(packName, classFilter);
    }

}
