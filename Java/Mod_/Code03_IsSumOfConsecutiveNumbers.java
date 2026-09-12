package Mod_;

//
public class Code03_IsSumOfConsecutiveNumbers {

    public static boolean is1(int num) {
        for (int start = 1, sum; start <= num; start++) {
            sum = start;
            for (int j = start + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    public static boolean is2(int num) {
        return (num & (num - 1)) != 0;
    }

    public static void main(String[] args) {
        for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + (is1(num) ? "T" : "F"));
        }
    }
}
