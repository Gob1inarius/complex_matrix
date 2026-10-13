import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner in;
    private ComplexMatrix a;
    private ComplexMatrix b;

    public ConsoleMenu(Scanner in) {
        this.in = in;
    }

    public void run() {
        int n = readInt("Число: ", 1);
        System.out.println("Получено: " + n);
    }

    private int readInt(String message, int min) {
        while (true) {
            System.out.print(message);
            String str = in.nextLine().trim();
            try {
                int num = Integer.parseInt(str);
                if (num >= min) {
                    return num;
                } else {
                    System.out.println("Число должно быть не меньше " + min);
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число");
            }
        }
    }
}