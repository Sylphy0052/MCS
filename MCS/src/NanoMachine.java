import java.util.HashSet;

public class NanoMachine {
	protected NanoMachineParam nmp;
	protected MoleculeParam mp;
	protected ThreePosition pos;
	protected int currentMsgID;
	
	public NanoMachine(NanoMachineParam nmp, MoleculeParam mp) {
		this.nmp = nmp;
		this.mp = mp;
		this.currentMsgID = 1;
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
	
	public HashSet<Molecule> createMolecule() {
		System.out.println("Error");
		return new HashSet<Molecule>();
	}
	
	public boolean receive(Molecule m) {return false;}
	
	public HashSet<Position> getPosition() {
		return pos.getPosition();
	}
	
	public ThreePosition getThreePosition() {
		return pos;
	}
	
	public int getNumMolecule() {
		return mp.getNumMolecule();
	}
	
	public Position getReleasePosition() {
		return nmp.getReleasePosition();
	}
	
	public void setLastMsgID(int lastMsgID) {}
	
	public int getCurrentMsgID() { return -1; }

}
