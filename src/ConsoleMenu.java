import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ConsoleMenu {
    private final Scanner in;
    private ComplexMatrix a;
    private ComplexMatrix b;

    public ConsoleMenu(Scanner in) {
        this.in = in;
    }

    public void run() {
        try {
            while (true) {
                printMenu();
                int choice = readInt("Пункт: ", 0);
                System.out.println();
                switch (choice) {
                    case 1:
                        a = readMatrix("A");
                        break;
                    case 2:
                        b = readMatrix("B");
                        break;
                    case 3:
                        try {
                            if (a == null) {
                                System.out.println("Матрица A не задана");
                                break;
                            }
                            if (b == null) {
                                System.out.println("Матрица B не задана");
                                break;
                            }
                            show("Матрица A + B", a.add(b));
                        } catch (MatrixException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            if (a == null) {
                                System.out.println("Матрица A не задана");
                                break;
                            }
                            if (b == null) {
                                System.out.println("Матрица B не задана");
                                break;
                            }
                            show("Матрица A * B", a.multiply(b));
                        } catch (MatrixException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            if (a == null) {
                                System.out.println("Матрица A не задана");
                                break;
                            }
                            if (b == null) {
                                System.out.println("Матрица B не задана");
                                break;
                            }
                            show("Матрица A / B", a.divide(b));
                        } catch (MatrixException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;

                    case 9:
                        show("Матрица A", a);
                        show("Матрица B", b);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Нет такого пункта");
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Ввод закончен. Выход");
        }
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

    private Complex[] readComplexRow(int rowNumber, int cols) {
        while (true) {
            System.out.print("Строка " + rowNumber + " (" + cols * 2 + " чисел вида re im для каждого элемента): ");
            String str = in.nextLine();
            StringTokenizer st = new StringTokenizer(str);
            int complexNum = st.countTokens();
            if (complexNum != cols * 2) {
                System.out.println("Нужно ввести " + cols * 2 + " чисел. Введено: " + complexNum);
                continue;
            }
            try {
                Complex[] row = new Complex[cols];
                for (int i = 0; i < cols; ++i) {
                    double re = parseNumber(st.nextToken());
                    double im = parseNumber(st.nextToken());
                    row[i] = new Complex(re, im);
                }
                return row;
            } catch (NumberFormatException e) {
                System.out.println("Нужны конечные числа");
            }
        }
    }

    private double parseNumber(String token) {
        token = token.replace(',', '.');
        double num = Double.parseDouble(token);
        if (Double.isNaN(num) || Double.isInfinite(num)) {
            throw new NumberFormatException("Число должно быть конечным");
        }
        return num;
    }

    private ComplexMatrix readMatrix(String name) {
        System.out.println("Матрица " + name);
        int rows = readInt("Число строк: ", 1);
        int cols = readInt("Число столбцов: ", 1);
        Complex[][] data = new Complex[rows][];
        for (int i = 0; i < rows; ++i) {
            data[i] = readComplexRow(i + 1, cols);
        }
        return new ComplexMatrix(data);
    }

    private void printMenu() {
        System.out.println("\n-=MENU=-");
        System.out.println("1. Ввести A");
        System.out.println("2. Ввести B");
        System.out.println("3. A + B");
        System.out.println("4. A * B");
        System.out.println("5. A / B");
        // System.out.println("6. Транспонирование");
        // System.out.println("7. Определитель");
        // System.out.println("8. Обратная матрица");
        System.out.println("9. Показать матрицы");
        System.out.println("0. Выход\n");
    }

    private void show(String name, ComplexMatrix m) {
        System.out.println(name);
        if (m == null) {
            System.out.println("Матрица не задана");
        } else {
            System.out.println(m);
        }
    }
}