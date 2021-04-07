import java.util.Scanner;

public class experiment_BiTree {
	public static class BiTNode{
		char data;
		BiTNode lchild;
		BiTNode rchild;
		public char visit_data() {return this.data;}
		public boolean print() {
			System.out.println(this.data);
			return true;
		}
	}
	public static class BiTree{
		BiTNode root;
		int n;
		BiTree(BiTNode newone){
			this.root = newone;
		}
		BiTree() {	}
		public void MidTraverse() {
			Traverse(this.root);
		}
		public void add(char parent,BiTNode newone, char flag) {
			if(parent == '^') {
				if(newone.data == '^')	return;
				else {
					this.root = newone;
					this.n++;
					return;
				}
			}
			BiTNode p = create_Traverse(this.root, parent);
			if(p != null) {
				this.n++;
				if(flag == 'L')	
					p.lchild = newone;
				else 
					p.rchild = newone;
			}	
		}
	}
	private static boolean Traverse(BiTNode a) {//����ݹ����
		if(a != null) {
			if(Traverse(a.lchild))
				if(a.print())
					if(Traverse(a.rchild)) return true;
			return false;
		}else return true;
	}
	
	private static BiTNode create_Traverse(BiTNode a, char b) {//�ǵݹ��������
		stack s = Init_stack();
		BiTNode p = a;
		while(p != null || !s.isStackEmpty()) {
			if(p != null) {s.Push(p);  p = p.lchild;}
			else {
				p = s.Pop().leaf;
				if(p.data == b)	return p;
				p = p.rchild;
			}
		}
		return null;
	}

	public static class stackNode{
		BiTNode leaf;
		stackNode next;		
	}
	public static class stack{
		private stackNode bottom;
		private stackNode top;
		private int size;
		
		public void Push(BiTNode leaf) {
			this.top.leaf = leaf;
			this.size++;
			stackNode new_pos = new stackNode();
			top.next = new_pos;
			top = top.next;
		}
		public stackNode Pop() {
			if(top == bottom) {
				return null;
			}
			stackNode temp = this.bottom;
			for(; temp.next != top; temp = temp.next);
			top = temp;
			top.next = null;
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
	public static stack Init_stack(){
		stack stack = new stack();
		stack.bottom = new stackNode();
		stack.top = stack.bottom;
		stack.size = 0;
		return stack;
	}
	
	public static void main(String[] args) {
		System.out.println("������Ԫ�飨˫�׽ڵ�+���ӽڵ�+L/R����˳�����룬�س����룺");
		System.out.println("eg.ABL");
		Scanner scan = new Scanner(System.in);
		BiTree tree = new BiTree();
		while(scan.hasNextLine()) {
			BiTNode a = new BiTNode();
			String sc = scan.next();
			char parent = sc.charAt(0);
			a.data = sc.charAt(1);
			char flag = sc.charAt(2);
			if(parent == '^' && a.data == '^')
				break;
			tree.add(parent, a, flag);
		}
		System.out.println("�����������ɹ�");
		System.out.println("������������˳��Ϊ��");
		tree.MidTraverse();
	}
}
