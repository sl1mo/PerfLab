package test4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите путь к файлу: ");
        String arrayPath = in.nextLine();

        in.close();

        File arrayFile = new File(arrayPath);
        List<Integer> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(arrayFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        int[] array = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }

        Arrays.sort(array);
        int average = array[array.length / 2];
        int counter = 0;
        for (int number : array) {
            counter += Math.abs(number - average);
        }
        System.out.println(counter);
    }
}
