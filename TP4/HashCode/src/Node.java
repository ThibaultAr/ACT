import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private Part part;
	private List<Node> arcs;
	
	public Node() {
		this.part = null;
		this.arcs = new ArrayList<Node>();
	}
	
	public Node(Part part) {
		this.part = part;
		this.arcs = new ArrayList<Node>();
	}
	
	public Node(Node node) {
		this.part = node.part;
		this.arcs = new ArrayList<Node>(node.arcs);
	}
	
	public void addAll(List<Node> nodes) {
		for(Node node : nodes) {
			node.add(this);
		}
	}
	
	public void add(Node node) {
		if(!this.arcs.contains(node)) {
			this.arcs.add(node);
			node.arcs.add(this);
		}
	}
	
	public void remove(Node node) {
		this.arcs.remove(node);
		node.arcs.remove(node);
	}
	
	public void detach(List<Node> nodes) {
		for(Node node : nodes) {
			node.arcs.remove(this);
			if(node.arcs.contains(this)) node.detach(node.arcs);
		}
	}
	
	public Part getPart() {
		return this.part;
	}
	
	public List<Node> getArcs() {
		return this.arcs;
	}
}
