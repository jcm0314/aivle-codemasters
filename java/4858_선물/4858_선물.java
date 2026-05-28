// Problem: 선물
// Number: 4858
// Solved: 2026. 5. 28.
// Language: Java

// don't place package name. 

import java.io.*;

import java.util.*; 

// don't change 'Main' class name and  'public' accessor. 

public class Main {
    private static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    
    public static void main(String[] args) throws IOException { 

        Scanner scanner = new Scanner(System.in); 

        int N = scanner.nextInt(); // 선물 종류 수
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        
        int g = arr[0];
        for (int i = 1; i < N; i++) {
            g = gcd(g, arr[i]);
        }
        
        System.out.println(g);
    }
}