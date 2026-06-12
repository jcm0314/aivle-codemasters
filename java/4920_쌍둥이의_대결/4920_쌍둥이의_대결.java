// Problem: 쌍둥이의 대결
// Number: 4920
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
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }
        int M = scanner.nextInt();

        // 모든 구간 곱의 mod M 값을 저장
        int totalIntervals = N * (N + 1) / 2;
        int[] vals = new int[totalIntervals];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            long cur = 1L;
            for (int j = i; j < N; j++) {
                cur = (cur * A[j]) % M;
                vals[idx++] = (int) cur;
            }
        }

        // 내림차순 정렬
        Arrays.sort(vals);
        // Arrays.sort는 오름차순이므로, 뒤에서부터 읽거나,
        // 혹은 별도 비교자로 Integer 배열로 정렬할 수도 있음

        long scoreK = 0; // 기성이 (선공)
        long scoreS = 0; // 기승이 (후공)

        // 가장 큰 값부터 번갈아 가져가기
        boolean turnK = true; // true: 기성이 차례, false: 기승이 차례
        for (int i = totalIntervals - 1; i >= 0; i--) {
            if (turnK) {
                scoreK += vals[i];
            } else {
                scoreS += vals[i];
            }
            turnK = !turnK;
        }

        long result = scoreK - scoreS;
        System.out.println(result);
    }
}