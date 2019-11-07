package com.geekbrains.training.lesson4.exceptions;

public class ArrayExceptions extends RuntimeException{
    public ArrayExceptions(String message) {
        super(message);
    }
}

package com.geekbrains.training.lesson4.exceptions;

public class MyArraySizeException extends ArrayExceptions{
    public MyArraySizeException() {
        super("Неверный размер массива");
    }
}


package com.geekbrains.training.lesson4.exceptions;

public class MyArrayDataException extends ArrayExceptions {
    public MyArrayDataException(int i, int j) {
        super("Ошибка преобразования элемента массива к int в ячейке i="+i+", j="+j);
    }
}


package com.geekbrains.training.lesson4.exceptions;

public class MainApp {
    public static void main(String[] args) {
        String[][] myArray = {{"1","1","1","1"}
                             ,{"1","1","1","1"}
                             ,{"1","1k","1","1"}
                             ,{"1","1","1","1"}
                             };

        int sum = 0;
        try {
            sum = getSumArrayToInt4x4(myArray);
            System.out.println(sum);
        }
        catch (ArrayExceptions e){
            System.out.println(e.getMessage());
        }
    }

    public static int getSumArrayToInt4x4(String[][] strArray){
        if (strArray.length != 4){
            throw new MyArraySizeException();
        }

        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].length != 4){
                throw new MyArraySizeException();
            }
        }

        int sum = 0;
        int i, j = 0;
        for (int k = 0; k < strArray.length*strArray[0].length; k++) {
            j = k%4;
            i = k/4;

            try {
                sum += Integer.parseInt(strArray[i][j]);
            }
            catch (NumberFormatException e){
                throw new MyArrayDataException(i,j);
            }
        }
        return sum;
    }
}
