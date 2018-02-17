import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class MediumMap {
	private int mediumDimensionX;
	private int mediumDimensionY;
	private int mediumDimensionZ;
	private HashMap<Position, HashSet<Object>> grid;
	
	public MediumMap(SimParams param) {
		mediumDimensionX = param.getMediumDimensionX();
		mediumDimensionY = param.getMediumDimensionY();
		mediumDimensionZ = param.getMediumDimensionZ();
		grid = new HashMap<Position, HashSet<Object>>();
	}
	
	public void add(NanoMachine nm) {
		for(Position p: nm.getPosition()) {
			add(nm, p);
		}
	}
	
	public void add(Molecule m) {
		Position p = m.getPosition();
		add(m, p);
	}
	
	public void add(Object o, Position p) {
		HashSet<Object> hs;
		if(!grid.containsKey(p)) {
			hs = new HashSet<Object>();
		} else {
			hs = grid.get(p);
		}
		hs.add(o);
		grid.put(p, hs);
	}
	
	public void remove(Molecule m) {
		Position p = m.getPosition();
		grid.get(p).remove(m);
	}
	
	public HashSet<Object> getAtPos(Position p) {
		return grid.get(p);
	}
	
	public void printInfo() {
		for(Entry<Position, HashSet<Object>> entry: grid.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}
}
