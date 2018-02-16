import java.util.HashSet;

public class NanoMachine {
	protected NanoMachineParam nmp;
	protected MoleculeParam mp;
	protected ThreePosition pos;
	
	public NanoMachine(NanoMachineParam nmp, MoleculeParam mp) {
		this.nmp = nmp;
		this.mp = mp;
		newPosition();
	}
	
	protected void newPosition() {
		Position centerPosition = nmp.getCenterPosition();
		int size = nmp.getSize();
		int startX = centerPosition.getX() - size + 1;
		int startY = centerPosition.getY() - size + 1;
		int startZ = centerPosition.getZ() - size + 1;
		int endX = centerPosition.getX() + size - 1;
		int endY = centerPosition.getY() + size - 1;
		int endZ = centerPosition.getZ() + size - 1;
		pos = new ThreePosition(new Position(startX, startY, startZ), new Position(endX, endY, endZ));
	}
	
	public Molecule createMolecule() {
		return new Molecule(nmp.getReleasePosition(), mp);
	}
	
	public HashSet<Position> getPosition() {
		return pos.getPosition();
	}
	
	public int getNumMolecule() {
		return mp.getNumMolecule();
	}
	
	public Position getReleasePosition() {
		return nmp.getReleasePosition();
	}

}
