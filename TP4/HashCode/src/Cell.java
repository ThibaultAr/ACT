
public class Cell {
	
	private int x, y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean containsOnPart(int x1, int y1, int x2, int y2) {
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}
	
	public boolean containsOnPart(Part part) {
		return this.containsOnPart(part.getX1(), part.getY1(), part.getX2(), part.getX2());
	}
}
