import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class Main {
    public static void main(String[] args ) throws IOException {
        String s="";
        File file = new File("test.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write("10 + 5");
        writer.flush();
        writer.close();
        try(FileReader reader = new FileReader("test.txt"))
        {
            int c;
            while((c=reader.read())!=-1){
                s+=(char)c;
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        Object[] m = Arrays.stream(s.split(" ")).toArray(); // Загоняем значения в массив
        Double a,b;

        try { // Через обработку исключений вычисляем а и в
            a = Double.valueOf(String.valueOf(m[0]));
        } catch (NumberFormatException e) {
            System.out.println("Error! Not number");
            return;
        }
        try {
            b = Double.valueOf(String.valueOf(m[2]));
        } catch (NumberFormatException e) {
            System.out.println("Error! Not number");
            return;
        }
        switch (String.valueOf(m[1])) { // Обрабатываем знак операции, кроме деления
            case "+":
                System.out.println(a + b);
                break;
            case "-":
                System.out.println(a - b);
                break;
            case "*":
                System.out.println(a * b);
                break;
            case "/":
                System.out.println(b != 0 ? a / b:"Error!");
                break;
            default:
                System.out.println("Operation Error!");
        }
    }
}