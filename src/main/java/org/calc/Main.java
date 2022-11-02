package org.calc;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int numFirst, numSecond;
    static char operation;
    static int result;

    public static void main (String[] args) {
        System.out.println("Введите выражение римскими или арабскими цифрами (от 1 до 10 или от I до X)\nДоступные операции: +,-,/,*");
        System.out.print(">> ");
        String userInput = scanner.nextLine();

        String[] userIS = userInput.split(" ");

        if (userIS.length < 2 || userIS.length > 3) {
            throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        else if (isNumeric(userIS[0]) && isNumeric(userIS[2])){
            System.out.printf("Резултат сложения арабских цифр: %d",
                    calculated(Integer.parseInt(userIS[0]), Integer.parseInt(userIS[2]), userIS[1].charAt(0)));

        } else if (!isNumeric(userIS[0]) && !isNumeric(userIS[2])) {
            int res = calculated(romanToNumber(userIS[0]), romanToNumber(userIS[2]), userIS[1].charAt(0));
            if (res < 1){ throw new RuntimeException("Римские цифры не могут быть отрицательными или нулевыми"); }
            System.out.printf("Резултат сложения римских цифр: %s", convertNumToRoman(res));


        } else if (isNumeric(userIS[0]) || isNumeric(userIS[2])){
            throw new RuntimeException("Нельзя работать одновременно с разными системами счисления");
        } else  {
            throw new RuntimeException("Проверьте правильность ввденных данных");
        }


    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9]+");
    }

    private static String convertNumToRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = roman[numArabian];
        return s;
    }


    private static int romanToNumber (String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            } else {
                throw new RuntimeException("Проверьте правильность ввденных данных, число может быть либо не правильно введено, либо меньше 0, либо больше X(10)");
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Неверный формат данных");
        }
    }

    public static int calculated (int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    throw new RuntimeException("В делении разрешены только целые ненулевые числа");
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}