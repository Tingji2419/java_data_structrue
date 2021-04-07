package dp;

import java.util.Scanner;

import java.io.*;

public class tree {
	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(new File("src/dp/inst1"));
		int n = scan.nextInt();
		int[][] tree = new int[n+1][n+1];
		for(int i = 1; i <= n ;i ++) 
			for(int j = 1; j <= i ; j++)
				tree[i][j] = scan.nextInt();
		
		scan.close();
		int[][]dp = new int [n + 1][n + 1]; 
		for(int i = n; i >=0 ; i--)
			for(int j = 1; j <= i; j++) {
				
			}
	}
}
