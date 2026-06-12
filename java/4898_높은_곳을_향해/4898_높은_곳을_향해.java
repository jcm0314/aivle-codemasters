// Problem: 높은 곳을 향해
// Number: 4898
// Solved: 2026. 6. 12.
// Language: Java

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] H = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1; // 땅 -> i번 기둥으로 바로 점프하는 경우
            for (int j = 0; j < i; j++) {
                if (H[j] < H[i]) { // 번호는 이미 j < i 로 보장, 높이만 체크
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}