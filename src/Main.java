import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String s = "";
        File inputFile = new File("input.txt");
        inputFile.createNewFile();
        FileWriter writerInput = new FileWriter(inputFile);
        writerInput.write("15 + 15\n25.3 * 3\nf / 0\n29.9 / 0\n157.8 - 93\n3 / 1\n3659.19 % 74.0001");
        writerInput.flush();
        writerInput.close();
        try (FileReader reader = new FileReader("input.txt")) {
            int c;
            while ((c = reader.read()) != -1) {
                s += (char) c;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        File outputFile = new File("output.txt");
        outputFile.createNewFile();
        FileWriter writerOutput = new FileWriter(outputFile);
        List<String> mTemp = Arrays.stream(s.split("\n")).collect(Collectors.toList()); // Загоняем значения по строчке в массив
        for (int i = 0; i < mTemp.size(); i++) {
            Object[] m = Arrays.stream(mTemp.get(i).split(" ")).toArray(); // Разбираем строку
            writerOutput.write(mTemp.get(i)+" = "); // Записываем исходные данные
            Double a, b;
            try {
                a = Double.valueOf(String.valueOf(m[0]));
            } catch (NumberFormatException e) {
                writerOutput.write("Error! Not number\n");
                continue;
            }
            try {
                b = Double.valueOf(String.valueOf(m[2]));
            } catch (NumberFormatException e) {
                writerOutput.write("Error! Not number\n");
                continue;
            }
            switch (String.valueOf(m[1])) { // Обрабатываем знак операции, кроме деления
                case "+":
                    writerOutput.write((a + b)+"\n");
                    break;
                case "-":
                    writerOutput.write((a - b)+"\n");
                    break;
                case "*":
                    writerOutput.write((a * b)+"\n");
                    break;
                case "/":
                    writerOutput.write(b != 0 ? a / b +"\n": "Error! Division by zero\n");
                    break;
                default:
                    writerOutput.write("Operation Error!\n");
            }
        }
        writerOutput.flush();
        writerOutput.close();
    }
}