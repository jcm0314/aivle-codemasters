// Problem: 신소재 개발
// Number: 4895
// Solved: 2026. 6. 12.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        long A = scanner.nextLong();
        long B = scanner.nextLong();
        long C = scanner.nextLong();

        long total = A + B + C;
        long maxCnt = Math.max(A, Math.max(B, C));
        long rest = total - maxCnt;

        if (maxCnt <= rest + 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}