//
//public class NoiseMoleculeCreator extends MoleculeCreator {
//	
//	public NoiseMoleculeCreator(SimParams params, Medium medium) {
//		super(params.getNoiseMoleculeParam(), medium);
//	}
//	
//	public void createMolecule() {
//		int X = medium.getX();
//		int Y = medium.getY();
//		int Z = medium.getZ();
//		for(int i = 0; i < ((MoleculeParam)mp).getNumMolecule(); i++) {
//			int x = (int)((Math.random() * X) - (X / 2));
//			int y = (int)((Math.random() * Y) - (Z / 2));
//			int z = (int)((Math.random() * Y) - (Z / 2));
//			NoiseMolecule nm = new NoiseMolecule(new Position(x, y, z), mp);
//			medium.addNoiseMolecule(nm);
//		}
//	}
//}
