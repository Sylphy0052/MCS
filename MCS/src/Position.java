
public class Position {
	private int x;
	private int y;
	private int z;
	
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Position pos) {
		this.x += pos.getX();
		this.y += pos.getY();
		this.z += pos.getZ();
	}
	
	public Position add(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public void set(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString() {
		return String.format("(%d, %d, %d)", x, y, z);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public boolean equal(Position p) {
		if(p.getX() == x && p.getY() == y && p.getZ() == z) {
			return true;
		}
		return false;
	}
	
	
}
