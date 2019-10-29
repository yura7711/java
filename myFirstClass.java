package myFirstPackage;

import java.util.Arrays;

public class myFirstClass {
    public static void main(String[] args) {
            //Задание 1
            System.out.println(checkSumBetween10and20(1, 9));
            System.out.println(checkSumBetween10and20(10, 10));
            System.out.println(checkSumBetween10and20(0, 0));
            System.out.println(checkSumBetween10and20(15, 100));

            //Задание 2
            printPositivOrNegativ(0);
            printPositivOrNegativ(1);
            printPositivOrNegativ(-1);

            //Задание 3
            printLeapYear(4);
            printLeapYear(5);
            printLeapYear(100);
            printLeapYear(400);

            //Задание 4
            int[] myArray = {1,2,3,4,5};
            System.out.println("Сумма элементов массива"+Arrays.toString(myArray)+" = " + sumArray(myArray));

            //Задание 5
            int[] myArray2 = new int[8];
            for (int i = 0, j=1; i <myArray2.length; i++, j+=3) {
                myArray2[i] = j;
            }
            System.out.println(Arrays.toString(myArray2));

            //Задание 6
            int [][] myArray3 = getSquare(5);
            for (int i = 0; i < myArray3.length; i++) {
                System.out.println(Arrays.toString(myArray3[i]));
            }

            //Заданиче 7
            int[] myArray4 = {8,1,5,4,9,2};
            System.out.println(getMaxElemArray(myArray4));
        }

    //Задание 1
    public static boolean checkSumBetween10and20(int firstNumber, int secondNumber) {
        int sum = firstNumber + secondNumber;
        if (sum >= 10 && sum <= 20){
            return true;
        }
        return false;
    }

    //Задание 2
    public static void printPositivOrNegativ(int n){
        if (n >= 0){
            System.out.println("Передано положительное число: " + n);
        }
        else{
            System.out.println("Передано отрицательное число: " + n);
        }
    }

    //Задание 3
    public static void printLeapYear(int year){
        if (year <= 0) {
            System.out.println("Некорректно указан год!");
            return;
        }

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println("Год является високосным: " + year);
        } else {
            System.out.println("Год не является високосным: " + year);
        }
    }

    //Заданиче 4
    public static int sumArray(int[] myArray){
        int sum = 0;
        for (int i = 0; i < myArray.length; i++) {
            sum += myArray[i];
        }
        return sum;
    }

    //Задание 5 в основной функции main

    //Задание 6
    public static int[][] getSquare(int n){
        int[][] myArray = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i==j) {
                    myArray[i][j] = 1;
                }
                else{
                    myArray[i][j] = 0;
                }
            }
        }
        return myArray;
    }

    //Задание 7
    public static int getMaxElemArray(int[] myArray){
        Arrays.sort(myArray);
        return myArray[myArray.length-1];
    }
}
