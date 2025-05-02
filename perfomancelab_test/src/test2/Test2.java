package test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите путь к файлу с параметрами круга: ");
        String circlePath = in.nextLine();

        System.out.println("Введите путь к файлу с параметрами точек: ");
        String pointsPath = in.nextLine();

        in.close();

        File circleFile = new File(circlePath);
        File pointsFile = new File(pointsPath);

        Map<String, Double> circle = new LinkedHashMap<>();
        List<double[]> points = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(circleFile))) {
            String[] line = reader.readLine().split(" ");

            circle.put("X", Double.parseDouble(line[0]));
            circle.put("Y", Double.parseDouble(line[1]));
            circle.put("R", Math.pow(Double.parseDouble(reader.readLine()), 2));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        for (String key : circle.keySet()) {
            System.out.println(key + " : " + circle.get(key));
        }
        System.out.println("\n");

        try (BufferedReader reader = new BufferedReader(new FileReader(pointsFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                double[] point = new double[]{
                        Double.parseDouble(line.split(" ")[0]),
                        Double.parseDouble(line.split(" ")[1])
                };
                points.add(point);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        for(double[] point : points) {
            System.out.println(point[0] + " " + point[1]);
        }
        System.out.println("\n");

        List<Integer> positionList = new LinkedList<>();

        for (double[] point : points) {
            double diff = Math.pow(point[0] - circle.get("X"), 2) + Math.pow(point[1] - circle.get("Y"), 2) - circle.get("R");
            if (diff == 0){
                positionList.add(0);
            } else if (diff < 0){
                positionList.add(1);
            } else {
                positionList.add(2);
            }
        }

        for(int i : positionList){
            System.out.println(i);
        }
    }
}
