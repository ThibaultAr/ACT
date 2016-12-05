import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private int[] value;
	private List<Node> arcs;
	
	public Node() {
		this.value = new int[4];
		this.arcs = new ArrayList<Node>();
	}
	
	public void addAll(List<Node> nodes) {
		this.arcs.addAll(nodes);
	}
	
	public void add(Node node) {
		this.arcs.add(node);
	}
	
	public void remove(Node node) {
		this.arcs.remove(node);
	}
	
	public int[] getValue() {
		return this.value;
	}
}
