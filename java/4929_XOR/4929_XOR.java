// Problem: XOR
// Number: 4929
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
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        long count = 0;

        int total = 1 << N; // 2^N

        // 0 : 공집합, 1~(2^N-1) : 부분집합
        for (int mask = 0; mask < total; mask++) {
            int xor = 0;
            int cnt = 0;

            // 각 비트 확인
            for (int i = 0; i < N; i++) {
                if ((mask & (1 << i)) != 0) {
                    xor ^= arr[i];
                    cnt++;
                }
            }

            // 두 개 이상의 정수를 선택한 경우만, XOR == 0인지 확인
            if (cnt >= 2 && xor == 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}