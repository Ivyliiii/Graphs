import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Graph_V<E> {
	Vertex curr;
	ArrayList<Vertex> toVisit = new ArrayList<Vertex>();
	HashMap<Vertex, Vertex> leadsTo = new HashMap<Vertex, Vertex>();
	private HashMap<E, Vertex> vertices;
	
	private class Vertex{
		public E info;
		public HashSet<Vertex> neighbors; // unordered set of information

		public Vertex(E information) {
			info = information;
			neighbors = new HashSet<Vertex>();
		}
	}

	public Graph_V() {
		vertices = new HashMap<E, Vertex>();
	}
	
	private void addVertex(E info) {
		if(!vertices.containsKey(info)) { 
			vertices.put(info, new Vertex(info));
		}
	}
	
	public Vertex getVertex(E info){
		return vertices.get(info);
	}

	public int size() {
		return vertices.size();
	}
	
	public void removeVertex(E info) {
		for(Vertex v: vertices.get(info).neighbors) {
			v.neighbors.remove(vertices.get(info));
		}
		vertices.remove(info);
	}
	
	public String toString() {
		String yes = "";
		for(E info : vertices.keySet()) {
			yes+="[" + info + ": ";
			for(Vertex v : getVertex(info).neighbors) {
				yes+=v.info + ", ";
			}
			yes+="] ";
		}
		return yes;
	}
	
	// remove as efficient as possible
	// size
	private void connect(E info1, E info2) {
		Vertex v1 = vertices.get(info1);
		Vertex v2 = vertices.get(info2);
		if(v1 == null || v2 == null) {
			throw(new NullPointerException());
		}
		v1.neighbors.add(v2);
		v2.neighbors.add(v1);
	}
	
	public ArrayList<Vertex> backTrace(HashMap<Vertex, Vertex> leadsTo, Vertex end, Vertex start){
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		HashMap<Vertex, Vertex> map = leadsTo;
		Vertex curr = end;
		while(curr != null) {
			path.add(curr);
			curr = map.get(curr);
		}
		return path;
	}
	
	public ArrayList<Vertex> findPath(Vertex start, Vertex end) {
		toVisit.add(start);
		curr = toVisit.remove(0);
		while(curr != null) {
			System.out.println("once");
			for(Vertex n : curr.neighbors) {
				if(curr == end) {
					System.out.println("equal");
					return backTrace(leadsTo, end, start);
				}
				if(!leadsTo.containsKey(n)) {
					toVisit.add(n);
					leadsTo.put(n, curr);
					curr = toVisit.remove(0);
				}
			}
		}
		return null;
	}
	
	public static void main(String args[]) {
		Graph_V<String> maybeFriends = new Graph_V<String>();
		maybeFriends.addVertex("Andria");
		maybeFriends.addVertex("Jason");
		maybeFriends.addVertex("Julie");
		maybeFriends.addVertex("Ivy");
		maybeFriends.addVertex("Ingrid");
		maybeFriends.addVertex("Grace");
		maybeFriends.connect("Andria", "Jason");
		maybeFriends.connect("Julie", "Ingrid");
		maybeFriends.connect("Ivy", "Grace");
		maybeFriends.connect("Ivy", "Andria");
		maybeFriends.connect("Ingrid", "Julie");
		maybeFriends.connect("Julie", "Ivy");
		System.out.println(maybeFriends.toString());
		System.out.println(maybeFriends.findPath(maybeFriends.getVertex("Ingrid"), maybeFriends.getVertex("Ivy")));
	}
}
