package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double x = readVariable("значение (x)");
        double n = readVariable("колличество слагаемых (n)");
        double e = readVariable("эпсилон (e)");

        if(!verifyVariableX(x)) {
            return;
        }

        double function = calculateFunction(x);
        double sumOfNTerm = calculateSumOfNTerm(x, (int)n);
        Result resultEpsilonSum = calculateEpsilonSum(x, e);
        Result resultEpsilonSumOn10 = calculateEpsilonSum(x, e / 10);

        printResult(sumOfNTerm, resultEpsilonSum, resultEpsilonSumOn10, function);
    }

    private static double readVariable(String string) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Введите %s: ", string);
        return scanner.nextDouble();
    }

    private static boolean verifyVariableX(double x) {
        if(x < -1 || x > 1) {
            System.out.println("x не подходит, поскольку не принадлежит интервалу (-1;1)");
            return false;
        }
        return true;
    }

    private static void printResult(double sumOfNTerm, Result resultEpsilonSum, Result resultEpsilonSumOn10, double function) {
        System.out.printf("1) Значение функции равно %.5f\n", sumOfNTerm);
        System.out.printf("2) Значение функции при %d слагаемых равно %.5f\n", resultEpsilonSum.n, resultEpsilonSum.sum);
        System.out.printf("3) Значение функции при %d слагаемых равно %.5f\n", resultEpsilonSumOn10.n, resultEpsilonSumOn10.sum);
        System.out.printf("4) Значение функции 1/(1+x)^2 равно %.5f\n", function);
    }

    private static double calculateSumOfNTerm(double x, int n) {
        double r = 1;
        double sum = 0;
        for(int i = 1; i <= n; i++) {
            sum += r;
            r = - r * x * (i + 1) / i;
        }
        return sum;
    }

    private static Result calculateEpsilonSum(double x, double e) {
        double r = 1;
        double sum = 0;
        int n = 0;
        while(Math.abs(sum - (sum + r)) > e) {
            sum += r;
            n++;
            r = - r * x * (n + 1) / n;
        }
        return new Result(n, sum);
    }

    private static double calculateFunction(double x) {
        return 1 / Math.pow(1 + x, 2);
    }
}

