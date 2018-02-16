import java.util.HashSet;

public class ThreePosition {
	private Position startPosition;
	private Position endPosition;
	private HashSet<Position> threePosition;
	
	public ThreePosition(Position startPosition, Position endPosition) {
		this.startPosition = startPosition;
		this.endPosition = endPosition;
		threePosition = new HashSet<Position>();
		newThreePosition();
	}
	
	private void newThreePosition() {
		int startX = startPosition.getX();
		int startY = startPosition.getY();
		int startZ = startPosition.getZ();
		int endX = endPosition.getX();
		int endY = endPosition.getY();
		int endZ = endPosition.getZ();
		for(int i = startX; i <= endX; i++) {
			for(int j = startY; j <= endY; j++) {
				for(int k = startZ; k <= endZ; k++) {
					threePosition.add(new Position(i, j, k));
				}
			}
		}
	}
	
	public boolean contain(Position pos) {
		for(Position p: threePosition) {
			if(pos == p) {
				return true;
			}
		}
		return false;
	}
	
	public HashSet<Position> getPosition() {
		return threePosition;
	}

}
