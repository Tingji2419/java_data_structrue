
package search;
import java.util.*;

public class DfS_1 {
	static int[][] map = {
			{0,1,0,1,1,0,0,0},
			{0,0,0,0,1,0,0,0},
			{0,0,0,0,1,0,0,0},
			{0,0,0,0,0,0,1,1},
			{0,0,0,0,0,1,0,0},
			{0,0,0,0,0,0,0,0}
	};
	public static boolean dfs(int[][] map, boolean[]  visited, int v, int d, ArrayList<Integer> path) {
		path.add(v);
		visited[v] = true;
		if(v == d)
			return true;
		for(int i = 0; i < map[v].length; i++)
			if(map[v][i] ==1 && visited[i] == false) 
				if(dfs(map, visited, i, d, path))
					return true;
		path.remove(path.size() - 1);
		return false;
	}
	
//	public static ArrayList<Integer> DFS_path(int s, int d, int n){
//		boolean[] visited = new boolean[map.length];
//		ArrayList<Integer> path = new ArrayList<Integer>();
//		dfs(map, visited, s, d, path);
//		return path;
//	}
//	
//	public static ArrayList<Integer> BFS_path(int s, int d, int n){
//		
//	}
//	public static void main(String[] args) {
//		
//	}
}
