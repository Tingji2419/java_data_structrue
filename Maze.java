package oop.search;

import java.util.*;
import java.io.*;

public class Maze{

    public static void find(int[][] map, int sx, int sy, int fx, int fy, ArrayList<Integer> px, ArrayList<Integer> py){
        boolean[][] visited = new boolean[map.length][map[0].length];
        dfs(map, visited, sx, sy, fx, fy, px, py);
    }

    public static boolean dfs(int[][] map, boolean[][] visited, int vx, int vy, int fx, int fy, ArrayList<Integer> px, ArrayList<Integer> py){
        visited[vx][vy] = true;
        px.add(vx);
        py.add(vy);
        if(vx == fx && vy == fy){
            return true;
        }
        //up
        if(vx - 1 >= 0 && map[vx - 1][vy] == 0 && visited[vx - 1][vy] == false){
            if(dfs(map, visited, vx - 1, vy, fx, fy, px, py)){
                return true;
            }
        }
        //left
        if(vy - 1 >= 0 && map[vx][vy - 1] == 0 && visited[vx][vy - 1] == false){
            if(dfs(map, visited, vx, vy - 1, fx, fy, px, py)){
                return true;
            }
        }
        //down
        if(vx + 1 < map.length && map[vx + 1][vy] == 0 && visited[vx + 1][vy] == false){
            if(dfs(map, visited, vx + 1, vy, fx, fy, px, py)){
                return true;
            }
        }
        //right
        if(vy + 1< map[vx].length && map[vx][vy + 1] == 0 && visited[vx][vy + 1] == false){
            if(dfs(map, visited, vx, vy + 1, fx, fy, px, py)){
                return true;
            }
        }
        px.remove(px.size() - 1);
        py.remove(py.size() - 1);
        return false;
    }

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new File("data/SEARCH/inst1"));
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = scan.nextInt();
            }
        }
        scan.close();
        ArrayList<Integer> px = new ArrayList<Integer>();
        ArrayList<Integer> py = new ArrayList<Integer>();
        find(map, 0, 0, 9, 4, px, py);
        for(int i = 0; i < px.size(); i++){
            System.out.println(px.get(i) + "\t" + py.get(i));
        }
    }
}