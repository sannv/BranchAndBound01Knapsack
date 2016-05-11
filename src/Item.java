
public class Item {
	int item;
	double profit, weight;

	public Item(int i, double p, double w) {
		item = i;
		profit = p;
		weight = w;

	}

	public String toString(){
		String s = "item: " + item + " profit: " + profit + " weight: " + weight;
		return s;
	}
}