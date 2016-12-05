
public class Part {
	
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
}
