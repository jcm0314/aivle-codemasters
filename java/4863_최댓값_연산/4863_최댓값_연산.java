// Problem: 최댓값 연산
// Number: 4863
// Solved: 2026. 5. 28.
// Language: Java

// don't place package name.
import java.io.*;
import java.util.*;

// don't change 'Main' class name and 'public' accessor.
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();

        int[] arr = {x, y, z};
        Arrays.sort(arr); // arr[0] <= arr[1] <= arr[2]

        // 최댓값이 최소 두 번 이상 등장해야 함
        if (arr[1] == arr[2]) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");
        }
    }
}