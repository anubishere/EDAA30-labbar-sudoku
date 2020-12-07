package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class mountain extends Fractal {
	private  Point a;
	private  Point b;
	private  Point c;
	private int dev;
	public HashMap<Side, Point> map;

	
	public mountain (int dev,Point a, Point b, Point c) {
		super();
		this.a=a;
		this.b=b;
		this.c=c;
		this.dev=dev;
		map= new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Kochs berg";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		fractalMountain(turtle, order,dev,  a, b, c);
	}
	
	private void fractalMountain(TurtleGraphics turtle, int order,int dev, Point a, Point b, Point c) {
		if(order==0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.penDown();
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		}
		else {
			
			double offset1=RandomUtilities.randFunc(dev);
			double offset2=RandomUtilities.randFunc(dev);
			double offset3=RandomUtilities.randFunc(dev);
			dev /=2;
			
			Point mLeft= getMiddle(a,b, offset1);
			Point mRight= getMiddle(b,c, offset2);
			Point mBot = getMiddle(a,c, offset3);
			
			//top
			fractalMountain(turtle, order-1,dev, mLeft, b, mRight);
			
			//middle
			fractalMountain(turtle, order-1,dev,  mLeft, mRight, mBot);
			
			//left
			fractalMountain(turtle, order-1,dev, a, mLeft, mBot);
			
			//right
			fractalMountain(turtle, order-1,dev, mBot, mRight, c);
			
			
			
		}
	}
	
	private Point getMiddle(Point a, Point b, double offset) {
		
		Side temp = new Side(a, b);
		if(map.containsKey(temp)) {
			Point point= map.get(temp);
			map.remove(temp);
			return point;
		}else {
		int yM=((a.getY()+b.getY())/2)+(int)offset;
		int xM=(a.getX()+b.getX())/2;
		Point m= new Point (xM, yM);
		map.put(temp, m);
		return m;
		}
	}

}
