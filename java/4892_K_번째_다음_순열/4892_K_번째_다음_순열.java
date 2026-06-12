// Problem: K 번째 다음 순열
// Number: 4892
// Solved: 2026. 6. 12.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int t = 0; t < K; t++) {
            if (!nextPermutation(arr)) {
                // 더 이상 다음 순열이 없으면(내림차순 상태),
                // 대희식 규칙에 따라 다시 오름차순으로 되돌림
                Arrays.sort(arr);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }

    // 표준 next_permutation 구현 (사전순 다음 순열)
    static boolean nextPermutation(int[] a) {
        int n = a.length;

        // 1. 뒤에서부터 a[i] < a[i+1]인 가장 큰 i 찾기
        int i = n - 2;
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }

        // 그런 i가 없으면 마지막 순열(내림차순)
        if (i < 0) return false;

        // 2. 뒤에서부터 a[i]보다 큰 a[j] 찾기
        int j = n - 1;
        while (a[j] <= a[i]) {
            j--;
        }

        // 3. a[i]와 a[j] 스왑
        swap(a, i, j);

        // 4. i+1부터 끝까지 뒤집기
        reverse(a, i + 1, n - 1);

        return true;
    }

    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static void reverse(int[] a, int l, int r) {
        while (l < r) {
            swap(a, l, r);
            l++;
            r--;
        }
    }
}