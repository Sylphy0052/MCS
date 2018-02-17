import java.util.HashSet;

public class VectorPosition {
	private Position startPosition;
	private Position endPosition;
	private HashSet<Position> vectorPosition;
	
	public VectorPosition(Position startPosition, Position endPosition) {
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		vectorPosition = new HashSet<Position>();
		newVectorPosition();
	}
	
	private void newVectorPosition() {
		if(startPosition.equal(endPosition)) { return; }
		
		int x1 = startPosition.getX();
		int y1 = startPosition.getY();
		int z1 = startPosition.getZ();

		int x2 = endPosition.getX();
		int y2 = endPosition.getY();
		int z2 = endPosition.getZ();
		
		int delX = (x2 - x1);
		int delY = (y2 - y1);
		int delZ = (z2 - z1);
		
		double unitLength = Math.sqrt(delX*delX + delY*delY + delZ*delZ);
		
		double x = ((double)delX/unitLength);
		double y = ((double)delY/unitLength);
		double z = ((double)delZ/unitLength);
		
		DoublePosition direction = new DoublePosition(x, y, z);
		DoublePosition currentPos = direction.toDouble(startPosition);
		
		while(!currentPos.toRoundInt().equal(endPosition)) {
			vectorPosition.add(currentPos.toRoundInt());
			currentPos.addDouble(direction);
		}
		vectorPosition.add(currentPos.toRoundInt());
	}
	
	public boolean contain(Position pos) {
		for(Position p: vectorPosition) {
			if(pos.equal(p)) {
				return true;
			}
		}
		return false;
	}
	
	public HashSet<Position> getPosition() {
		return vectorPosition;
	}

}
