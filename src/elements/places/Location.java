package elements.places;

import java.text.DecimalFormat;

public abstract class Location {
	
	
	public final double x;
	public double y;
	public double z;
	
	public Location(double x, double y){
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	public Location(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public abstract Location plus(double x, double y);
	
	public abstract Location plus(double x, double y, double z);
	
	public abstract double distanceTo(Location other);
	
	@Override
	public String toString() {
		DecimalFormat f = new DecimalFormat("#0.00");
		return "("+f.format(x)+","+f.format(y)+","+f.format(z)+")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Location){
			Location l = (Location) obj;
			return this.x == l.x && this.y == l.y && this.z == l.z;
		}
		else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return Double.hashCode((x*11+y)*17+z);
	}
}
