package greedy;
import java.util.*;
public class Cokkies {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan  = new Scanner(System.in);
		int n = scan.nextInt();
		int k = scan.nextInt();
		ArrayList<Integer> s = new 	ArrayList<Integer>();
		int sum = 0;
		for(int i = 0; n > 0; i++) {
			s.add(i,n % 10);
			n = n/10;
			sum++;
		}
		scan.close();
		for(int i = 0; i < k ; i ++) {
			if(s.get(i) <= s.get(i+1)) {
				int j = i+1;
				while(j < sum && s.get(j) <= s.get(j+1))
					j++;
				s.remove(j);
			}
			else {
				int j = i+1;
				while(j < sum && s.get(j) >= s.get(j+1))
					j++;
				s.remove(j);
			}
		}
			
			

		
		System.out.println( );
				
		
		
		
	}

}
