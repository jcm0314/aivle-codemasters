// Problem: 소수를 세자
// Number: 4884
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int K = scanner.nextInt();
        int[] A = new int[K];
        for (int i = 0; i < K; i++) {
            A[i] = scanner.nextInt();
        }

        int pos = 0;          // A에서 현재 매칭할 인덱스
        int answer = -1;

        // 소수들을 순서대로 생성하면서 A를 부분수열로 매칭
        for (int p = 2; ; p++) {
            if (isPrime(p)) {
                String s = Integer.toString(p);
                for (int i = 0; i < s.length() && pos < K; i++) {
                    int digit = s.charAt(i) - '0';
                    if (digit == A[pos]) {
                        pos++;
                        if (pos == K) {
                            answer = p; // 마지막 매칭이 포함된 소수
                            break;
                        }
                    }
                }
                if (answer != -1) break;
            }
        }

        System.out.println(answer);
    }

    static boolean isPrime(int x) {
        if (x < 2) return false;
        if (x == 2) return true;
        if (x % 2 == 0) return false;
        int r = (int)Math.sqrt(x);
        for (int i = 3; i <= r; i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }
}