// Problem: 뮤지컬
// Number: 4901
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

        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = scanner.nextInt();
        }

        // 배우 번호가 1..K 이므로 K+1 크기의 카운트 배열 사용
        int[] cnt = new int[K + 1];
        int distinct = 0; // 현재 구간에 등장한 서로 다른 배우 수

        int left = 0;
        int answer = N; // 최댓값 N으로 초기화

        for (int right = 0; right < N; right++) {
            int actor = a[right];
            if (cnt[actor] == 0) {
                distinct++;
            }
            cnt[actor]++;

            // 모든 배우 1..K가 다 포함되면, left를 최대한 당겨서 최소 길이 만들기
            while (distinct == K && left <= right) {
                // 현재 구간 길이로 정답 갱신
                int len = right - left + 1;
                if (len < answer) {
                    answer = len;
                }

                int leftActor = a[left];
                cnt[leftActor]--;
                if (cnt[leftActor] == 0) {
                    distinct--;
                }
                left++;
            }
        }

        System.out.println(answer);
    }
}