package test1;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите размер массива (число N): ");
        int n = in.nextInt();

        System.out.println("Введите интервал (число M): ");
        int m = in.nextInt();

        in.close();

        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        System.out.print("Пройденный путь: ");
        int pathIndex = 0;
        do {
            System.out.print(array[pathIndex]);
            pathIndex = (pathIndex + m - 1) % n;
        } while (pathIndex != 0);
    }
}