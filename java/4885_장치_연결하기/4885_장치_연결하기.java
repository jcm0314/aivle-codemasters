// Problem: 장치 연결하기
// Number: 4885
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static class Edge {
        int u, v;
        Edge(int u, int v) { this.u = u; this.v = v; }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] R = new int[N];
        int[] B = new int[N];

        for (int i = 0; i < N; i++) {
            R[i] = scanner.nextInt();
            B[i] = scanner.nextInt();
        }

        // 모든 가능한 간선 목록 만들기 (무방향, u < v)
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                edgeList.add(new Edge(i, j));
            }
        }
        int E = edgeList.size();

        int minEdge = Integer.MAX_VALUE;
        long count = 0;

        // 모든 타입 배치: 0..(1<<N)-1
        boolean[] isRed = new boolean[N];

        for (int maskType = 0; maskType < (1 << N); maskType++) {
            for (int i = 0; i < N; i++) {
                isRed[i] = ((maskType >> i) & 1) == 1;
            }

            // 모든 간선 선택 마스크: 0..(1<<E)-1
            int totalEdgeMasks = 1 << E;

            for (int maskEdge = 0; maskEdge < totalEdgeMasks; maskEdge++) {
                // 전선을 하나도 사용하지 않는 경우는 문제에서 주어지지 않지만,
                // 여기서도 스킵해 주면 좋다.
                if (maskEdge == 0) continue;

                int[] redCnt = new int[N];
                int[] blueCnt = new int[N];
                int edgeUsed = 0;

                for (int k = 0; k < E; k++) {
                    if (((maskEdge >> k) & 1) == 1) {
                        Edge e = edgeList.get(k);
                        int u = e.u;
                        int v = e.v;
                        edgeUsed++;

                        // u에서 본 v의 타입
                        if (isRed[v]) redCnt[u]++;
                        else blueCnt[u]++;

                        // v에서 본 u의 타입
                        if (isRed[u]) redCnt[v]++;
                        else blueCnt[v]++;
                    }
                }

                // 제약 만족 검사
                boolean ok = true;
                for (int i = 0; i < N; i++) {
                    if (R[i] != -1 && redCnt[i] != R[i]) {
                        ok = false;
                        break;
                    }
                    if (B[i] != -1 && blueCnt[i] != B[i]) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) continue;

                if (edgeUsed < minEdge) {
                    minEdge = edgeUsed;
                    count = 1;
                } else if (edgeUsed == minEdge) {
                    count++;
                }
            }
        }

        if (minEdge == Integer.MAX_VALUE) {
            // 어떤 구성도 조건을 만족하지 못함
            System.out.println(-1);
        } else {
            System.out.println(minEdge);
            System.out.println(count);
        }
    }
}