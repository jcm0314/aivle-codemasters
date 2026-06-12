// Problem: 주식 투자
// Number: 4902
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
        int K = scanner.nextInt();

        // K가 100의 배수가 아니면 경우의 수 0
        if (K % 100 != 0) {
            System.out.println(0);
            return;
        }

        int t = K / 100; // 합이 t가 되도록 +1/-1 N개 선택

        // x = (N + t) / 2 가 정수이고 0 <= x <= N 이어야 함
        if ((N + t) % 2 != 0) {
            System.out.println(0);
            return;
        }

        int x = (N + t) / 2;
        if (x < 0 || x > N) {
            System.out.println(0);
            return;
        }

        // 경우의 수 = C(N, x)
        long ans = comb(N, x);
        System.out.println(ans);
    }

    // nCk 계산 (n <= 50 정도이므로 long으로 충분)
    static long comb(int n, int k) {
        if (k < 0 || k > n) return 0;
        if (k > n - k) k = n - k; // 대칭성

        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - k + i) / i;
        }
        return res;
    }
}