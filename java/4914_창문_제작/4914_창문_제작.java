// Problem: 창문 제작
// Number: 4914
// Solved: 2026. 6. 12.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        long N = scanner.nextLong();
        long a = scanner.nextLong();
        long b = scanner.nextLong();

        // a, b, N >= 2 (문제에서 보장), long으로 계산
        long den = a - 1;                 // 분모
        long num = N * (b + 1) - 1;       // 분자

        long x = (num + den - 1) / den;   // 올림 나눗셈

        long result = x + N;              // 거래 1 x번 + 거래 2 N번

        System.out.println(result);
    }
}