package fractal;



import koch.Koch;
import mountain.Point;
import mountain.mountain;


public class FractalApplication {
	public static void main(String[] args) {
		Point a= new Point(100,500);
		Point b= new Point(200,100);
		Point c= new Point(400,500);

		mountain m = new mountain(50,a, b , c);
		
		Fractal[] fractals = new Fractal[2];
		fractals[0]= m;
		fractals[1] = new Koch(300);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
