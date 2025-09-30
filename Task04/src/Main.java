// Інтерфейс
interface Promotable {
    void promote();
}

// Базовий клас
class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void work() {
        System.out.println(name + " is working as an employee.");
    }
}

// Підклас Manager
class Manager extends Employee implements Promotable {

    public Manager(String name, double salary) {
        super(name, salary);
    }

    @Override
    public void work() {
        System.out.println(name + " is managing the team.");
    }

    @Override
    public void promote() {
        System.out.println(name + " has been promoted to Senior Manager!");
    }
}

// Підклас Developer
class Developer extends Employee implements Promotable {

    public Developer(String name, double salary) {
        super(name, salary);
    }

    @Override
    public void work() {
        System.out.println(name + " is writing code.");
    }

    @Override
    public void promote() {
        System.out.println(name + " has been promoted to Senior Developer!");
    }
}

// Тестування
public class Main {
    public static void main(String[] args) {
        Employee e1 = new Manager("Olena", 5000);
        Employee e2 = new Developer("Andrii", 4000);

        // Поліморфізм при виклику work()
        e1.work(); // Manager версія
        e2.work(); // Developer версія

        // Використання інтерфейсу Promotable
        Promotable p1 = (Promotable) e1;
        Promotable p2 = (Promotable) e2;

        p1.promote();
        p2.promote();
    }
}

