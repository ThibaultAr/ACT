
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
	
	public boolean containsOnPart(int[] parts) {
		return this.containsOnPart(parts[0], parts[1], parts[2], parts[3]);
	}
}
