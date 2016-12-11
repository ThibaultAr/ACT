import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree {
	
	private List<Tree> children;
	private Part part;
	
	public Tree() {
		this(null);
	}
	
	public Tree(Part part) {
		this.children = new ArrayList<Tree>();
		this.part = part;
	}
	
	public void addChild(Tree tree) {
		this.children.add(tree);
	}
	
	public List<Part> bestList() {
		if(this.children.isEmpty()) {
			return new LinkedList<Part>();
		} else {
			List<Part> bestList = new LinkedList<Part>();
			int bestScore = 0;
			
			for(Tree tree : this.children) {
				int score = tree.part.surface();
				List<Part> actual = tree.bestList();
				
				for(Part part : actual) {
					score += part.surface();
				}
				
				if(score > bestScore) {
					bestScore = score;
					bestList = actual;
					bestList.add(tree.part);
				}
			}
			
			return bestList;
		}
	}
	
	public void free() {
		for(Tree tree : this.children) {
			tree.free();
			this.children = null;
		}
	}
	
	public void construct(List<Part> parts) {
		if(!parts.isEmpty()) {
			for(Part part : parts) {
				Tree tree = new Tree(part);
				this.children.add(tree);
				
				List<Part> childrenParts = new LinkedList<Part>();
				
				for(Part localPart : parts) {
					if(!localPart.isContainsOn(part) && !part.isContainsOn(localPart) && localPart != part) childrenParts.add(localPart);
				}
				
				tree.construct(childrenParts);
			}
		}
	}
}
