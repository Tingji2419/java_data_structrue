import java.util.Scanner;

//import java.util.*;
public class experiment_2 {
	public static class stackNode{
		int x;
		int y;
		int d = 1;  //��1��2��3��4�ֱ��ʾ�ҡ��¡�����
		stackNode next;		
	}
	public static class Sqstack{
		private stackNode bottom;
		private stackNode top;
		private int size;
		
		public void Push(int x, int y, int d) {
			this.top.x = x;
			this.top.y = y;
			this.top.d = d;
			this.size++;
			stackNode new_pos = new stackNode();
			top.next = new_pos;
			top = top.next;
		}
		public stackNode GetTop() {
			if(top == bottom)
				return bottom;
			stackNode temp = this.bottom;
			for(; temp.next != top; temp = temp.next);
			return temp;
		}
		
		public stackNode Pop() {
			if(top == bottom) {
				System.out.println("ջ��ɶ����ʣ!");
				return null;
			}
			stackNode temp = this.bottom;
			for(; temp.next != top; temp = temp.next);
			top = temp;
			top.next = null;
			//top.x = top.y = top.d = 0;
			this.size--;
			return temp;
		}
		
		public boolean isStackEmpty() {
			if(top == bottom)
				return true;
			else
				return false;
		}
	}
	public static Sqstack Init_stack(){
		Sqstack stack = new Sqstack();
		stack.bottom = new stackNode();
		stack.top = stack.bottom;
		stack.size = 0;
		return stack;
	}
	public static void main(String[] args) {
		Sqstack route = Init_stack();
		Scanner scan = new Scanner(System.in);
		System.out.println("������������������������������������Թ����⡪������������������������������");
		System.out.println("���������������������������������������Թ���С����������������������������");
		System.out.println("������");
		int m = scan.nextInt();
		System.out.println("������");
		int n = scan.nextInt();
		System.out.println("���������������������������������������Թ���ʽ����������������������������");
		System.out.println("����������������������0��ʾ���ߣ�1��ʾ�����ߡ���������������������");
		int[][] map = new int[m+2][n+2];//���ϱ߿�
		for(int i = 0; i < m+2; i++) 
			map[i][0] = 1;
		for(int i = 0; i < m+2; i++) 
			map[i][n+1] = 1;
		for(int i = 0; i < n+2; i++) 
			map[0][i] = 1;
		for(int i = 0; i < n+2; i++) 
			map[m+1][i] = 1;
		for(int i = 1 ; i <= m; i++)
			for(int j = 1; j <= n; j++)
				map[i][j] = scan.nextInt();
		scan.close();
		route.Push(1, 1, 1); //��һ��Ĭ������̽��
		
		
		do {
			if(map[route.GetTop().x][route.GetTop().y] == 0) {
				map[route.GetTop().x][route.GetTop().y] = 1;
				switch(route.GetTop().d) {
					case 1: route.Push(route.GetTop().x, route.GetTop().y + 1, 1);break;//����
					case 2: route.Push(route.GetTop().x + 1, route.GetTop().y, 1);break;//����
					case 3: route.Push(route.GetTop().x, route.GetTop().y - 1, 1);break;//����
					case 4: route.Push(route.GetTop().x - 1, route.GetTop().y, 1);break;//����
				}
				if(route.GetTop().x == m && route.GetTop().y == n)
					break;
			}
			else {
				if(!route.isStackEmpty()) {
					route.Pop();
					while(route.GetTop().d == 4 && !route.isStackEmpty()) {
						map[route.GetTop().x][route.GetTop().y] = 1;
						route.Pop();
					}
					if(route.GetTop().d < 4) {
						route.GetTop().d++;
						switch(route.GetTop().d) {
						case 1: route.Push(route.GetTop().x, route.GetTop().y + 1, 1);break;//����
						case 2: route.Push(route.GetTop().x + 1, route.GetTop().y, 1);break;//����
						case 3: route.Push(route.GetTop().x, route.GetTop().y - 1, 1);break;//����
						case 4: route.Push(route.GetTop().x - 1, route.GetTop().y, 1);break;//����
						}
						if(route.GetTop().x == m && route.GetTop().y == n)
							break;
					}
				}
			}
		}while(!route.isStackEmpty());
		
		if(route.isStackEmpty())
			System.out.println("�Թ��޽�");
		else {
			int[][] way = new int[m + 2][n + 2];
			System.out.println("·�����£�");
			while(!route.isStackEmpty()) {
				way[route.GetTop().x][route.GetTop().y] = 1;
				//System.out.printf("(%d,%d,%d)\n",route.GetTop().x,route.GetTop().y,route.GetTop().d);
				route.Pop();
			}
			for(int i = 1; i < m +1; i++) {
				System.out.println();
				for(int j = 1; j < n + 1; j++)
					System.out.printf("%d ",way[i][j]);
			}
		}
	}
}
