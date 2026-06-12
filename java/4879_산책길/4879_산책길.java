// Problem: 산책길
// Number: 4879
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {

    static int N;
    static long answer;

    // 좌표를 배열 인덱스로 옮기기 위한 오프셋
    static final int OFFSET = 25;
    static final int SIZE = 60; // 충분히 크게

    // 방향: 0=동, 1=북, 2=서, 3=남
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean[][] visitedVertex;        // 점 방문 여부
    static boolean[][][] visitedEdge;        // 변 방문 여부: [x][y][dir]

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();

        visitedVertex = new boolean[SIZE][SIZE];
        visitedEdge = new boolean[SIZE][SIZE][4];

        answer = 0;

        // 시작점 (0,0)을 배열에서 (OFFSET, OFFSET)으로
        int sx = OFFSET;
        int sy = OFFSET;

        // 시작점 방문 표시
        visitedVertex[sx][sy] = true;

        // 시작 방향은 동쪽(0)으로 고정: 회전 대칭 제거
        int dir = 0;
        int nx = sx + dx[dir];
        int ny = sy + dy[dir];

        visitedVertex[nx][ny] = true;
        markEdge(sx, sy, nx, ny, dir, true);

        // 이미 한 칸 전진했으니 남은 횟수는 N-1
        dfs(nx, ny, dir, N - 1);

        System.out.println(answer);
    }

    // 간선을 방문/해제 (무방향)
    static void markEdge(int x1, int y1, int x2, int y2, int dir, boolean val) {
        int od = (dir + 2) % 4; // 반대 방향
        visitedEdge[x1][y1][dir] = val;
        visitedEdge[x2][y2][od] = val;
    }

    static void dfs(int x, int y, int dir, int stepsLeft) {
        if (stepsLeft == 0) {
            // N번 이동 완료 → 유효한 산책길
            answer++;
            return;
        }

        // 좌회전, 우회전 두 경우
        // 좌회전
        int ndirL = (dir + 1) % 4;
        moveNext(x, y, ndirL, stepsLeft);

        // 우회전
        int ndirR = (dir + 3) % 4;
        moveNext(x, y, ndirR, stepsLeft);
    }

    static void moveNext(int x, int y, int dir, int stepsLeft) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (!inRange(nx, ny)) return;
        if (visitedVertex[nx][ny]) return; // 꼭짓점 재방문 금지
        if (visitedEdge[x][y][dir]) return; // 변 재사용 금지

        visitedVertex[nx][ny] = true;
        markEdge(x, y, nx, ny, dir, true);

        dfs(nx, ny, dir, stepsLeft - 1);

        // 백트래킹
        markEdge(x, y, nx, ny, dir, false);
        visitedVertex[nx][ny] = false;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
}