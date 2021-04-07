class array{
	arrayNode next;
}
class arrayNode{
	int element;
	arrayNode next;
}
class ElemType{}
public class practice1 {
	public static final String ElemType = "array";
	public array merge(array a, array b) {
		arrayNode p = a.next;
		while(p.next != null) 
			p = p.next;
		p.next = b.next;
		for(p = a.next; p.next != null; p = p.next) 
			for(arrayNode q = p.next; q.next != null; q = q.next) 
				if(p.element < q.element) {
					int temp =  p.element;
					p.element = q.element;
					q.element = temp;
				}
		return a;
	}
	public void change(arrayNode list, arrayNode p){
		arrayNode q = list.next;
		while(q.next != p) 
			q = q.next;
		q.next = p.next;
		p.next = p.next.next;
		q.next.next = p;
	}
	public void delete_same(arrayNode del) {
		arrayNode p = del.next;
		while(p != null) {
			while(p.next.element != del.element)
				p = p.next;
			p.next = p.next.next;
			p = p.next;
		}
	}
	public void single(array L) {
		arrayNode p = L.next;
		while(p.next != null) {
			for(arrayNode q = p.next; q != null; q = q.next)
				if(q.element == p.element)
					delete_same(q);
			p = p.next;
		}
	}
	public arrayNode Reverse(arrayNode node){
		if (node.next == null)
			return node;
	 	arrayNode prevNode = Reverse(node.next);
	 	arrayNode temp = node.next; 
	 	temp.next = node;
	 	node.next = null;
		return prevNode;
	 }

}
