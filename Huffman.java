
package jpeg;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;



class HuffmanNode { 

	int data; 
	String c; 

	HuffmanNode left; 
	HuffmanNode right; 
} 

class MyComparator implements Comparator<HuffmanNode> { 
	public int compare(HuffmanNode x, HuffmanNode y) 
	{ 

		return x.data - y.data; 
	} 
} 

public class Huffman {
    public static HashMap<String,String> n2c = new HashMap<>();
	public static void printCode(HuffmanNode root, String s) 
	{ 
 
		if (root.left 
				== null
			&& root.right 
				== null
			&& (root.c)!="-") { 

			// c is the character in the node 
		//System.out.println(root.c + ":" + s); 
			
			n2c.put(root.c, s);
			

			return; 
		} 

		// if we go to left then add "0" to the code. 
		// if we go to the right add"1" to the code. 

		// recursive calls for left and 
		// right sub-tree of the generated tree. 
		printCode(root.left, s + "1"); 
		printCode(root.right, s + "0"); 
	} 
	

	// main function 
	public static void getHufmanCode(String[] Array,int[] charfreq) 
	{ 

		

		// number of characters. 
		int n =Array.length; 
		//String[] Array = { "a", "b", "c", "d", "e", "f" }; 
		//int[] charfreq = { 5, 9, 12, 13, 16, 45 }; 

		// creating a priority queue q. 
		// makes a min-priority queue(min-heap). 
		PriorityQueue<HuffmanNode> q 
			= new PriorityQueue<HuffmanNode>(n, new MyComparator()); 

		for (int i = 0; i < n; i++) { 

			// creating a Huffman node object 
			// and add it to the priority queue. 
			HuffmanNode hn = new HuffmanNode(); 

			hn.c = Array[i]; 
			hn.data = charfreq[i]; 

			hn.left = null; 
			hn.right = null; 

			// add functions adds 
			// the huffman node to the queue. 
			q.add(hn); 
		} 
             

		// create a root node 
		HuffmanNode root = null; 

		// Here we will extract the two minimum value 
		// from the heap each time until 
		// its size reduces to 1, extract until 
		// all the nodes are extracted. 
		while (q.size() > 1) { 

			// first min extract. 
			HuffmanNode x = q.peek(); 
			q.poll(); 

			// second min extarct. 
			HuffmanNode y = q.peek(); 
			q.poll(); 

			// new node f which is equal 
			HuffmanNode f = new HuffmanNode(); 

			// to the sum of the frequency of the two nodes 
			// assigning values to the f node. 
			f.data = x.data + y.data; 
			f.c = "-"; 

			// first extracted node as left child. 
			f.left = x; 

			// second extracted node as the right child. 
			f.right = y; 

			// marking the f node as the root node. 
			root = f; 

			// add this node to the priority-queue. 
			q.add(f); 
		} 

		// print the codes by traversing the tree 
		printCode(root, ""); 
	} 
}
