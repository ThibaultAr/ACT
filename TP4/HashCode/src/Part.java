
public class Part implements Comparable<Part> {
	
	private int x1, y1, x2, y2;
	
	public Part(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public int surface() {
		return ((x2 - x1) + 1) * ((y2 - y1) + 1);
	}
	
	public int getX1() {
		return this.x1;
	}
	
	public int getX2() {
		return this.x2;
	}
	
	public int getY1() {
		return this.y1;
	}
	
	public int getY2() {
		return this.y2;
	}
	
	public String toString() {
		return x1 + " " + y1 + " " + x2 + " " + y2; 
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Part) {
			Part part = (Part) obj;
			return part.x1 == x1 && part.x2 == x2 && part.y1 == y1 && part.y2 == y2;
		}
		return false;
	}
	
	public boolean isContainsOn(Part part) {
		return x1 >= part.x1 && x1 <= part.x2 && (y1 >= part.y1 && y1 <= part.y2 || y1 <= part.y1 && y2 >= part.y2)
			|| x2 >= part.x1 && x2 <= part.x2 && (y1 >= part.y1 && y1 <= part.y2 || y1 <= part.y1 && y2 >= part.y2)
			|| (x1 >= part.x1 && x1 <= part.x2 || x1 <= part.x1 && x2 >= part.x2) && y2 >= part.y1 && y2 <= part.y2
			|| x2 >= part.x1 && x2 <= part.x2 && y2 >= part.y1 && y2 <= part.y2;
	}
	
	public boolean isStrictlyContainsOn(Part part) {
		return x1 >= part.x1 && x2 <= part.x2 && y1 >= part.y1 && y2 <= part.y2;
	}
	
	public boolean isCellContainIn(Cell cell) {
		return cell.containsOnPart(this);
	}
	
	public boolean isCellContainIn(int x, int y) {
		return this.isCellContainIn(new Cell(x, y));
	}

	public int getNbJambon(char[][] pizza) {
		int nbJambon = 0;
		for(int x = x1; x <= x2; x++) {
			for(int y = y1; y <= y2; y++) {
				if(pizza[x][y] == 'H') nbJambon++;
			}
		}
		return nbJambon;
	}
	
	@Override
	public int compareTo(Part o) {
		return o.surface() - this.surface();
	}
}
