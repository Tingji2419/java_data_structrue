import java.util.Scanner;


public class Week2_2 {
	public static class person{
		int password;	
	}
	public static class ListNode{
		person x = new person();
		ListNode next;
		ListNode(){
			this.x = null;
			this.next = this;
		}
		ListNode(person newone){
			this.x = newone;
			this.next = this;
		}
	
		public void add(person newone, int index) {//��index������½ڵ�,���ٴӵڶ�λ��ʼ
			ListNode newNode = new ListNode(newone);
			ListNode p = this;
			if(index == 1)
				this.x = newone;
			else {
				for(int i = 1 ;i < index - 1; i++)
					p = p.next;
				newNode.next = p.next;
				p.next = newNode;
			}
		}
		public int get_elem(int index) {
			ListNode p = this;
			for(int i = 1 ;i < index; i++)
				p = p.next;
			return p.x.password;
		}
		public void change_elem(int index, int n) {
			ListNode p = this;
			for(int i = 1 ;i < index; i++)
				p = p.next;
			p.x.password = n;
		}
		
	}
	public static void main(String[] args) {	
		Scanner scan = new Scanner(System.in);
		System.out.println("�����뱨�����ޣ�");
		int m = scan.nextInt() ;
		System.out.println("������������");
		int n = scan.nextInt();
		int j = 1;
		System.out.println("���ε�����Ϊ���ո�or�س����룩��");
		ListNode a = new ListNode();
		for(int i = 1; i <= n; i++) {
			person b = new person();
			b.password = scan.nextInt();
			if(j == 1) {
				a.add(b, 1);
				j = 0;
			}
			else
				a.add(b, i);
		}
		System.out.println("����˳������Ϊ��");
		int position = 1;
		for(int i = n; i > 0; i--){		
			int count = m;
			while(count > 1 ){
				while(a.get_elem(position) == 0){
					position++;
					if(position > n)
						position -= n;
					}	
			position++;
			if(position > n)
				position -= n;
			count--;
			}
			while(a.get_elem(position) == 0){
				position++;
				if(position > n)
					position -= n;
				}	
			m = a.get_elem(position);
			a.change_elem(position, 0);
			System.out.println(position);
		}
		
	}
}
