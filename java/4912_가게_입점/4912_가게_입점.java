// Problem: 가게 입점
// Number: 4912
// Solved: 2026. 6. 12.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        long L = scanner.nextLong();
        long R = scanner.nextLong();

        long total = 0;

        // A = S = B + C, A 범위가 [L, R] 이므로 S도 [L, R]
        for (long S = L; S <= R; S++) {
            // B 범위: L <= B <= R
            // 그리고 L <= S - B <= R  ->  S - R <= B <= S - L
            long lower = Math.max(L, S - R);
            long upper = Math.min(R, S - L);

            if (lower <= upper) {
                total += (upper - lower + 1);
            }
        }

        System.out.println(total);
    }
}