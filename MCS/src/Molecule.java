
public class Molecule {
	protected Position pos;
	protected MoleculeParam mp;
	protected int currentMsgID;
	
	public Molecule(Position pos, MoleculeParam mp, int currentMsgID) {
		this.pos = pos;
		this.mp = mp;
		this.currentMsgID = currentMsgID;
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public double getVolume() {
		return mp.getVolume();
	}
	
	public void setPosition(Position pos) {
		this.pos = pos;
	}
	
	public MoleculeType getType() {
		return mp.getMoleculeType();
	}
	
	public int getCurrentMsgID() {
		return currentMsgID;
	}
}
