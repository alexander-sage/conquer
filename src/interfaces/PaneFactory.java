package interfaces;

import console.Coordinate;
import console.Screen;

public class PaneFactory {
	
	
	
	
	
	public PaneFactory(){
		
	}
	
	public static Pane getMyStatsPanel(){
		return Pane.withBorder(25, 15, new Coordinate(25, 0));
	}
	
	
	
	public static Pane getOtherStatsPanel(){
		return Pane.withBorder(25, 15, new Coordinate(52, 0));
	}
	

	
	public static Pane getStandardMessagePane(){
		return Pane.withBorder(80, 8, new Coordinate(0, 17));
	}
	
	public static Pane getStandardMenuPane(){
		return Pane.withBorder(26, 15, new Coordinate(0,0));
	}

	public static Pane getStandardToolPane() {
		return Pane.withBorder(80, 4, new Coordinate(0, 14));
	}
}
