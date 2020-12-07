package mountain;

public class Side {
	
	public Point  a;
	public Point  b;
	public Point m;
	public Side(Point a, Point b) {
		this.a=a;
		this.b=b;
	}
	
	@Override
	public boolean equals(Object x) {
		Side side= (Side) x;
		if((a==side.a||a==side.b)&&(b==side.b||b==side.a)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
		}
	
	
	
	
	
}
