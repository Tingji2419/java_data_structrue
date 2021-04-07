package oop.search;

import java.util.*;
import java.io.*;

public class DFS{

    public static boolean dfs(int[][] map, boolean[] visited, int v, int d, ArrayList<Integer> path){
        path.add(v);
        visited[v] = true;
        if(v == d){
            return true;
        }
        for(int i = 0; i < map[v].length; i++){
            if(map[v][i] == 1 && visited[i] == false){
                if(dfs(map, visited, i, d, path)){
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public static ArrayList<Integer> find_path(int[][] map, int s, int d){
        boolean[] visited = new boolean[map.length];
        ArrayList<Integer> path = new ArrayList<Integer>();
        dfs(map, visited, s, d, path);
        return path;
    }

    public static ArrayList<Integer> bfs(int[][] map, int s, int d){
        ArrayList<Integer> path = new ArrayList<Integer>();
        ArrayList<Integer> q = new ArrayList<Integer>();
        int[] pre = new int[map.length];
        q.add(s);
        for(int i = 0; i < pre.length; i++){
            pre[i] = -1;
        }
        while(q.isEmpty() == false){
            int head = q.get(0);
            q.remove(0);
            if(head == d){
                //return the path
                int a = d;
                while(a >= 0){
                    path.add(0, a);
                    a = pre[a];
                }
                return path;
            }
            for(int i = 0; i < map.length; i++){
                if(map[head][i] == 1 && pre[i] < 0){
                    q.add(i);
                    pre[i] = head;
                }
            }
        }

        return path;
    }

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("data/SEARCH/inst"));
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] a = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                a[i][j] = scan.nextInt();
            }
        }
        scan.close();
        System.out.println(bfs(a, 0, 7));
    }
}