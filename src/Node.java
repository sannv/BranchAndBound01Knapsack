import java.util.ArrayList;

public class Node {
	ArrayList<Integer> itemsUsed = new ArrayList<Integer>();
	ArrayList<Integer> itemsSkipped = new ArrayList<Integer>();
//	ArrayList<Item>	items = new ArrayList<Item>();
	int nodeId;
	int level;
	double profit;
	double weight;
	double bound;
	
	public Node(){
		
	}
	
	public Node(Node n){
		for ( Integer number : n.itemsUsed){
			itemsUsed.add(number);
		}
		for ( Integer number : n.itemsSkipped){
			itemsSkipped.add(number);
		}
	}
	
	public String toString(){
		String s = "Node: " + nodeId + " items: " + itemsUsed + " level: " + level + " profit: " + profit +
	" weight: " + weight + " bound: " + bound + " items skipped: " + itemsSkipped;
		return s;
	}
	
	public String toItemsUsed(){
		return itemsUsed.toString();
	}
}
