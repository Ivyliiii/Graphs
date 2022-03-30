import java.util.ArrayList;

public class LocationGraphs {
	LabeledGraph<String, Integer> lb = new LabeledGraph<String, Integer>();
	
	public ArrayList<String> DijkstraAlgorithm(String start, String end) {
		return lb.DijkstraAlgorithm(start, end);
	}
	
	public static void main(String[] args) {
		LocationGraphs run = new LocationGraphs();
		run.lb.addVertex("A");
		run.lb.addVertex("B");
		run.lb.addVertex("C");
		run.lb.addVertex("D");
		run.lb.addVertex("E");
		run.lb.addVertex("F");
		run.lb.addVertex("H");
		run.lb.connect("A", "B", 2);
		run.lb.connect("B", "C", 3);
		run.lb.connect("C", "D", 18);
		run.lb.connect("D", "E", 2);
		run.lb.connect("E", "F", 2);
		run.lb.connect("F", "A", 3);
		System.out.println(run.DijkstraAlgorithm("A", "D"));
	}
}
