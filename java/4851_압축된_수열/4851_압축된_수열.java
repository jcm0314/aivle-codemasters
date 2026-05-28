// Problem: 압축된 수열
// Number: 4851
// Solved: 2026. 5. 28.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {

    public static int getLengthInBase(int num, int base) {
        int len = 0;

        while (num > 0) {
            num /= base;
            len++;
        }

        return len;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int base = 10; base <= 62; base++) {
            int totalSize = N - 1; // 공백 개수

            for (int i = 0; i < N; i++) {
                totalSize += getLengthInBase(arr[i], base);
            }

            if (totalSize <= M) {
                System.out.println(base);
                return;
            }
        }

        System.out.println(-1);
    }
}