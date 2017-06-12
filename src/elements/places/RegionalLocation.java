package elements.places;

public class RegionalLocation extends Location {

	public RegionalLocation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public RegionalLocation(double x, double y, double z){
		super(x, y, z);
	}

	public RegionalLocation plus(double x, double y){
		return new RegionalLocation(this.x+x, this.y+y, z);
	}
	
	public RegionalLocation plus(double x, double y, double z){
		return new RegionalLocation(this.x+x, this.y+y, this.z+z);
	}
	
	@Override
	public double distanceTo(Location other) {
		if(other instanceof RegionalLocation){
			double delta_x = this.x-other.x;
			double delta_y = this.y-other.y;
			return Math.sqrt(delta_x*delta_x + delta_y*delta_y);
		}
		else{
			return Double.POSITIVE_INFINITY;
		}
	}

}
