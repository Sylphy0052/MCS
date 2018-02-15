import java.util.regex.Pattern;

public class IntermediateNodeParam {
	private Position centerPosition;
	private int size;
	private Position infoReleasePosition;
	private Position ackReleasePosition;
	public IntermediateNodeParam(String param) {
		Pattern p = Pattern.compile("[,()\\s ]+");
		String[] ps = p.split(param);
		centerPosition = new Position(Integer.parseInt(ps[1]), Integer.parseInt(ps[2]), Integer.parseInt(ps[3]));
		size = Integer.parseInt(ps[4]);
		infoReleasePosition = new Position(Integer.parseInt(ps[5]), Integer.parseInt(ps[6]), Integer.parseInt(ps[7]));
		ackReleasePosition = new Position(Integer.parseInt(ps[8]), Integer.parseInt(ps[9]), Integer.parseInt(ps[10]));
	}

}
