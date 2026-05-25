// Problem: 마법의 지팡이
// Number: 4865
// Solved: 2026. 5. 25.
// Language: Java

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine().trim());

        // 입력값 1일 때 예외처리
        if (n == 1) {
            System.out.println(0);
            return;
        }

        int cnt2 = 0, cnt3 = 0, cnt5 = 0;

        while (n % 2 == 0) {
            n /= 2;
            cnt2++;
        }
        while (n % 3 == 0) {
            n /= 3;
            cnt3++;
        }
        while (n % 5 == 0) {
            n /= 5;
            cnt5++;
        }

        if (n != 1) { // 2,3,5 말고 다른 소인수가 남아 있음
            System.out.println(-1);
            return;
        }

        long answer = cnt2 + 2 * cnt3 + 3 * cnt5;
        System.out.println(answer);
    }
}