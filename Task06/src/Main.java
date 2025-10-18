interface DataStructure {
    boolean add(String value);
    boolean remove(String value);
    boolean contains(String value);
    void clear();
    boolean isEmpty();
    int size();
    void print();
}

class MyStringArray implements DataStructure {
    private String[] array;
    private int count;

    public MyStringArray() {
        array = new String[8];
        count = 0;
    }

    public boolean add(String value) {
        if (count == array.length) {
            int newSize = (array.length * 3) / 2 + 1;
            String[] newArray = new String[newSize];
            System.arraycopy(array, 0, newArray, 0, count);
            array = newArray;
        }
        array[count++] = value;
        return true;
    }

    public boolean remove(String value) {
        for (int i = 0; i < count; i++) {
            if (array[i].equals(value)) {
                int numMoved = count - i - 1;
                if (numMoved > 0)
                    System.arraycopy(array, i + 1, array, i, numMoved);
                array[--count] = null;
                if (count < array.length / 2 && array.length > 8) {
                    int newSize = array.length / 2;
                    String[] newArray = new String[newSize];
                    System.arraycopy(array, 0, newArray, 0, count);
                    array = newArray;
                }
                return true;
            }
        }
        return false;
    }

    public boolean contains(String value) {
        for (int i = 0; i < count; i++) {
            if (array[i].equals(value)) return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < count; i++) array[i] = null;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void print() {
        for (int i = 0; i < count; i++)
            System.out.println("[" + i + "]: " + array[i]);
    }
}

public class Main {
    public static void testDataStructure(DataStructure ds) {
        System.out.println("Додаємо елементи");
        ds.add("Яблуко");
        ds.add("Груша");
        ds.add("Банан");
        System.out.println("Розмір структури: " + ds.size());
        System.out.println("Список елементів структури:");
        ds.print();
        System.out.println("Чи порожня структура?: " + ds.isEmpty());
        System.out.println("Видаляємо банан. Успішно?: " + ds.remove("Банан"));
        System.out.println("Розмір структури: " + ds.size());
        System.out.println("Список елементів структури:");
        ds.print();
        System.out.println("Очищуємо структуру");
        ds.clear();
        System.out.println("Чи порожня структура?: " + ds.isEmpty());
        System.out.println("Розмір структури: " + ds.size());
        System.out.println("Список елементів структури:");
        ds.print();
        System.out.println("Додаємо елементи");
        ds.add("Яблуко");
        ds.add("Груша");
        ds.add("Помідора");
        ds.add("Банан");
        System.out.println("Чи є помідора? " + ds.contains("Помідора"));
        System.out.println("Чи порожня структура?: " + ds.isEmpty());
        System.out.println("Розмір структури: " + ds.size());
        System.out.println("Список елементів структури:");
        ds.print();
    }

    public static void main(String[] args) {
        MyStringArray arr = new MyStringArray();
        testDataStructure(arr);
    }
}
