import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int[] numbers = {0, 0, 0};
    static boolean[] booleans = {false, false, false};

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) numbers[i] = scanner.nextInt();

        updateBooleans(numbers);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (i != j) {
                    updateBooleans(operationWithArray(numbers, i, j));
                }
            }
        }


        printAnswer(booleans);

    }



    private static int countMedian(int[] numbers){
        numbers = Arrays.stream(numbers).sorted().toArray() ;
        return numbers[1];
    }

    private static void updateBooleans(int[] numbers){
        int median = countMedian(numbers);
            for (int i = 0; i < 3; i++){
                if (numbers[i] == median) booleans[i] = true;
            }

    }


    private static void printAnswer(boolean[] isAble){
        for (int i = 0; i < 3; i++){
            System.out.println(isAble[i] ? "YES" : "NO");
        }
    }

    private static int[] operationWithArray(int[] numbers, int i, int j){
        int[] copiedArray = new int[3];
        System.arraycopy(numbers, 0, copiedArray, 0, 3);
        copiedArray[i] = copiedArray[i] - copiedArray[j];
        return copiedArray;
    }

}
