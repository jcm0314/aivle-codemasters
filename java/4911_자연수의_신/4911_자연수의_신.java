// Problem: 자연수의 신
// Number: 4911
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        long N = scanner.nextLong();
        long K = scanner.nextLong();

        long oddCount = (N + 1) / 2; // 홀수 개수

        long result;
        if (K <= oddCount) {
            // K번째 홀수
            result = 2 * K - 1;
        } else {
            // 짝수 부분에서 (K - oddCount)번째 짝수
            long idx = K - oddCount;
            result = 2 * idx;
        }

        System.out.println(result);
    }
}