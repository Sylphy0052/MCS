import java.util.HashSet;

public class Receiver extends NanoMachine {
	public Receiver(NanoMachineParam nmp, MoleculeParam mp) {
		super(nmp, mp);
	}
	
	public HashSet<Molecule> createMolecule() {
		HashSet<Molecule> mols = new HashSet<Molecule>();
		for(int i = 0; i < mp.getNumMolecule(); i++) {
			mols.add(new Molecule(nmp.getReleasePosition(), mp, currentMsgID));
		}
		currentMsgID++;
		return mols;
	}
	
	public boolean receive(Molecule m) {
		if(currentMsgID == m.getCurrentMsgID()) {
			return true;
		}
		return false;
	}
}
