package oop.search;

import java.util.*;
import java.io.*;

public class KPC{

    public int best_profit;
    public int best_weight;
    public ArrayList<Integer> best_sol;

    public int[][] bound;

    public int[] bound2;

    public static void generate(int n, int m, int Q) throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter("data/SEARCH/kpc" + n));
        Random rand = new Random(1);
        out.write(n + " " + Q + " " + m);
        out.newLine();
        for(int i = 0; i < n; i++){
            int x = rand.nextInt(Q / 3) + 1;
            int y = rand.nextInt(Q / 3) + 1;
            out.write(x + " " +y);
            out.newLine();
        }
        boolean[][] conf = new boolean[n][n];
        int iter = 0;
        while(iter < m){
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
            if(conf[a][b] || a == b){
                continue;
            }
            if(a > b){
                int temp = a;
                a = b;
                b = temp;
            }
            out.write(a + " " + b);
            out.newLine();
            conf[a][b] = true;
            conf[b][a] = true;
            iter++;
        }
        out.close();
    }

    public void dfs(int n, int[] w, int[] p, boolean[][] conf, int Q, int st, ArrayList<Integer> sol, int sw, int sp){
        if(st < n - 1 && sp + bound[st + 1][Q - sw] <= best_profit){
            return;
        }
        //if(st < n - 1 && sp + bound2[st + 1] <= best_profit){
            //return;
        //}
        if(sp > best_profit){
            best_profit = sp;
            best_weight = sw;
            best_sol = new ArrayList<Integer>(sol);
            System.out.println("best solution: " + best_sol + "\t" + best_profit + "\t" + best_weight);
        }
        for(int i = st + 1; i < n; i++){
            if(sw + w[i] > Q){
                continue;
            }
            boolean is_conf = false;
            for(int j = 0; j < sol.size(); j++){
                int a = sol.get(j);
                if(conf[a][i]){
                    is_conf = true;
                    break;
                }
            }
            if(is_conf){
                continue;
            }
            sol.add(i);
            dfs(n, w, p, conf, Q, i, sol, sw + w[i], sp + p[i]);
            sol.remove(sol.size() - 1);
        }
    }

    public void compute_bound(int n, int[] w, int[] p, int Q){
        for(int i = w[n - 1]; i < Q + 1; i++){
            bound[n - 1][i] = p[n - 1];
        }

        for(int i = n - 2; i >= 0; i--){
            for(int j = 0; j <= Q; j++){
                if(j < w[i]){
                    bound[i][j] = bound[i + 1][j];
                }
                else{
                    bound[i][j] = Math.max(bound[i + 1][j], bound[i + 1][j - w[i]] + p[i]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        //generate(70, 70, 100);
        //System.out.println("hello world");
        //System.exit(0);
        Scanner scan = new Scanner(new File("data/SEARCH/kpc100"));
        int n = scan.nextInt();
        int Q = scan.nextInt();
        int m = scan.nextInt();
        int[] w = new int[n];
        int[] p = new int[n];
        boolean[][] conf = new boolean[n][n];
        for(int i = 0; i < n; i++){
            w[i] = scan.nextInt();
            p[i] = scan.nextInt();
        }
        for(int i = 0; i < m; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            conf[a][b] = conf[b][a] = true;
        }
        scan.close();

        KPC kpc = new KPC();
        kpc.best_profit = 0;
        kpc.best_sol = new ArrayList<Integer>();
        kpc.bound = new int[n][Q + 1];

        kpc.bound2 = new int[n];
        kpc.bound2[n - 1] = p[n - 1];
        for(int i = n - 2; i >= 0; i--){
            kpc.bound2[i] = kpc.bound2[i + 1] + p[i];
        }

        double t1 = System.nanoTime();
        kpc.compute_bound(n, w, p, Q);
        kpc.dfs(n, w, p, conf, Q, -1, new ArrayList<Integer>(), 0, 0);
        double t2 = System.nanoTime();

        System.out.println(kpc.best_sol + "\t" + kpc.best_profit + "\t" + kpc.best_weight);
        System.out.println("computational time: " + (t2 - t1) / 1e9);
    }
}