package divide_conquer;
import java.util.*;
public class guibing {
	public static void merge(int[] a,int s, int mid ,int t) {
		ArrayList<Integer> b = new ArrayList<Integer>();
		int i = s;
		int j = mid + 1;
		while(i <= mid&& j <= t) {
			if(a[i] <= a[j]) {
				b.add(a[i]);
				i++;}
			else {
				b.add(a[j]);
				j++;
			}
		}
		while(i <= mid) {
			b.add(a[i]);
			i++;
		}
		while(j <= t) {
			b.add(a[j]);
			j++;
		}	
		//for(int i <= b.)½«b¸´ÖÆ¸øa
	}
	
	public static void sort(int[] a, int s, int t) {
		if( s >= t)
			return;
		int mid = (s + t) / 2;
		sort(a,s,mid);
		sort(a,mid+1,t);
		merge(a, s, mid, t);
	}
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		ArrayList<Integer> a = new ArrayList<Integer>();
		while(cin.hasNext())
			a.add(cin.nextInt());
		//int[] b = a.toArray(null); 
		//sort(b, 0, a.length()-1);
		
	}
}
