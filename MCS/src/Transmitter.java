import java.util.HashSet;

public class Transmitter extends NanoMachine {
	private int lastMsgID;
	public Transmitter(NanoMachineParam nmp, MoleculeParam mp) {
		super(nmp, mp);
	}
	
	public HashSet<Molecule> createMolecule() {
		HashSet<Molecule> mols = new HashSet<Molecule>();
		for(int i = 0; i < mp.getNumMolecule(); i++) {
			mols.add(new Molecule(nmp.getReleasePosition(), mp, currentMsgID));
		}
		return mols;
	}
	
	public boolean receive(Molecule m) {
		if(lastMsgID == m.getCurrentMsgID()) {
			// finish
			currentMsgID = 0;
		}
		if(currentMsgID == m.getCurrentMsgID()) {
			currentMsgID++;
			return true;
		}
		return false;
	}
	
	public void setLastMsgID(int lastMsgID) {
		this.lastMsgID = lastMsgID;
	}
	
	public int getCurrentMsgID() {
		return currentMsgID;
	}
}
