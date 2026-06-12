// Problem: 사내망 접속 기록
// Number: 4903
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();

        int K = scanner.nextInt();
        int[] log = new int[K];
        for (int i = 0; i < K; i++) {
            log[i] = scanner.nextInt();
        }

        boolean ok = true;
        for (int i = 0; i < K - 1; i++) {
            int u = log[i];
            int v = log[i + 1];

            if (!canMove(u, v)) {
                ok = false;
                break;
            }
        }

        System.out.println(ok ? "YES" : "NO");
    }

    // u에서 v로 한 번에 이동 가능한지
    static boolean canMove(int u, int v) {
        if (u == v) return true; // 같은 컴퓨터에 머무는 건 허용되는 접속으로 보지 않는다면 이 줄을 제거

        // 0 <-> 2차 허브
        if (u == 0) {
            return isHub(v);
        }
        if (v == 0) {
            return isHub(u);
        }

        // 둘 다 0이 아닐 때: 같은 블록이면 서로 모두 연결
        return sameBlock(u, v);
    }

    // x가 2차 허브 번호인지 (각 블록의 첫 컴퓨터)
    static boolean isHub(int x) {
        if (x <= 0 || x > N * M) return false;
        // x가 1, M+1, 2M+1, ... 인지 확인
        return ((x - 1) % M) == 0;
    }

    // 같은 블록(같은 허브 그룹)에 속하는지
    static boolean sameBlock(int a, int b) {
        if (a <= 0 || b <= 0) return false;
        int blockA = (a - 1) / M;
        int blockB = (b - 1) / M;
        return blockA == blockB;
    }
}