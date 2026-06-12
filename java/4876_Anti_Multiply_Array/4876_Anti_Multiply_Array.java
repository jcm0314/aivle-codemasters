// Problem: Anti Multiply Array
// Number: 4876
// Solved: 2026. 6. 12.
// Language: Java

import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int i, j;
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        // 곱 -> 그 곱을 만드는 인덱스 쌍 리스트
        Map<Integer, List<Pair>> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int prod = A[i] * A[j];

                if (map.containsKey(prod)) {
                    // 이전에 같은 곱을 만들었던 쌍들 확인
                    List<Pair> list = map.get(prod);
                    for (Pair p : list) {
                        // 네 인덱스가 모두 서로 다른지 확인
                        if (p.i != i && p.i != j && p.j != i && p.j != j) {
                            System.out.println("YES");
                            return;
                        }
                    }
                    // 현재 쌍도 리스트에 추가
                    list.add(new Pair(i, j));
                } else {
                    List<Pair> list = new ArrayList<>();
                    list.add(new Pair(i, j));
                    map.put(prod, list);
                }
            }
        }

        System.out.println("NO");
    }
}