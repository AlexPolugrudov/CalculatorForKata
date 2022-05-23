
import java.util.Scanner;

public class Main {
    private static final String[] arabicDigitsArray = {"10","9","8","7","6","5","4","3","2","1"};
    private static final String[] romanDigitsArray = {"X","IX","VIII","VII","VI","V","IV","III","II","I"};
    private static CalcForArab calcForArab;
    private static CalcForRomans calcForRomans;

    public static void solution(String s) throws Exception {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (s.contains(arabicDigitsArray[i]) && s.contains(romanDigitsArray[j]))
                    throw new Exception("Используются одновременно разные системы счисления");
            }
        }
        for (int i = 0; i < 10; i++) {
            if (s.contains(romanDigitsArray[i])) {
                calcForRomans = new CalcForRomans(s);
                break;
            } else if(s.contains(arabicDigitsArray[i])) {
                calcForArab = new CalcForArab(s);
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String ourString = scanner.nextLine();
        solution(ourString);
    }
}


//Калькулятор для римских чисел
class CalcForRomans {
    private String str;

    public CalcForRomans(String str) throws Exception {
        this.str = str;
        solutionForRomans(str);
    }
    private static int[] arabArray = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    private static String[] romanArray = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};
    private static String[] operations = {"+", "-", "*", "/"};

    public static void solutionForRomans(String str) throws Exception {
        StringBuilder firstDigit = new StringBuilder();
        StringBuilder secondDigit = new StringBuilder();
        StringBuilder operation = new StringBuilder();

        String[] temp = str.split("");
        int index = 0;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("+") || temp[i].equals("-") || temp[i].equals("*") || temp[i].equals("/"))
                index = i;
        }
        if (index == 0)
            throw new ArithmeticException("Отсутствует арифметический оператор");

        for (int i = 0; i < index; i++) {
            firstDigit.append(temp[i]);
        }
        for (int i = index + 1; i < temp.length; i++) {
            secondDigit.append(temp[i]);
        }
        operation.append(temp[index]);

        String ourString1 = firstDigit.toString();
        String ourString2 = secondDigit.toString();
        String ourOperation = operation.toString();

        int ourDigit1 = roman2int(ourString1);
        int ourDigit2 = roman2int(ourString2);

        String result = int2roman(operation(ourDigit1, ourDigit2, ourOperation));

        System.out.println(result);
    }

    public static int roman2int(String romanDigit) throws Exception {
        for (int i = 0; i < romanArray.length; i++) {
            if (romanDigit.equals(romanArray[i]))
                return arabArray[i];
        }
        throw new Exception("Число должно быть больше 0 и меньше 10");
    }

    public static String int2roman(int number) throws Exception {
        String[] romanNumbers = {"M", "CMXC", "CM", "D", "CDXC", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arab = {1000, 990, 900, 500, 490, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (number > 0 || arab.length == (i - 1)) {
            while ((number - arab[i]) >= 0) {
                number -= arab[i];
                result.append(romanNumbers[i]);
            }
            i++;
        }
        return result.toString();
    }

    public static int operation(int num1, int num2, String op) {
        int result = 0;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                System.out.println("Неверно");
        }
        if (result <= 0)
            throw new ArithmeticException("У римлян не было чисел меньше 1 (ну, в условии так :))");
        return result;
    }
}

//Калькулятор для арабских чисел
class CalcForArab {
    private String str;

    public CalcForArab(String str) throws Exception {
        this.str = str;
        solutionForArab(str);
    }

    public static void solutionForArab(String str) throws Exception {
        StringBuilder firstDigit = new StringBuilder();
        StringBuilder secondDigit = new StringBuilder();
        StringBuilder operation = new StringBuilder();

        String[] temp = str.split("");
        int index = 0;

        for (int i = 0; i < temp.length; i++) {
            if (temp[i].equals("+") || temp[i].equals("-") ||temp[i].equals("*") ||temp[i].equals("/"))
                index = i;
        }
        if (index == 0)
            throw new ArithmeticException("Отсутствует арифметический оператор");

        for (int i = 0; i < index; i++) {
            firstDigit.append(temp[i]);
        }
        for (int i = index + 1; i < temp.length; i++) {
            secondDigit.append(temp[i]);
        }
        operation.append(temp[index]);

        int num1 = Integer.parseInt(firstDigit.toString());
        int num2 = Integer.parseInt(secondDigit.toString());
        String ourOperation = operation.toString();

        int result = operation(num1, num2, ourOperation);

        System.out.println(result);
    }

    public static int operation(int num1, int num2, String op) {
        int result = 0;
        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | IllegalArgumentException e) {
                    System.out.println("Деление на ноль");
                    break;
                }
            default:
                System.out.println("Неверно");
        }

        return result;
    }
}
