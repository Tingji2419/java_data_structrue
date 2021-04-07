package oop.search;

import java.io.*;
import java.util.*;

public class TSP{

    public double best_cost;
    public ArrayList<Integer> best_path;

    public static class State implements Comparable<State>{

        public double cost;
        public int id;
        public int num;
        public boolean[] visited;

        public State pre;

        public int compareTo(State s){
            if(num > s.num){
                return -1;
            }
            else if(num < s.num){
                return 1;
            }
            else if(cost < s.cost){
                return -1;
            }
            else if(cost > s.cost){
                return 1;
            }
            else{
                return 0;
            }
        }
    }



    public static class State2 implements Comparable<State2>{

        public double cost;
        public int id;
        public int num;
        public long visited;

        public State2 pre;

        public int compareTo(State2 s){
            if(num > s.num){
                return -1;
            }
            else if(num < s.num){
                return 1;
            }
            else if(cost < s.cost){
                return -1;
            }
            else if(cost > s.cost){
                return 1;
            }
            else{
                return 0;
            }
        }
    }


    public static void generate(int n) throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter("data/SEARCH/tsp" + n));
        Random rand = new Random(1);
        out.write(n + "");
        out.newLine();
        for(int i = 0; i < n; i++){
            int x = rand.nextInt(101);
            int y = rand.nextInt(101);
            out.write(x + " " +y);
            out.newLine();
        }
        out.close();
    }

    public void dfs(int n, double[][] map, ArrayList<Integer> path, double cost){
        if(path.size() == n){
            int first = path.get(0);
            int last = path.get(path.size() - 1);
            if(cost + map[last][first] < best_cost){
                best_cost = cost + map[last][first];
                best_path = new ArrayList<Integer>(path);
                best_path.add(first);
                System.out.println("best path: " + best_path + "\t" + best_cost);
            }
            return ;
        }
        for(int i = 0; i < n; i++){
            if(path.contains(i)){
                continue;
            }
            double ncost = cost + map[path.get(path.size() - 1)][i];
            path.add(i);
            dfs(n, map, path, ncost);
            path.remove(path.size() - 1);
        }
    }


    public void dfs2(int n, double[][] map, ArrayList<Integer> path, boolean[] visited, double cost){
        if(path.size() > 1 && cost + map[path.get(path.size() - 1)][path.get(0)] >= best_cost){
            return;
        }
        if(path.size() == n){
            int first = path.get(0);
            int last = path.get(path.size() - 1);
            if(cost + map[last][first] < best_cost){
                best_cost = cost + map[last][first];
                best_path = new ArrayList<Integer>(path);
                best_path.add(first);
                System.out.println("best path: " + best_path + "\t" + best_cost);
            }
            return ;
        }
        for(int i = 0; i < n; i++){
            if(visited[i]){
                continue;
            }
            double ncost = cost + map[path.get(path.size() - 1)][i];
            path.add(i);
            visited[i] = true;
            dfs2(n, map, path, visited, ncost);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }



    public boolean find(State s){
        int[] p = {0, 2, 4, 6, 5, 1, 8, 3, 7, 9, 0};
        ArrayList<Integer> q = new ArrayList<Integer>();
        State t = s;
        while(t != null){
            q.add(0, t.id);
            t = t.pre;
        }
        for(int i = 0; i < q.size(); i++){
            if(p[i] != q.get(i)){
                return false;
            }
        }
        System.out.println(q + "\t" + s.cost);
        return true;
    }

    public void bfs(int n, double[][] map){
        PriorityQueue<State> q = new PriorityQueue<State>();
        State init = new State();
        init.cost = 0;
        init.id = 0;
        init.num = 1;
        init.pre = null;
        init.visited = new boolean[n];
        init.visited[0] = true;
        q.add(init);

        while(q.isEmpty() == false){
            State s = q.poll();
            //find(s);
            if(s.num == n){
                if(s.cost + map[s.id][0] < best_cost){
                    best_cost = s.cost + map[s.id][0];
                    best_path.clear();
                    State t = s;
                    while(t != null){
                        best_path.add(0, t.id);
                        t = t.pre;
                    }
                    best_path.add(0);
                    System.out.println("best path: " + best_path + "\t" + best_cost);
                }
                continue;
            }

            for(int i = 0; i < n; i++){
                if(s.visited[i]){
                    continue;
                }
                State ns = new State();
                ns.id = i;
                ns.cost = s.cost + map[s.id][i];
                if(ns.cost + map[i][0] >= best_cost){
                    continue;
                }
                ns.num = s.num + 1;
                ns.visited = new boolean[n];
                for(int j = 0; j < n; j++){
                    ns.visited[j] = s.visited[j];
                }
                ns.visited[i] = true;
                ns.pre = s;
                q.add(ns);
            }
        }
    }


    public void bfs2(int n, double[][] map){
        long[] mask = new long[n];
        long mk = 0x01;
        long all_mask = 0x00;
        for(int i = 0; i < n; i++){
            mask[i] = mk;
            all_mask = all_mask | mk;
            mk *= 2;
        }

        PriorityQueue<State2> q = new PriorityQueue<State2>();
        State2 init = new State2();
        init.cost = 0;
        init.id = 0;
        init.num = 1;
        init.pre = null;
        init.visited = mask[0];
        q.add(init);

        while(q.isEmpty() == false){
            State2 s = q.poll();
            if(s.num == n){
                if(s.cost + map[s.id][0] < best_cost){
                    best_cost = s.cost + map[s.id][0];
                    best_path.clear();
                    State2 t = s;
                    while(t != null){
                        best_path.add(0, t.id);
                        t = t.pre;
                    }
                    best_path.add(0);
                    System.out.println("best path: " + best_path + "\t" + best_cost);
                }
                continue;
            }

            for(int i = 0; i < n; i++){
                if((s.visited & mask[i]) != 0x00){
                    continue;
                }
                State2 ns = new State2();
                ns.id = i;
                ns.cost = s.cost + map[s.id][i];
                if(ns.cost + map[i][0] >= best_cost){
                    continue;
                }
                ns.num = s.num + 1;
                ns.visited = s.visited | mask[i];
                ns.pre = s;
                q.add(ns);
            }
        }
    }


    public boolean dominate(ArrayList<State2> set, State2 s2){
        for(int i = 0; i < set.size(); i++){
            State2 s1 = set.get(i);
            if(s1.cost <= s2.cost && (s1.visited & s2.visited) == s2.visited){
                return true;
            }
        }
        return false;
    }

    public void bfs3(int n, double[][] map){
        long[] mask = new long[n];
        long mk = 0x01;
        long all_mask = 0x00;
        for(int i = 0; i < n; i++){
            mask[i] = mk;
            all_mask = all_mask | mk;
            mk *= 2;
        }

        PriorityQueue<State2> q = new PriorityQueue<State2>();
        ArrayList<ArrayList<State2>> sets = new ArrayList<ArrayList<State2>>();
        for(int i = 0; i < n; i++){
            sets.add(new ArrayList<State2>());
        }
        State2 init = new State2();
        init.cost = 0;
        init.id = 0;
        init.num = 1;
        init.pre = null;
        init.visited = mask[0];
        q.add(init);
        sets.get(init.id).add(init);

        while(q.isEmpty() == false){
            State2 s = q.poll();
            if(s.num == n){
                if(s.cost + map[s.id][0] < best_cost){
                    best_cost = s.cost + map[s.id][0];
                    best_path.clear();
                    State2 t = s;
                    while(t != null){
                        best_path.add(0, t.id);
                        t = t.pre;
                    }
                    best_path.add(0);
                    System.out.println("best path: " + best_path + "\t" + best_cost);
                }
                continue;
            }

            for(int i = 0; i < n; i++){
                if((s.visited & mask[i]) != 0x00){
                    continue;
                }
                State2 ns = new State2();
                ns.id = i;
                ns.cost = s.cost + map[s.id][i];
                if(ns.cost + map[i][0] >= best_cost){
                    continue;
                }
                ns.num = s.num + 1;
                ns.visited = s.visited | mask[i];
                ns.pre = s;
                if(dominate(sets.get(ns.id), ns)){
                    continue;
                }
                q.add(ns);
                sets.get(ns.id).add(ns);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        //generate(18);
        //System.out.println("finished");
        //System.exit(0);
        Scanner scan = new Scanner(new File("data/SEARCH/tsp18"));
        int n = scan.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for(int i = 0; i < n; i++){
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
        }
        scan.close();
        double[][] map = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                map[i][j] = map[j][i] = Math.sqrt((x[i]  - x[j]) * (x[i] - x[j])
                                        + (y[i] - y[j]) * (y[i] - y[j]));
            }
        }

        TSP tsp = new TSP();
        tsp.best_cost = 1e10;
        tsp.best_path = new ArrayList<Integer>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        double t1 = System.nanoTime();
        //tsp.dfs2(n, map, path, visited, 0);
        tsp.bfs3(n, map);
        double t2 = System.nanoTime();
        System.out.println(tsp.best_path + "\t" + tsp.best_cost);
        System.out.println("computational time: " + (t2 - t1) / 1e9);
    }
}