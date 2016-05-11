import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EX17 {

	public static void main(String[] args) {
		int nodeNumber = 1;
		int capacity = 0;
		int numItems;
		double bound = 0;
		double achievableProfit;
		int currentItemBeingUsed = 1;
		Node currentNode = new Node();
		
		ArrayList<Node> node = new ArrayList<Node>();
		ArrayList<Item> items = new ArrayList<Item>();
		// TODO Auto-generated method stub
//		Scanner keys = new Scanner(System.in);
//		System.out.println("Enter the file to be opened.");
//		String filename = keys.nextLine();
//		keys.close();
//		File file = new File(filename);
		File file = new File("sampletest.txt");
		try {
			Scanner inputFile = new Scanner((file));
			capacity = inputFile.nextInt();
			numItems = inputFile.nextInt();
			System.out.println("The capacity of knapsack is " + capacity + ". There are "
					+ numItems + " items in the sack.");
			int itemNumber = 1;
			while (inputFile.hasNext()){
				double profit = inputFile.nextInt();
				double weight = inputFile.nextInt();
				System.out.println(itemNumber + ": " + profit + ", " + weight);
				Item newItem = new Item(itemNumber, profit, weight);
				items.add(newItem);
				itemNumber ++;
			}
			inputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			capacity = 0;
			numItems = 0;
			System.exit(0);
		}
//		System.out.println(items);
		if(capacity > 0){
			bound = calculateBound(items, capacity, 0);
//			bound = calculateBound(items, capacity, 4);
			System.out.println("current bound is " + bound);
			Node node0 = new Node();
			node0.nodeId = nodeNumber;
			nodeNumber++;
			node0.level = currentItemBeingUsed-1;
			node0.bound = calculateBound(items, capacity, 0);
			currentNode = node0;
//			node.add(node0);
//			System.out.println("Exploring [" + node0.toString() + "]");
			//building the nodes
			while(currentItemBeingUsed < numItems){
				int tempCurrentItem = currentItemBeingUsed-1;
//				System.out.println("Processing item " + currentItemBeingUsed);
				//			Node tempNodeUse = new Node();
				//			Node tempNodeNoUse = new Node();
//				if(currentItemBeingUsed == 0){
//					//				tempNodeUse.level = tempCurrentItem;
//					//				tempNodeUse.itemsUsed.add(currentItemBeingUsed);
//					//				tempNodeNoUse.itemsSkipped.add(currentItemBeingUsed);
//					//				tempNodeUse.bound = calculateBound(items, capacity, tempCurrentItem);
//					//				tempNodeNoUse.bound = calculateBound(items, capacity, tempCurrentItem + 1);
//					double useItem = calculateBound(items, capacity, tempCurrentItem);
//					double noUseItem = calculateBound(items, capacity, tempCurrentItem + 1);
//					System.out.println("if using " + currentItemBeingUsed + ". The bound is " + useItem );
//					System.out.println("if not using " + currentItemBeingUsed + ". The bound is " + noUseItem);
//					currentItemBeingUsed++;
//				}
//				else if(currentItemBeingUsed > 0){
//					double useItem = calculateBound(items, capacity, tempCurrentItem);
//					if(tempCurrentItem + 1 <= items.size()){
//						double noUseItem = calculateBound(items, capacity, tempCurrentItem + 1);
//						System.out.println("if using " + currentItemBeingUsed + ". The bound is " + useItem );
//						System.out.println("if not using " + currentItemBeingUsed + ". The bound is " + noUseItem);
//						currentItemBeingUsed++;
//					}
//					else{
//						System.out.println("didn't do the no use part");
//					}
//				}
				System.out.println("Exploring [" + currentNode + "]");
				if(node.size() == 0){
					//build don't use item node
					Node nodeNo = new Node();
					nodeNo.nodeId = nodeNumber;
					nodeNo.level = currentItemBeingUsed;
					nodeNumber++;
					nodeNo.itemsSkipped.add(tempCurrentItem);
					nodeNo.bound = calculateBound(items, capacity, tempCurrentItem + 1);
					nodeNo.profit = calProfit(nodeNo, items);
					nodeNo.weight = calWeight(nodeNo, items);
					System.out.println("Left child is [" + nodeNo.toString() + "]");
					goFurtherWithNode(nodeNo, capacity);
					//build use item node
					Node nodeYes = new Node();
					nodeYes.nodeId = nodeNumber;
					nodeYes.level = currentItemBeingUsed;
					nodeNumber++;
					nodeYes.itemsUsed.add(currentItemBeingUsed);
					nodeYes.bound = calculateBound(items, capacity, tempCurrentItem);
					nodeYes.profit = calProfit(nodeYes, items);
					nodeYes.weight = calWeight(nodeYes, items);
					System.out.println("Right child is [" + nodeYes.toString() + "]");
					goFurtherWithNode(nodeYes, capacity);
					if(nodeNo.bound > nodeYes.bound){
						System.out.println("explore node " + nodeNo.nodeId + " further.");
						currentNode = nodeNo;
						node.add(nodeYes);
					}
					else if(nodeYes.bound > nodeNo.bound){
						System.out.println("explore node " + nodeYes.nodeId + " further.");
						currentNode = nodeYes;
						node.add(nodeNo);
					}
					currentItemBeingUsed++;
					System.out.println();
					
				}
				else{
					//build don't use item node
					Node nodeNo = new Node(currentNode);
					nodeNo.nodeId = nodeNumber;
					nodeNo.level = currentItemBeingUsed;
					nodeNumber++;
					nodeNo.itemsSkipped.add(tempCurrentItem);
					nodeNo.bound = calculateBound(items, capacity, nodeNo);
					nodeNo.profit = calProfit(nodeNo, items);
					nodeNo.weight = calWeight(nodeNo, items);
					System.out.println("Left child is [" + nodeNo.toString() + "]");
					goFurtherWithNode(nodeNo, capacity);
					//build use item node
					Node nodeYes = new Node(currentNode);
					nodeYes.nodeId = nodeNumber;
					nodeYes.level = currentItemBeingUsed;
					nodeNumber++;
					nodeYes.itemsUsed.add(currentItemBeingUsed);
					nodeYes.bound = calculateBound(items, capacity, nodeYes);
					nodeYes.profit = calProfit(nodeYes, items);
					nodeYes.weight = calWeight(nodeYes, items);
					System.out.println("Right child is [" + nodeYes.toString() + "]");
					goFurtherWithNode(nodeYes, capacity);
					if(nodeNo.bound > nodeYes.bound){
						System.out.println("explore node " + nodeNo.nodeId + " further.");
						currentNode = nodeNo;
						node.add(nodeYes);
					}
					else if(nodeYes.bound > nodeNo.bound){
						System.out.println("explore node " + nodeYes.nodeId + " further.");
						currentNode = nodeYes;
						node.add(nodeNo);
					}
					currentItemBeingUsed++;
					System.out.println();
				}
			}

		}
		else{
			System.out.println("Nothing to work on because the capacity is " + capacity);
			System.exit(0);
		}
	}
	
	/**
	 * finds the bound for the listOfItem
	 * @param listOfItems A list of Object Item in an ArrayList
	 * @param capacity The capacity of the knapsack.
	 */
	public static double calculateBound( ArrayList<Item> listOfItem, int capacity, int startIndex ){
		double tempCap = 0;
		int itemNum = startIndex;
		double bound = 0;
		while (tempCap < capacity && itemNum <= (listOfItem.size()-1)){
			Item tempItem = listOfItem.get(itemNum);
//			System.out.println(tempItem.toString());
			tempCap = tempItem.weight + tempCap;
			bound = tempItem.profit + bound;
//			System.out.println("Capacity at the momement: " + tempCap + " "
//					+ "the bound at the momement: " + bound);
			if(tempCap > capacity){
//				System.out.println("Using a ratio of profit to weight.");
				bound = bound - tempItem.profit;
				tempCap = tempCap - tempItem.weight;
				double ratio = capacity - tempCap;
				double itemWeightToProfit = tempItem.profit / tempItem.weight;
				double ratioOfProfit = itemWeightToProfit * ratio;
				bound = ratioOfProfit + bound;
				tempCap = tempCap + ratio;
			}
			itemNum ++;
			
		}
//		System.out.println("Capacity at the momement: " + tempCap + " "
//				+ "the bound at the momement: " + bound);
//		System.out.println(bound);
		return bound;
	}
	// copying the list of items isn't correctly copying 
	public static double calculateBound( ArrayList<Item> listOfItem, int capacity, Node n ){
		//copy list of items
		ArrayList<Item> copyOfList = new ArrayList<Item>();
		for(int i = 0; i <= listOfItem.size()-1; i++){
			for(int j=0; j <= n.itemsSkipped.size()-1; j++){
				int skipNumber = n.itemsSkipped.get(j);
				if(skipNumber-1 == i ){
					System.out.println("not adding to this list " + skipNumber);
				}
				else{
					copyOfList.add(listOfItem.get(i));
				}
			}
//			copyOfList.add(listOfItem.get(i));
		}
		for(Item i : copyOfList){
			System.out.println(i.toString());
		}
//		System.out.println(copyOfList.size());
		double tempCap = 0;
		int itemNum = 0;
		double bound = 0;
		while (tempCap < capacity && itemNum <= (copyOfList.size()-1)){
			Item tempItem = copyOfList.get(itemNum);
//			System.out.println(tempItem.toString());
			tempCap = tempItem.weight + tempCap;
			bound = tempItem.profit + bound;
//			System.out.println("Capacity at the momement: " + tempCap + " "
//			+ "the bound at the momement: " + bound);
			if(tempCap > capacity){
//				System.out.println("Using a ratio of profit to weight.");
				bound = bound - tempItem.profit;
				tempCap = tempCap - tempItem.weight;
				double ratio = capacity - tempCap;
				double itemWeightToProfit = tempItem.profit / tempItem.weight;
				double ratioOfProfit = itemWeightToProfit * ratio;
				bound = ratioOfProfit + bound;
				tempCap = tempCap + ratio;
			}
			itemNum ++;

		}
		return bound;
	}
	
	public static double calProfit(Node n, ArrayList<Item> items){
		double p = 0;
		for(Integer itemNum : n.itemsUsed){
			p = p + items.get(itemNum-1).profit;
		}
		return p;
	}
	
	public static double calWeight(Node n, ArrayList<Item> items){
		double w = 0;
		for(Integer itemNum : n.itemsUsed){
			w = w + items.get(itemNum-1).weight;
		}
		return w;
	}
	
	public static void goFurtherWithNode(Node n, int capacity){
		if(n.weight > capacity){
			System.out.println("Prune because too heavy.");
		}
		else if(n.weight < capacity){
			System.out.println("Explore further.");
		}
		else if(n.weight == capacity){
			System.out.println("Hit capacity exactly so don't explore further.");
		}
	}
	
	public static void copyItemsToTemp(ArrayList<Item> listOfItem, int capacity, Node n){
		//copy list of items
		ArrayList<Item> copyOfList = new ArrayList<Item>();
		for(int i = 0; i < listOfItem.size()-1; i++){
			for(Integer itemSkipped : n.itemsSkipped){
				if(itemSkipped-1 == i){
					System.out.println("not adding to this list");
				}
				else{
					copyOfList.add(listOfItem.get(i));
				}
			}
		}
		for(Item i : copyOfList){
			System.out.println(i.toString());
		}
		System.out.println(copyOfList.size());
	}
	
	public void explore(){
		
	}
}