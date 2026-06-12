// Problem: 부가가치세
// Number: 4907
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        long T = scanner.nextLong();  // 물건의 총액

        // S는 대략 10T/11 근처
        long approxS = (10L * T) / 11L;

        long answerS = -1;
        long answerV = -1;

        // 근처 범위 탐색 (±100 정도면 매우 넉넉)
        for (long S = Math.max(1, approxS - 200); S <= approxS + 200; S++) {
            long V = S / 10;     // floor(S/10)
            if (S + V == T) {
                answerS = S;
                answerV = V;
                break;          // 문제에서 해가 존재하면 유일하므로 바로 종료
            }
        }

        if (answerS == -1) {
            System.out.println(-1);
        } else {
            System.out.println(answerS + " " + answerV);
        }
    }
}