// Problem: 곰팡이
// Number: 4918
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static final long MOD = 1000000007L;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        long N = scanner.nextLong(); // 1 <= N <= 1e12

        // 곰팡이 총 수 = Fibonacci(N+1)
        long result = fib(N + 1);

        System.out.println(result % MOD);
    }

    // 행렬 거듭제곱으로 피보나치 계산
    // fib(0) = 0, fib(1) = 1
    private static long fib(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long[][] base = {
                {1, 1},
                {1, 0}
        };

        long[][] res = matrixPower(base, n - 1);

        // [0][0] * F1 + [0][1] * F0 = res[0][0] * 1 + res[0][1] * 0
        return res[0][0] % MOD;
    }

    private static long[][] matrixPower(long[][] base, long exp) {
        long[][] result = {
                {1, 0},
                {0, 1}
        }; // identity

        long[][] cur = {
                {base[0][0], base[0][1]},
                {base[1][0], base[1][1]}
        };

        while (exp > 0) {
            if ((exp & 1L) != 0) {
                result = multiply(result, cur);
            }
            cur = multiply(cur, cur);
            exp >>= 1;
        }

        return result;
    }

    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[2][2];

        c[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        c[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        c[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        c[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;

        return c;
    }
}