
public class DoublePosition {
	private double x;
	private double y;
	private double z;
	
	public DoublePosition(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString() {
		return String.format("(%1$.1f, %1$.1f, %1$.1f)", x, y, z);
	}
}
