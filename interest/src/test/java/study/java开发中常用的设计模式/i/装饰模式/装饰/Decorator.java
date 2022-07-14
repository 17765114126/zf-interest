package study.java开发中常用的设计模式.i.装饰模式.装饰;

public abstract class Decorator implements Person {

    protected Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void eat() {
        person.eat();
    }
}