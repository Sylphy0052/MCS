import java.util.regex.Pattern;

public class MicrotubuleParam {
	private Position startPosition;
	private Position endPosition;
	
	public MicrotubuleParam(String param) {
		Pattern p = Pattern.compile("[,()\\s ]+");
		String[] ps = p.split(param);
		startPosition = new Position(Integer.parseInt(ps[1]), Integer.parseInt(ps[2]), Integer.parseInt(ps[3]));
		endPosition = new Position(Integer.parseInt(ps[4]), Integer.parseInt(ps[5]), Integer.parseInt(ps[6]));
	}

}
