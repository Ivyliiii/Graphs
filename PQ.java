import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PQ<E> {
	
	private ArrayList<Node> list = new ArrayList<Node>();
	
	// function that allows us to immediately print it out
	public String toString() {
		String output = "";
		for(Node n: list) {
			output += n.data.toString();
			output += "  ";
		}
		return output;
	}
	
	// this is the add function that adds the nodes in terms of their frequency
	// I tried to do binary search, but it didn't really work for me
	public void add(E e, int priority) {
		// edge case for when there nothing in the list
		if(list.size() == 0) {
			list.add(new Node(e, priority));
			return;
		}
		// edge case for when there are only 1 element in the list
		if(list.size() == 1) {
			if(priority > list.get(list.size()-1).priority) {
				list.add(new Node(e, priority));
				return;
			}
			else {
				list.add(0, new Node(e, priority));
				return;
			}
		}

	
        for(int i = 0; i < list.size(); i++) {
			if(list.get(i).priority > priority) {
				list.add(i, new Node(e, priority));
				return;
			}
		}
		list.add(new Node(e, priority));
		
	}
	
	public void put(E e, int priority) {
		if(list.contains(e)) {
			int index = list.indexOf(e);
			if (priority < list.get(index).priority) {
				list.remove(index);
				// edge case for when there are only 1 element in the list
				if(list.size() == 1) {
					if(priority > list.get(list.size()-1).priority) {
						list.add(new Node(e, priority));
						return;
					}
					else {
						list.add(0, new Node(e, priority));
						return;
					}
				}
		        for(int i = 0; i < list.size(); i++) {
					if(list.get(i).priority > priority) {
						list.add(i, new Node(e, priority));
						return;
					}
				}
				list.add(new Node(e, priority));
			}
		}
		else {
			list.add(new Node(e, priority));
			return;
		}

	}
	
	// function for removing the first term of a list
	public E pop() {
		Node temp =  list.get(0);
		list.remove(0);
		return temp.data;
	}
	
	// returns the priority of an element
	public int getPriority() {
		return list.get(0).priority;
	}
	
	// this is just the node class and a node contains a random type of data and the priority
	public class Node {	
		E data;
		int priority;
			
		public Node(E data, int inte) {
			this.data = data;
			priority = inte;
		}
	}
	
	// returns the size of the list
	public int size() {
		return list.size();
	}
	
}