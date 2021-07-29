package array;

import base.Student;

public class TestArray {

    public static void main(String[] args) {
        Array<Integer> array = new Array();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(1, 100);
        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        array.removeElement(4);
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);

        Array<Student> students = new Array<>();
        students.addLast(new Student("Alice", 100));
        students.addLast(new Student("Bob", 66));
        students.addLast(new Student("Tom", 88));
        System.out.println(students);
    }
}
