// Problem: 머니 게임
// Number: 4910
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        long N = scanner.nextLong(); // 민서
        long M = scanner.nextLong(); // 윤호

        while (true) {
            if (N == 0 || M == 0) break;

            if (N >= 2 * M) {
                // 민서가 윤호의 2배 이상인 동안 여러 번 빼기
                long k = (N - 2 * M) / (2 * M); // 조건을 깨기 직전까지 가능한 횟수
                if (k <= 0) {
                    N -= 2 * M;
                } else {
                    N -= 2 * M * k;
                }
            } else if (M >= 2 * N) {
                // 윤호가 민서의 2배 이상인 동안 여러 번 빼기
                long k = (M - 2 * N) / (2 * N);
                if (k <= 0) {
                    M -= 2 * N;
                } else {
                    M -= 2 * N * k;
                }
            } else {
                // 더 이상 누구도 상대의 2배 이상이 아니면 종료
                break;
            }
        }

        System.out.println(N + " " + M);
    }
}