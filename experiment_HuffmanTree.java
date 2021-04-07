
public class experiment_HuffmanTree {
	public static class HTNode{ //Node
		int weight;
		int parent;
		int lchild;
		int rchild;
//		HTNode(int w, int p, int l, int r){
//			this.weight = w;
//			this.parent = p;
//			this.rchild = r;
//			this.lchild = l;
//		}
	}

	public static class HTree{
		int leave;
		int num;
		HTNode[] head;
		char[] element;
		int[] w;
		String[] code;
		
		HTree(int n, char[] element, int[] w){
			this.leave = n;
			this.num = 2 * n - 1;
			this.head = new HTNode[num+1];
			this.element = new char[n+1];
			this.w = new int[n+1];
			this.code = new String[n+1];
			for(int i = 1; i <= n; i++) {
				this.head[i] = new HTNode();
				this.head[i].weight = w[i];
				this.element[i] = element[i];
				this.w[i] = w[i];
			}
			for(int i = n + 1; i <= this.num; i++) {
				this.head[i] = new HTNode();
			}
			
			for(int i = n + 1; i <= this.num; i++) {
				int s1, s2;
				s1 = this.Select_Min(i, 0);
				s2 = this.Select_Min(i, s1);
				this.head[s1].parent = this.head[s2].parent = i;
				this.head[i].lchild = s1;
				this.head[i].rchild = s2;
				this.head[i].weight = this.head[s1].weight + this.head[s1].weight;	
			}
		}
		
		int Select_Min(int n, int skip) {
			int temp = 10000000;
			int min = 0;
			for(int i = 1; i < n; i++) {//由于顺序决定，权值相同时一定会先选到无父母的节点
				if(this.head[i].weight < temp && i != skip && this.head[i].parent == 0) {
					temp = this.head[i].weight;
					min = i;
				}
			}
			return min;
		}
		void HTcode(){
			for(int i = 1; i <= this.leave; i++) {
				char[] tem = new char[this.leave];
				int start = 0;
				for(int c = i, f = this.head[i].parent; f != 0; c = f, f = this.head[f].parent,	start++) {
					if(this.head[f].lchild == c) 
						tem[start] = '0';
					else 
						tem[start] = '1';
				}
				this.code[i] = String.valueOf(tem);//char转String字符串
				this.code[i] = new StringBuffer(this.code[i]).reverse().toString();//翻转编码
				this.code[i] = this.code[i].trim();
			}
		}
		void Print_code() {
			System.out.print("哈夫曼编码表：");
			System.out.println();
			for(int i = 1; i <= this.leave; i++) {
				System.out.printf("%c", this.element[i]);
				System.out.printf("\t%s", this.code[i]);
				System.out.println();
			}
		}
		void to_code(String s) {
			System.out.println();
			System.out.print("原字符串：");
			System.out.println();
			System.out.print(s);
			System.out.println();
			System.out.print("其哈夫曼编码为：");
			System.out.println();
			char[] tem = s.toCharArray();
			for(int i = 0; i < tem.length; i++) {
				if(tem[i] == ' ')
					System.out.print(this.code[1]);
				else {
					System.out.print(this.code[(int)tem[i]-63]);
				}
			}
		}
		void to_Str(String s) {
			System.out.println();
			System.out.print("原哈夫曼编码为：");
			System.out.println();
			System.out.print(s);
			System.out.println();
			System.out.print("其译码为：");
			System.out.println();
			char[] tem = s.toCharArray();	
			for(int h = 0; h < tem.length ;) {
				int j = this.num;
				for(int i = h; i < tem.length && (this.head[j].lchild != 0||this.head[j].rchild!=0); h++, i++) {
					if(tem[i] == '0')
						j = this.head[j].lchild;
					else
						j = this.head[j].rchild;
				}
				System.out.print(this.element[j]);
			}
		}
	}
	
	public static void main(String[] args) {
		char[] a1 = new char[28];
		a1[1] = ' ';
		for(int i = 2; i <= 27; i++) 
			a1[i] = (char)(63+i);
		int[] a2 = {0,186,64,13,22,32,103,21,15,47,57,1,5,21,20,57,63,15,1,48,51,80,23,8,18,1,16,1};
		HTree a = new HTree(a2.length-1,a1,a2);
		a.HTcode();
		a.Print_code();
		String s = "THIS PROGRAM IS MY FAVOURITE";
		a.to_code(s);
		System.out.println();
		String s2 = "11011111001010100001001001111110101110111111111011110010000101010000110010100101001100111011110000010101110101111101011101011";
		a.to_Str(s2);

	}
}
