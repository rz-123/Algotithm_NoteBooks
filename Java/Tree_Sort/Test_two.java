package Tree_Sort;

public class Test_two {

    // 输出二进制
    public static void printBinary(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print( (num & ( 1 << i ) ) == 0 ? "0" : "1" ) ;
        }
        System.out.println( );
    }

    public static void main(String[] args) {

        int i = 0b11110000000000000000000000000000;
        printBinary(i);
        printBinary(i << 2);   // 11000000000000000000000000000000
        printBinary(i >>> 2);  // 00111100000000000000000000000000
        printBinary( i >> 2);  // 11111100000000000000000000000000

        int n = 16;
        System.out.println(n << 2);  // 64
        System.out.println(n >> 2);  // 4
    }

}
