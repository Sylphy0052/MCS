
public class ReturnObjects {
	public Object o;
	public boolean flg; 
	
	public ReturnObjects(Object o) {
		this.o = o;
		this.flg = false;
	}
	
	public ReturnObjects(Object o, boolean flg) {
		this.o = o;
		this.flg = flg;
	}
	
	public Object getObject() {
		return o;
	}
	
	public boolean is() {
		return flg;
	}

}
