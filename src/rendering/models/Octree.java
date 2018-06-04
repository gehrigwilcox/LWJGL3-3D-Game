package rendering.models;

public class Octree {

	public static final int precision = 4; //how many sub-divisions to have
	
	
	private final boolean[] segments = new boolean[(int) Math.pow(8, precision)];
	
	
	
	public Octree(Model model){
		
	}
	
}
