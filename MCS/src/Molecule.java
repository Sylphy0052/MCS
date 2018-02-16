
public class Molecule {
	protected Position pos;
	protected MoleculeParam mp;
	
	public Molecule(Position pos, MoleculeParam mp) {
		this.pos = pos;
		this.mp = mp;
	}
	
	public Position getPosition() {
		return pos;
	}

}
