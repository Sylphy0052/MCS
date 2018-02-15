import java.util.regex.Pattern;

public class NanoMachineParam {
	private Position centerPosition;
	private int size;
	private Position releasePosition;
	
	public NanoMachineParam(String param) {
		Pattern p = Pattern.compile("[,()\\s ]+");
		String[] ps = p.split(param);
		centerPosition = new Position(Integer.parseInt(ps[1]), Integer.parseInt(ps[2]), Integer.parseInt(ps[3]));
		size = Integer.parseInt(ps[4]);
		releasePosition = new Position(Integer.parseInt(ps[5]), Integer.parseInt(ps[6]), Integer.parseInt(ps[7]));
	}
}
