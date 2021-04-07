import java.util.*;
public class experiment_HashCode {
	static int def = (int)(1.0/0.0);
	static int p = 3;//取余数的除数
	static int len = 50;
	
	public static class Node{
		int num;
	}
	public static class Hash{
		Node[] data;
		int length = len;
		
		Hash(int[] a){
			this.data = new Node[this.length];
			for(int i = 0; i< this.length; i++) {
				this.data[i] = new Node();
				this.data[i].num = def;
			}
			Hashcode(a);
		}
		private void Hashcode(int[] a){
			for(int i = 0; i < a.length; i++) {
				int temp = a[i] % p;
				if(this.data[temp].num == def)
					this.data[temp].num = a[i];
				else
					Hashcode_conf(a[i],1);
			}
		}
		private void Hashcode_conf(int a, int i) {
			while(i <= this.length/2) {
				int temp = a % p;
				if(((temp + i * i) % this.length) < this.length && this.data[(temp + i * i) % this.length].num == def) {
					this.data[(temp + i * i) % this.length].num = a;
					break;
				}
				else if(((temp - i * i) % this.length) >= 0 && this.data[(temp - i * i) % this.length].num == def) {
					this.data[(temp - i * i) % this.length].num = a;
					break;
				}
				else {
					Hashcode_conf(a,i + 1);
					break;
				}
			}
		}
		public int Search(int lab) {
			int temp = lab % p;
			if(this.data[temp].num == lab)
				return temp;
			else
				return Search_conf(lab,1);
		}
		private int Search_conf(int lab, int i) {
			while(i <= this.length/2) {
				int temp = lab % p;
				if(((temp + i * i) % this.length) < this.length && this.data[(temp + i * i) % this.length].num == lab)
					return (temp + i * i) % this.length;
				else if(((temp - i * i) % this.length) >= 0 && this.data[(temp - i * i) % this.length].num == lab) {
					return (temp - i * i) % this.length;
				}
				else
					return Search_conf(lab,i + 1);
			}
			return -1;
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入数据(空格分离)");
		String[] str = scan.nextLine().trim().split(" ");
		int[] a = new int[str.length];
		for(int i = 0; i < str.length; i++)
			a[i] = Integer.parseInt(str[i]);
		
		Hash test = new Hash(a);
		System.out.println("Hash表创建成功");
		System.out.println("请输入要查询的值：");
		int index = test.Search(scan.nextInt());
		scan.close();
		if(index == -1)
			System.out.println("查询失败，表中无此值");
		else {
			System.out.println("查询成功，在表中的位置为：");
			System.out.println(index);
		}
		
	}
}
