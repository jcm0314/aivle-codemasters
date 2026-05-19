// Problem: 그룹ID
// Number: 4857
// Solved: 2026. 5. 19.
// Language: Java

/* 일단 DFS/BFS 문제 - 그룹이라는 연결 요소를 찾는 문제
1. 입력 받기 :N, M 입력 받고 인접 리스트 만들어서 연결
2. 방문 배열 준비 visited boolean으로 할 예정
3. 각 정점에 대해 탐색 : N까지 순회하면서 방문하지 않은 점 나오면 거기서부터 시작 
4. 전역 변수 : bestSize랑 bestId
5. 결과 출력
*/

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        
        visited = new boolean[N + 1];
        
        int bestSize = 0;
        int bestId = Integer.MAX_VALUE;
        
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                int[] result = dfs(i);
                int size = result[0];
                int minId = result[1];
                
                if (size > bestSize) {
                    bestSize = size;
                    bestId = minId;
                } else if (size == bestSize && minId < bestId) {
                    bestId = minId;
                }
            }
        }
        System.out.println(bestId);
    }
    
    // result[0]은 사이즈고, result[1]은 minId
    static int[] dfs(int start) {
        int size = 0;
        int minId = Integer.MAX_VALUE;
        
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;
        
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            size++;
            if (cur < minId) minId = cur;
            
            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }
        return new int[]{size, minId};
    }
}