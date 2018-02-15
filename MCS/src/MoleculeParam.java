
public class MoleculeParam {
	private int numMolecule;
	private MoleculeType moleculeType;
	private MovementType movementType;
	private int adaptiveChange;
	private double size;
	private double volume;
	
	public MoleculeParam(String param) {
		String[] ps = param.split(" ");
		numMolecule = Integer.parseInt(ps[0]);
		moleculeType = MoleculeType.getMoleculeType(ps[1]);
		if(moleculeType == MoleculeType.NOISE) {
			size = Double.parseDouble(ps[2]);
		} else {
			movementType = MovementType.getMovementType(ps[2]);
			adaptiveChange = Integer.parseInt(ps[3]);
			size = Double.parseDouble(ps[4]);
		}
		volume = Math.pow(size, 3);
	}

}
