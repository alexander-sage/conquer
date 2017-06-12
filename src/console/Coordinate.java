package console;

public class Coordinate {
	final int x, y;
	public Coordinate(Coordinate other){
		this.x = other.x;
		this.y = other.y;
	}
	
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinate plus(Coordinate c){
		return new Coordinate(x+c.x, y+c.y);
	}
	
	public Coordinate plus(int x, int y){
		return new Coordinate(this.x+x, this.y+y);
	}

	public Coordinate minus(int i, int j) {
		return plus(-i, -j);
	}
	
	public Coordinate minus(Coordinate c){
		return plus(-c.x, -c.y);
	}
}
