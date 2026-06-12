// Problem: Trailing Zero - 1
// Number: 4904
// Solved: 2026. 6. 12.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int p = scanner.nextInt();  // 진법
        int n = scanner.nextInt();  // n!

        // p의 소인수 분해: prime -> exponent
        Map<Integer, Integer> factors = factorize(p);

        int answer = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : factors.entrySet()) {
            int prime = entry.getKey();
            int expInP = entry.getValue();

            // n! 안에 prime이 몇 번 곱해져 있는지 계산
            int cntPrimeInFact = countPrimeInFactorial(n, prime);

            // 이 소인수 기준으로 가능한 p의 개수
            int zerosForThisPrime = cntPrimeInFact / expInP;

            answer = Math.min(answer, zerosForThisPrime);
        }

        System.out.println(answer);
    }

    // p <= 100 이므로 간단히 trial division으로 소인수 분해
    static Map<Integer, Integer> factorize(int p) {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        int x = p;
        for (int d = 2; d * d <= x; d++) {
            while (x % d == 0) {
                map.put(d, map.getOrDefault(d, 0) + 1);
                x /= d;
            }
        }
        if (x > 1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        return map;
    }

    // n! 안에 prime 이 몇 번 곱해져 있는지 (지수) 계산
    static int countPrimeInFactorial(int n, int prime) {
        int count = 0;
        int div = prime;

        while (div <= n) {
            count += n / div;
            // div *= prime; 이 때 overflow 걱정은 사실상 없음 (n <= 1000, prime >= 2)
            div *= prime;
        }

        return count;
    }
}