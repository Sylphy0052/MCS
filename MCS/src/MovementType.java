
public enum MovementType {
	ACTIVE,
	PASSIVE,
	NONE;
	
	public static MovementType getMovementType(String s) {
		switch(s) {
		case "ACTIVE":
			return ACTIVE;
		case "PASSIVE":
			return PASSIVE;
		case "NONE":
			return NONE;
		default:
			System.exit(1);
			return null;
		}
	}
}
