package console;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;

public class Printer {
	final ConsoleSystemInterface screen;
	
	public Printer(ConsoleSystemInterface screen){
		this.screen = screen;
	}
	
	public void printAt(Coordinate position, String output){
		screen.print(position.x, position.y, output);
	}
	
	public void printAt(Coordinate position, String output, int color){
		screen.print(position.x, position.y, output, color);
	}
	
	public void printAt(Coordinate position, String output, CSIColor color){
		screen.print(position.x, position.y, output, color);
	}
	
	public void printAt(Coordinate position, String[] output){
		for(int i=0; i<output.length; i++){
			screen.print(position.x, position.y+i, output[i]);
		}
	}
	
	public void printAt(Coordinate position, String[] output, int color){
		for(int i=0; i<output.length; i++){
			screen.print(position.x, position.y+i, output[i], color);
		}
	}
	
	public void printAt(Coordinate position, String[] output, CSIColor color){
		for(int i=0; i<output.length; i++){
			screen.print(position.x, position.y+i, output[i], color);
		}
	}
	
	public void printAt(Coordinate position, ColorString output){
		screen.print(position.x, position.y, output.getValue(), output.getColor());
	}
	
	public void printAt(Coordinate position, ColorString[] output){
		for(int i=0; i<output.length; i++){
			screen.print(position.x, position.y+i, output[i].getValue(), output[i].getColor());
		}
	}

	public void refresh() {
		screen.refresh();
		
	}
}
