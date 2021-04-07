import java.util.*;
public class Week2 {
	public static class person{
		int no;
		char type;
		String name;
		int schno;
		int score;//第一名5分，递减
	}
	public static class ListNode{
		person x = new person();
		ListNode next;
		ListNode(){;
		}
		ListNode(person newone){
			x = newone;
		}
		
		public void add(person newone) {//按照学校顺序向尾部添加新节点
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
		
		public void print(){//打印所有表单
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
		System.out.println("欢迎使用体育成绩录入系统");
		while(true) {
			System.out.println("系统功能如下：");
			System.out.println("1、成绩录入");
			System.out.println("2、成绩总览");
			System.out.println("3、男、女子各项总分");
			System.out.println("0、退出系统");
			System.out.println("请输入命令（数字）：");
			Scanner scan = new Scanner(System.in);
			c = scan.nextInt();
		
			switch(c) {
			case 0:  System.out.println("谢谢使用"); System.exit(0);
			case 1:  fun_1(a);  break;
			case 2:  fun_2(a);  break;
			case 3:  fun_3(a);  break;
			default:  System.out.println("\n 输入命令错，请重新输入! ");
			}
		
		}
		
	}
	public static void fun_1(ListNode a) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请按顺序输入数据(项目号, 性别（A为男子，B为女子）, 姓名, 学校) :");
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
		System.out.println("录入成功！");
	//	scan.close();
	}
	public static void fun_2(ListNode a) {
		System.out.printf("项目\t性别\t名字\t学校\t得分");
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
		System.out.println("学校\t男子项目总分\t女子项目总分\t");
		for(int i = 1; sum[i] >= 0 && i < 21;i++) {
			//System.out.println("学校\t男子项目总分\t女子项目总分\t");
			System.out.printf("%d\t%d\t\t%d\t",i, sum[i], sum[i*2]);
			System.out.println();
		}
	}
}
