// Problem: 묵찌빠봇
// Number: 4861
// Solved: 2026. 5. 28.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    // a, b: 1=가위, 2=바위, 3=보
    // return 0: 비김, 1: a 승, 2: b 승
    static int rps(int a, int b) {
        if (a == b) return 0;
        if ((a == 1 && b == 3) ||
            (a == 2 && b == 1) ||
            (a == 3 && b == 2)) return 1;
        return 2;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] A = new int[N];
        int[] B = new int[M];

        for (int i = 0; i < N; i++) A[i] = sc.nextInt();
        for (int i = 0; i < M; i++) B[i] = sc.nextInt();

        // 상태 방문 체크
        Set<String> visited = new HashSet<>();

        int idx1 = 0;
        int idx2 = 0;
        int turn = 0;          // 0: 선공 결정 단계, 1: 묵찌빠 단계
        int attacker = -1;     // 0: 봇1, 1: 봇2 (turn==1에서만 의미 있음)

        while (true) {
            String state = idx1 + "," + idx2 + "," + turn + "," + attacker;
            if (visited.contains(state)) {
                System.out.println(0);  // 무한 반복
                return;
            }
            visited.add(state);

            int hand1 = A[idx1];
            int hand2 = B[idx2];

            int res = rps(hand1, hand2);

            if (turn == 0) {
                // 선공/후공 정하기
                if (res == 0) {
                    // 비김 -> 다시
                } else if (res == 1) {
                    attacker = 0;   // 봇1이 선공
                    turn = 1;       // 묵찌빠 단계로 진입
                } else { // res == 2
                    attacker = 1;   // 봇2가 선공
                    turn = 1;
                }
            } else {
                // 묵찌빠 단계
                if (res == 0) {
                    // 둘이 같은 손 -> 선공 승리
                    if (attacker == 0) System.out.println(1);
                    else System.out.println(2);
                    return;
                } else {
                    // 승패가 갈렸을 때
                    int winner; // 0: 봇1, 1: 봇2
                    if (res == 1) winner = 0;
                    else winner = 1;

                    if (winner != attacker) {
                        // 후공이 이김 -> 선공/후공 교대
                        attacker = 1 - attacker;
                    }
                    // 선공이 이긴 경우는 그대로 유지
                }
            }

            // 다음 턴으로 인덱스 이동 (리스트 끝나면 처음으로)
            idx1 = (idx1 + 1) % N;
            idx2 = (idx2 + 1) % M;
        }
    }
}