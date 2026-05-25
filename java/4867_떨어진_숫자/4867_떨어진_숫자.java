// Problem: 떨어진 숫자
// Number: 4867
// Solved: 2026. 5. 25.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String original = br.readLine().trim(); // 떨어뜨린 수
        String picked   = br.readLine().trim(); // 주워 담은 수

        // 길이가 다르면 바로 NO
        if (original.length() != picked.length()) {
            System.out.println("NO");
            return;
        }

        // 0~9 각 숫자의 등장 횟수 배열
        int[] freqA = new int[10];
        int[] freqB = new int[10];

        // 각 문자열에서 숫자 등장 횟수 세기
        for (int i = 0; i < original.length(); i++) {
            freqA[original.charAt(i) - '0']++;
            freqB[picked.charAt(i) - '0']++;
        }

        // 등장 횟수 비교
        for (int d = 0; d < 10; d++) {
            if (freqA[d] != freqB[d]) {
                System.out.println("NO");
                return;
            }
        }

        // 모두 같으면 YES
        System.out.println("YES");
    }
}