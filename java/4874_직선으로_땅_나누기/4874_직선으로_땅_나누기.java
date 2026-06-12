// Problem: 직선으로 땅 나누기
// Number: 4874
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int L = 0;
        while (true) {
            long regions = 1L + (long)L * (L + 1) / 2; // R(L)
            if (regions >= N) {
                System.out.println(L);
                break;
            }
            L++;
        }
    }
}