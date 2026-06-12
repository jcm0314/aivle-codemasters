// Problem: 쉬는 시간
// Number: 4938
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    static final int MOD = 998244353;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        // D(0) = 1, D(1) = 0
        // N은 1 이상이므로, 배열은 N까지 필요
        long[] D = new long[N + 2];
        D[0] = 1;
        if (N >= 1) D[1] = 0;

        for (int n = 2; n <= N; n++) {
            long val = (D[n - 1] + D[n - 2]) % MOD;
            val = val * (n - 1) % MOD;
            D[n] = val;
        }

        System.out.println(D[N] % MOD);
    }
}