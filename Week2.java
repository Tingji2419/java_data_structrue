import java.util.*;
public class Week2 {
	public static class person{
		int no;
		char type;
		String name;
		int schno;
		int score;//��һ��5�֣��ݼ�
	}
	public static class ListNode{
		person x = new person();
		ListNode next;
		ListNode(){;
		}
		ListNode(person newone){
			x = newone;
		}
		
		public void add(person newone) {//����ѧУ˳����β������½ڵ�
			ListNode newNode = new ListNode(newone);
			if(this.x.schno == newNode.x.schno) {
				newNode.next = this.next;
				this.next = newNode;
			}
			else if(this.next == null) 
				this.next = newNode;
			else
				this.next.add(newone);
		}
		
		public void print(){//��ӡ���б�
			if(this.x.name == null)
				this.next.print();
			else {
				System.out.printf("%d\t%c\t%s\t%d\t%d", this.x.no, this.x.type, this.x.name, this.x.schno, this.x.score);
				System.out.println();
				if(this.next != null)
					this.next.print();
			}
		}		
	}
	
	public static void main(String[] args) {
		ListNode a = new ListNode();
		int c;
		System.out.println("��ӭʹ�������ɼ�¼��ϵͳ");
		while(true) {
			System.out.println("ϵͳ�������£�");
			System.out.println("1���ɼ�¼��");
			System.out.println("2���ɼ�����");
			System.out.println("3���С�Ů�Ӹ����ܷ�");
			System.out.println("0���˳�ϵͳ");
			System.out.println("������������֣���");
			Scanner scan = new Scanner(System.in);
			c = scan.nextInt();
		
			switch(c) {
			case 0:  System.out.println("ллʹ��"); System.exit(0);
			case 1:  fun_1(a);  break;
			case 2:  fun_2(a);  break;
			case 3:  fun_3(a);  break;
			default:  System.out.println("\n �������������������! ");
			}
		
		}
		
	}
	public static void fun_1(ListNode a) {
		Scanner scan = new Scanner(System.in);
		System.out.println("�밴˳����������(��Ŀ��, �Ա�AΪ���ӣ�BΪŮ�ӣ�, ����, ѧУ) :");
		int ia = 0;
		int ib = 0;
		do{
			person b = new person();
			char s = scan.next().charAt(0);
			if(s == 'Q')
				break;
			b.no = s - 48;
			b.type = scan.next().charAt(0);
			b.name = scan.next();
			b.schno = scan.nextInt();
			if(b.no % 2 == 1) {
				ia = ia % 5;
				ia++;
				switch(ia) {
				case 1: b.score = 7; break;
				case 2: b.score = 5; break;
				case 3: b.score = 3; break;
				case 4: b.score = 2; break;
				case 5: b.score = 1; break;
				}
			}
			else{
				ib = ib % 3;
				ib++;
				switch(ib) {
				case 1: b.score = 5; break;
				case 2: b.score = 3; break;
				case 3: b.score = 2; break;
				}
			}
			a.add(b);
		}while(true);
		System.out.println("¼��ɹ���");
	//	scan.close();
	}
	public static void fun_2(ListNode a) {
		System.out.printf("��Ŀ\t�Ա�\t����\tѧУ\t�÷�");
		System.out.println();
		a.print();
	}
	public static void fun_3(ListNode a) {
		int[] sum = new int[41];
		for(int i = 0; i< 41; i++)
			sum[i] = -1;
		for(;a != null; a = a.next) {
			if(sum[a.x.schno] == -1)
				sum[a.x.schno] = sum[2 * a.x.schno] = 0;
			if(a.x.type == 'A')
				sum[a.x.schno] += a.x.score;
			else
				sum[2 * a.x.schno] += a.x.score;
		}
		System.out.println("ѧУ\t������Ŀ�ܷ�\tŮ����Ŀ�ܷ�\t");
		for(int i = 1; sum[i] >= 0 && i < 21;i++) {
			//System.out.println("ѧУ\t������Ŀ�ܷ�\tŮ����Ŀ�ܷ�\t");
			System.out.printf("%d\t%d\t\t%d\t",i, sum[i], sum[i*2]);
			System.out.println();
		}
	}
}
