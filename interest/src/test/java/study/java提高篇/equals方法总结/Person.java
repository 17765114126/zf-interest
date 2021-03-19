package study.java提高篇.equals方法总结;

/**
 * @ClassName Person
 * @Author zhaofu
 * @Date 2020/7/27
 * @Version V1.0
 **/
public class Person {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof Person) {
            Person p = (Person) object;
            if (p.getName() == null || name == null) {
                return false;
            } else {
                return name.equalsIgnoreCase(p.getName());
            }
        }
        return false;
    }
}
