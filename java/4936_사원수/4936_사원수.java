// Problem: 사원수
// Number: 4936
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        // 첫 번째 사원수 a + bi + cj + dk
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();

        // 두 번째 사원수 w + xi + yj + zk
        int w = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();

        // 곱의 결과 o + pi + qj + rk
        int o = a * w - b * x - c * y - d * z;
        int p = a * x + b * w + c * z - d * y;
        int q = a * y - b * z + c * w + d * x;
        int r = a * z + b * y - c * x + d * w;

        System.out.println(o + " " + p + " " + q + " " + r);
    }
}