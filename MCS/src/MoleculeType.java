
public enum MoleculeType {
	INFO,
	ACK,
	NOISE;
	
	public static MoleculeType getMoleculeType(String s) {
		switch(s) {
		case "INFO":
			return INFO;
		case "ACK":
			return ACK;
		case "NOISE":
			return NOISE;
		default:
			System.exit(1);
			return null;
		}
	}
}
