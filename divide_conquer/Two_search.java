package divide_conquer;

import java.util.Random;
import java.util.ArrayList;
public class Two_search {
	public static int sort(int[] a, int b) {
		int low = 0;
		int high = a.length - 1;
		while(low<=high) {
			int mid = (low + high ) / 2;
			if(a[mid] == b)
				return mid;
			else if(a[mid] > b)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}
	public static void main(String[] args) {
		Random rand = new Random();
		int n = 1000;
		ArrayList<Integer> an = new ArrayList<Integer>();
		for(int i = 0; i <n ; i ++)
			an.add(rand.nextInt(100));
		int b = rand.nextInt(100);
		an.sort(null);
		Integer[] a = new Integer[an.size()];
		an.toArray(a);
	}
}
