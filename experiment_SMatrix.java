import java.util.*;;

public class experiment_SMatrix {
	public static class Triple{
		int row;//��
		int col;//��
		int val;//ֵ
	}
	public static class RLSMatrix{
		Triple data[] = new Triple[100];
		int rpos[] = new int[30];
		int m;//��
		int n;//��
		int t;//�ǿո���
		
		public void print() {
			int tem[][] = new int[this.m + 1][this.n + 1];
			for(int i = 1; i <= this.t; i++)
				tem[this.data[i].row][this.data[i].col] = this.data[i].val;
			for(int i = 1; i < this.m + 1; i++) {
				for(int j = 1; j < this.n + 1; j++)
					System.out.printf("%d\t", tem[i][j]);
				System.out.println();
			}
		}
		
		public void Init_rpos() {
			for(int i = 1; i <= this.m; i++) {
				this.rpos[i] = 1;
			}
			for(int i = 1; i <= this.m; i++) {
				for(int j = 1; j < this.t; j++) {
					if(this.data[j].row < i)
						this.rpos[i]++;
				}
			}
		}
		
		
	}
	public static void add_SMatrix() {
		RLSMatrix a = new RLSMatrix();
		RLSMatrix b = new RLSMatrix();
		Scanner scan = new Scanner(System.in);
		System.out.println("��������������������������ӡ�����������������������");		
		System.out.println();
		System.out.println("�������һ������");
		System.out.print("���� = ");
		a.m = scan.nextInt();
		System.out.print("���� = ");
		a.n = scan.nextInt();
		System.out.print("�ǿ�Ԫ�ظ���Ϊ = ");
		a.t = scan.nextInt();
		System.out.println("�������������Ԫ�ص��� �� ֵ���ո���룩�� ");
		for(int i = 1; i <= a.t; i++) {
			a.data[i] = new Triple();
			a.data[i].row = scan.nextInt();
			a.data[i].col = scan.nextInt();
			a.data[i].val = scan.nextInt();
		}
		System.out.println();
		System.out.println("������ڶ�������");
		System.out.println("(����������Ĭ�����һ����ͬ )");
		b.m = a.m;
		b.n = a.n;
		System.out.print("�ǿ�Ԫ�ظ���Ϊ = ");
		b.t = scan.nextInt();
		System.out.println("�������������Ԫ�ص��� �� ֵ���ո���룩�� ");
		for(int i = 1; i <= b.t; i++) {
			b.data[i] = new Triple();
			b.data[i].row = scan.nextInt();
			b.data[i].col = scan.nextInt();
			b.data[i].val = scan.nextInt();
		}
		System.out.println("��������������������������ϡ�������������������");
		a.Init_rpos();
		b.Init_rpos();
		System.out.println("����������������������ʼ���󡪡�����������������");
		a.print();
		System.out.println();
		b.print();
		System.out.println("����������������������ӽ����������������������");
		
		for(int j = 1; j <= b.t; j++) {
			int i = a.rpos[b.data[j].row];
			if(b.data[j].row == a.m) {
				while(b.data[j].col > a.data[i].col) {
					i++;	
					if(i > a.t)	break;
				}
			}
			else{
				while(b.data[j].col > a.data[i].col && i < a.rpos[b.data[j].row + 1]) {
					i++;	
				}
			}
			
			if(i <= a.t && b.data[j].col == a.data[i].col && b.data[j].row == a.data[i].row) {
				a.data[i].val += b.data[j].val;
				if(a.data[i].val == 0) {
					for(int z = i; z < a.t; z++) {
						a.data[z].col = a.data[z + 1].col;
						a.data[z].row = a.data[z + 1].row;
						a.data[z].val = a.data[z + 1].val;
					}
					a.data[a.t] = null;
					a.t--;
					a.Init_rpos();
				}
			}
			else {
				a.data[a.t + 1] = new Triple();
				for(int z = a.t + 1; z > i; z--) {
					a.data[z].col = a.data[z - 1].col;
					a.data[z].row = a.data[z - 1].row;
					a.data[z].val = a.data[z - 1].val;
				}
				a.data[i].col = b.data[j].col;
				a.data[i].row = b.data[j].row;
				a.data[i].val = b.data[j].val;
				a.t++;
				a.Init_rpos();
			}
		}

		a.print();
		System.out.println("������������������������������������������������������������");
	}
	public static void minus_SMatrix() {
		RLSMatrix a = new RLSMatrix();
		RLSMatrix b = new RLSMatrix();
		Scanner scan = new Scanner(System.in);
		System.out.println("���������������������������������������������������");		
		System.out.println();
		System.out.println("�������һ������");
		System.out.print("���� = ");
		a.m = scan.nextInt();
		System.out.print("���� = ");
		a.n = scan.nextInt();
		System.out.print("�ǿ�Ԫ�ظ���Ϊ = ");
		a.t = scan.nextInt();
		System.out.println("�������������Ԫ�ص��� �� ֵ���ո���룩�� ");
		for(int i = 1; i <= a.t; i++) {
			a.data[i] = new Triple();
			a.data[i].row = scan.nextInt();
			a.data[i].col = scan.nextInt();
			a.data[i].val = scan.nextInt();
		}
		System.out.println();
		System.out.println("������ڶ�������");
		System.out.println("(����������Ĭ�����һ����ͬ )");
		b.m = a.m;
		b.n = a.n;
		System.out.print("�ǿ�Ԫ�ظ���Ϊ = ");
		b.t = scan.nextInt();
		System.out.println("�������������Ԫ�ص��� �� ֵ���ո���룩�� ");
		for(int i = 1; i <= b.t; i++) {
			b.data[i] = new Triple();
			b.data[i].row = scan.nextInt();
			b.data[i].col = scan.nextInt();
			b.data[i].val = scan.nextInt();
		}
		System.out.println("��������������������������ϡ�������������������");
		a.Init_rpos();
		b.Init_rpos();
		System.out.println("����������������������ʼ���󡪡�����������������");
		a.print();
		System.out.println();
		b.print();
		System.out.println("����������������������������������������������");
		
		for(int j = 1; j <= b.t; j++) {
			int i = a.rpos[b.data[j].row];
			if(b.data[j].row == a.m) {
				while(b.data[j].col > a.data[i].col) {
					i++;	
					if(i > a.t) break;
				}
			}
			else{
				while(b.data[j].col > a.data[i].col && i < a.rpos[b.data[j].row + 1]) {
					i++;	
				}
			}
			
			if(i <= a.t && b.data[j].col == a.data[i].col && b.data[j].row == a.data[i].row) {	
				a.data[i].val -= b.data[j].val;
				if(a.data[i].val == 0) {
					for(int z = i; z < a.t; z++) {
						a.data[z].col = a.data[z + 1].col;
						a.data[z].row = a.data[z + 1].row;
						a.data[z].val = a.data[z + 1].val;
					}
					a.data[a.t] = null;
					a.t--;
					a.Init_rpos();
				}
			}
			else {
				a.data[a.t + 1] = new Triple();
				for(int z = a.t + 1; z > i; z--) {
					a.data[z].col = a.data[z - 1].col;
					a.data[z].row = a.data[z - 1].row;
					a.data[z].val = a.data[z - 1].val;
				}
				a.data[i].col = b.data[j].col;
				a.data[i].row = b.data[j].row;
				a.data[i].val = -b.data[j].val;
				a.t++;
				a.Init_rpos();
			}		
		}
	//	a.Init_rpos();
		a.print();
	}
	public static void mult_SMatrix() {
		RLSMatrix a = new RLSMatrix();
		RLSMatrix b = new RLSMatrix();
		RLSMatrix c = new RLSMatrix();
		Scanner scan = new Scanner(System.in);
		System.out.println("��������������������������ˡ�����������������������");		
		System.out.println();
		System.out.println("�������һ������");
		System.out.print("���� = ");
		a.m = scan.nextInt();
		System.out.print("���� = ");
		a.n = scan.nextInt();
		System.out.print("�ǿ�Ԫ�ظ���Ϊ = ");
		a.t = scan.nextInt();
		System.out.println("�������������Ԫ�ص��� �� ֵ���ո���룩�� ");
		for(int i = 1; i <= a.t; i++) {
			a.data[i] = new Triple();
			a.data[i].row = scan.nextInt();
			a.data[i].col = scan.nextInt();
			a.data[i].val = scan.nextInt();
		}
		System.out.println();
		System.out.println("������ڶ�������");
		System.out.printf("(�����������ѷֱ�Ĭ��Ϊ%d %d )",a.n,a.m);
		b.m = a.n;
		b.n = a.m;
		c.m = a.m;
		c.n = b.n;
		System.out.print("�ǿ�Ԫ�ظ���Ϊ = ");
		b.t = scan.nextInt();
		System.out.println("�������������Ԫ�ص��� �� ֵ���ո���룩�� ");
		for(int i = 1; i <= b.t; i++) {
			b.data[i] = new Triple();
			b.data[i].row = scan.nextInt();
			b.data[i].col = scan.nextInt();
			b.data[i].val = scan.nextInt();
		}
		System.out.println("��������������������������ϡ�������������������");
		a.Init_rpos();
		b.Init_rpos();
		int flag[] = new int[b.m + 1];
		for(int i = 1; i <= b.t; i++) {
			flag[b.data[i].row] = 1;
		}
		for(int i = 1; i <= b.m; i++)
			if(flag[i] != 1)
				b.rpos[i] = b.t + 1;
		
		//c.Init_rpos();
		System.out.println("����������������������ʼ���󡪡�����������������");
		a.print();
		System.out.println();
		b.print();
		System.out.println("����������������������˽����������������������");
		int tp = 0;
		int p = 0;
		int j = 0;
		int t = 0;
		int ccol = 0;
		if(a.t * b.t != 0) {
			int ctemp[] = new int[c.m + 1];
			for(int i = 1; i < a.m; i++) {
				for(int z = 1; z < ctemp.length; z++)
					ctemp[z] = 0;
				c.rpos[i] = c.t + 1;
				if(i < a.m) tp = a.rpos[i + 1];
				else tp = a.t + 1;
				for(p = a.rpos[i]; p < tp; p++) {
					j = a.data[p].col;
					if(j < b.m)  t = b.rpos[j + 1];
					else	t = b.t + 1;
					for(int q = b.rpos[j]; q < t; q++) {
						if(b.data[q].row != a.data[p].col)
							continue;
						ccol = b.data[q].col;
						ctemp[ccol] += a.data[p].val * b.data[q].val;					
					}
				}
				for(ccol = 1; ccol <= c.n; ccol++) {
					if(ctemp[ccol] != 0) {
						c.t++;
						c.data[c.t] = new Triple();
						c.data[c.t].row = i;
						c.data[c.t].col = ccol;
						c.data[c.t].val = ctemp[ccol];
					}
				}
			}
		}
		//c.Init_rpos();
		c.print();
	}
	public static void main(String[] args) {
		int c;
		System.out.println("��ӭʹ��ϡ�����������");
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("�������£�");
			System.out.println("1���������");
			System.out.println("2���������");
			System.out.println("3���������");
			System.out.println("0���˳�ϵͳ");
			System.out.println("������������֣���");
			c = scan.nextInt();
			switch(c) {
			case 0:  System.out.println("ллʹ��"); System.exit(0);
			case 1:  add_SMatrix();  break;
			case 2:  minus_SMatrix();  break;
			case 3:  mult_SMatrix();  break;
			default:  System.out.println("\n �������������������! ");
			}
		
		}
	}
	

}
