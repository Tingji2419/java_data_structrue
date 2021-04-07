
public class experiment_PrimTree {
	public static class temp{
		int adjvex; //用顺序的数字代替节点名
		int lowcost;
		int front;
	}
	public static class Graph{
		int[][] arcs; //用邻接图表示
		int vertex_num;
		char[] vertex_name;
		temp[] closedge; 
		
		Graph(int[][] a, char[] b, int n){
			this.arcs = new int[n][n];
			this.vertex_name = new char[n];
			this.closedge = new temp[n];
			this.vertex_num = n;
			for(int i = 0; i < n; i++) {
				this.vertex_name[i] = b[i];
				this.closedge[i] = new temp();
				this.closedge[i].adjvex = i;
				for(int j = 0; j < n; j++)
					this.arcs[i][j] = a[i][j];
			}
		}
		int locate(char x) {
			for(int i = 0; i < this.vertex_num; i++)
				if(this.vertex_name[i] == x) {
					return i;
				}
			return -1;
		}
		int minimum_closedge() {
			int temp = 1000000;
			int min = -1;
			for(int i = 0; i < this.vertex_num; i++)
				if(this.closedge[i].lowcost < temp && this.closedge[i].lowcost != 0) {
					temp = this.closedge[i].lowcost;
					min = i;
				}
			return min;
		}
		void MiniSpanTree_Prim(char x) {
			int k = this.locate(x);
			System.out.print(this.vertex_name[k]);
			for(int j = 0; j < this.vertex_num; j++)
				if(j != k) {
					this.closedge[j].adjvex = j;
					this.closedge[j].lowcost = this.arcs[k][j];
				}
			this.closedge[k].lowcost = 0;
			for(int i = 1; i < this.vertex_num; i++) {
				k = this.minimum_closedge();
				if(i != 1)
					System.out.print(this.vertex_name[this.closedge[k].front]);
				System.out.print("——");
				System.out.print(this.vertex_name[k]);
				System.out.println();
				this.closedge[k].lowcost = 0;
				for(int j = 0; j < this.vertex_num; j++) {
					if(this.arcs[k][j] < this.closedge[j].lowcost) {
						this.closedge[j].lowcost = this.arcs[k][j];
						this.closedge[j].front = k;
					}
				}
			}
		}
		
	}
	public static void main(String[] args) {
		int inf = 1000000;
		int[][] a = {
				{inf,4,3,inf,inf,inf,inf,inf},
				{4,inf,5,5,9,inf,inf,inf},
				{3,5,inf,5,inf,inf,inf,5},
				{inf,5,5,inf,7,6,5,4},
				{inf,9,inf,7,inf,3,inf,inf},
				{inf,inf,inf,6,3,inf,2,inf},
				{inf,inf,inf,5,inf,2,inf,6},
				{inf,inf,5,4,inf,inf,6,inf}
		};
		char[] b = {'a','b','c','d','e','f','g','h'};
		Graph test = new Graph(a,b,b.length);
		System.out.print("最小生成树如下：");
		System.out.println();
		test.MiniSpanTree_Prim('a');
	}
}