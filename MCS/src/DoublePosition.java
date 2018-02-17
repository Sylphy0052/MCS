
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
	
	public DoublePosition toDouble(Position pos){
		return new DoublePosition(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public Position toRoundInt() {
		return new Position((int)Math.round(x), (int)Math.round(y), (int)Math.round(z));
	}
	
	public void addDouble(DoublePosition other){
		this.x += other.getX();
		this.y += other.getY();
		this.z += other.getZ();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
}
