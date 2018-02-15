
public class Position {
	private int x;
	private int y;
	private int z;
	
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString() {
		return String.format("(%d, %d, %d)", x, y, z);
	}
}
