import java.util.HashSet;

public class Medium {
	private int X;
	private int Y;
	private int Z;
	private MolComSim sim;
	private HashSet<Molecule> movingMolecules;
	private HashSet<Molecule> molecules;
	private MediumMap map;
	private NanoMachine transmitter;
	private NanoMachine receiver;
	
	public Medium(SimParams params, MolComSim sim) {
		X = params.getMediumDimensionX();
		Y = params.getMediumDimensionY();
		Z = params.getMediumDimensionZ();
		this.sim = sim;
		molecules = new HashSet<Molecule>();
		map = new MediumMap(params);
		movingMolecules = new HashSet<Molecule>();
	}
	
	public void init() {
		// Noise
		createNoiseMolecules();
		
		// Microtubule
		// とりあえず放置
		
		// NanoMachine
		transmitter = new Transmitter(sim.getSimParams().getTransmitter(), sim.getSimParams().getInfoMoleculeParam());
		transmitter.setLastMsgID(sim.getSimParams().getNumMessages());
		map.add(transmitter);
		receiver = new Receiver(sim.getSimParams().getReceiver(), sim.getSimParams().getAckMoleculeParam());
		map.add(receiver);
		
		// Molecule
		createMolecules(transmitter);
	}
	
	public boolean nextStep() {
		HashSet<Molecule> loopMolecule = (HashSet<Molecule>) movingMolecules.clone();
		for(Molecule m: loopMolecule) {
			boolean isNanoMachine = move(m);
			if(isNanoMachine) {
				receiveMolecule(m);
			}
		}
		return transmitter.getCurrentMsgID() == 0;
	}
	
	private boolean move(Molecule m) {
		Position nextPosition = decideNextPosition(m.getPosition());
		nextPosition = checkMediumOutside(nextPosition);
		ReturnObjects ro = checkCollisionNanoMachine(m, nextPosition);
		// ナノマシーンめっけたかどうか
		if(!ro.is()) {
			nextPosition = checkCollision(m, nextPosition);
		} else {
			nextPosition = (Position)ro.getObject();
		}
		if(!m.getPosition().equal(nextPosition)) {
			map.remove(m);
			m.setPosition(nextPosition);
			map.add(m);
		}
		return ro.is();
	}
	
	private Position decideNextPosition(Position p) {
		double stepLengthX = sim.getSimParams().getStepLengthX();
		double stepLengthY = sim.getSimParams().getStepLengthY();
		double stepLengthZ = sim.getSimParams().getStepLengthZ();
		int nextX = p.getX() + (int)Math.round(Math.random() * stepLengthX * 2- stepLengthX);
		int nextY = p.getY() + (int)Math.round(Math.random() * stepLengthY * 2- stepLengthY);
		int nextZ = p.getZ() + (int)Math.round(Math.random() * stepLengthZ * 2- stepLengthZ);
		return new Position(nextX, nextY, nextZ);
	}
	
	private Position checkMediumOutside(Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if(x < -X / 2) {
			x = -X / 2;
		} else if(x > X / 2) {
			x = X / 2;
		}
		if(y < -Y / 2) {
			y = -Y / 2;
		} else if(y > Y / 2) {
			y = Y / 2;
		}
		if(z < -Z / 2) {
			z = -Z / 2;
		} else if(z > Z / 2) {
			z = Z / 2;
		}
		pos.set(x,  y, z);
		return pos;
	}
	
	public ReturnObjects checkCollisionNanoMachine(Molecule m, Position nextPos) {
		VectorPosition vp = new VectorPosition(m.getPosition(), nextPos);
		switch(m.getType()) {
		case INFO:
			for(Position p: vp.getPosition()) {
				if(receiver.getThreePosition().contain(p)) {
					return new ReturnObjects(p, true);
				}
			}
			break;
		case ACK:
			for(Position p: vp.getPosition()) {
				if(transmitter.getThreePosition().contain(p)) {
					return new ReturnObjects(p, true);
				}
			}
			break;
		}
		return new ReturnObjects(nextPos);
	}
	
	public Position checkCollision(Molecule m, Position pos) {
		if(!sim.getSimParams().isUseCollisions()) { return pos; }
		HashSet<Object> hs = map.getAtPos(pos);
		if(hs == null) { return pos; }
		double vin = m.getVolume();
		double vsum = 0.0;
		for(Object o: hs) {
			if(o instanceof Molecule) {
				vsum += ((Molecule)o).getVolume();
			}
		}
		if(vsum >= 1) {
			return m.getPosition();
		}
		double p = vsum + vin / (1 - vsum);
		if(Math.random() < p) {
			return m.getPosition();
		}
		return pos;
	}
	
	private void receiveMolecule(Molecule m) {
		switch(m.getType()) {
		case INFO:
			if(receiver.receive(m)) {
				HashSet<Molecule> mols = receiver.createMolecule();
				for(Molecule mol: mols) {
					addMolecule(mol);
				}
			}
			removeMolecule(m);
			break;
		case ACK:
			if(transmitter.receive(m)) {
				HashSet<Molecule> mols = transmitter.createMolecule();
				for(Molecule mol: mols) {
					addMolecule(mol);
				}
			}
			removeMolecule(m);
			break;
		}
	}
	
	public void addMolecule(Molecule m) {
		movingMolecules.add(m);
		molecules.add(m);
		map.add(m);
	}
	
	public void removeMolecule(Molecule m) {
		movingMolecules.remove(m);
		molecules.remove(m);
		map.remove(m);
	}
	
	public void addNoiseMolecule(NoiseMolecule nm) {
		molecules.add(nm);
		map.add(nm);
	}
	
	public void createNoiseMolecules() {
		MoleculeParam nmp = sim.getSimParams().getNoiseMoleculeParam();
		for(int i = 0; i < nmp.getNumMolecule(); i++) {
			int x = (int)((Math.random() * X) - (X / 2));
			int y = (int)((Math.random() * Y) - (Z / 2));
			int z = (int)((Math.random() * Y) - (Z / 2));
			NoiseMolecule nm = new NoiseMolecule(new Position(x, y, z), nmp);
			addMolecule(nm);
		}
	}
	
	public void createMolecules(NanoMachine nm) {
		HashSet<Molecule> mols = nm.createMolecule();
		for(Molecule m: mols) {
			addMolecule(m);
		}
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getZ() {
		return Z;
	}	
	
	public HashSet<Molecule> getMolecules() {
		return molecules;
	}
}
