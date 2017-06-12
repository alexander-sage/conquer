package interfaces.menus;

import static interfaces.KeyReader.isDown;
import static interfaces.KeyReader.isUp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import console.Coordinate;
import console.Screen;
import driver.Player;
import interfaces.BorderedWindow;
import interfaces.KeyReader;
import interfaces.Pane;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import utils.StringUtils;

public class MenuBox implements BorderedWindow{
	

	
	
	
	List<String> entries;
	int current = 0;
	int cameraIndex = 0;
	Pane pane;
	
	public MenuBox drawBorder(){
		pane.drawBorder();
		return this;
	}
	
	public MenuBox(Pane pane){
		this.pane = pane;
		this.entries = new ArrayList<>();
	}
	
	public MenuBox(Pane pane, Collection<String> entries){
		this(pane);
		addEntries(entries);
		pane.setContent(getMenuStrings(), CSIColor.WHITE);
	}
	
	public MenuBox addEntries(String[] newEntries){
		for(String s:newEntries){
			entries.add(s);
		}
		return this;
	}
	
	public MenuBox addEntries(Collection<String> newEntries) {
		entries.addAll(newEntries);
		return this;
	}
	
	public MenuBox addEntry(String entry){
		entries.add(entry);
		return this;
	}
	
	public int indexOf(String entry){
		return entries.indexOf(entry);
	}
	
	public boolean removeEntry(String entry){
		return entries.remove(entry);
	}
	
	public String removeEntry(int index){
		return entries.remove(index);
	}
	
	public MenuBox setEntries(List<String> entries){
		this.entries = entries;
		return this;
	}
	
	
	@Override
	public String toString() {
		return StringUtils.delimitedBy("\n", getMenuStrings());
	}
	
	
	
	public List<String> getMenuStrings(){
		List<String> output = new ArrayList<>();
		int width = pane.getWidth();
		int height = pane.getHeight();
		
		int room = width-2;
		
		
		
		
		for(int i=0; i<height; i++){
			StringBuilder line = new StringBuilder("  ");
			if(entries.size()>i){
				line.append(StringUtils.trimTo(room, entries.get(i+cameraIndex)));
				
			}
			
			output.add(line.toString());
		}
		
		
		
		return output;
	}
	
	
	public void displayAt(Coordinate position){
		Coordinate temp = this.pane.getPosition();
		this.pane.setPosition(position);
		display();
		this.pane.setPosition(temp);
	}
	
	public void display(){
		pane.drawBorder();
		pane.setContent(getMenuStrings(), CSIColor.WHITE);
		pane.display();
	}
	
	public int runAt(Coordinate position){
		Pane temp = pane;
		pane = new Pane(temp.getHeight(), temp.getWidth(), position);
		current =  run();
		pane = temp;
		return current;
	}
	
	public int run(){
		
	
		if(current>= entries.size()){
			current = 0;
		}
		
		display();
		
		boolean done = false;
		printCursor();
		while(!done){
			
			
			
			
			done = moveThroughMenu();
			
			
			
		}
		return current;
	}
	
	void printCursor(){
		Screen screen = pane.getScreen();
		Coordinate position = pane.getPosition();
		screen.printer.printAt(position.plus(0, current-cameraIndex), ">");
		screen.refresh();
	}
	
	
	/**
	 * A method that increases the index of the cursor and scrolls up and down through the menu as necessary.
	 * @return Whether the menu is done being executed
	 */
		
	boolean moveThroughMenu(){
		
		Screen screen = pane.getScreen();
		Coordinate position = pane.getPosition();
		
		
		
		
		CharKey input = Player.getPlayer().getScreen().inkey();
		boolean done = false;
		if( isUp(input)){
			if(current > 0){
				screen.printer.printAt(position.plus(0, current-cameraIndex) ," ");
				current--;
				if(current-cameraIndex<0){//scroll
					cameraIndex--;
					display();
				}
			}
			else{
				screen.printer.printAt(position.plus(0, current-cameraIndex) ," ");
				current = entries.size()-1;
				if(entries.size()>pane.getHeight()){
					cameraIndex = current-pane.getHeight()+1;
					display();
				}
			}
		}
		else if(isDown(input)){
			if(current < entries.size()-1){
				screen.printer.printAt(position.plus(0, current-cameraIndex) ," ");
				current++;
				if(current-cameraIndex >= pane.getHeight()){//scroll
					cameraIndex++;
					display();
				}
			}
			else{
				current = 0;
				cameraIndex = 0;
				display();
			}
		}
		else if(KeyReader.isSame(input, CharKey.ENTER)){
			done = true;
		}
		else if(KeyReader.isSame(input,  CharKey.ESC)){
			current = -1;
			done = true;
		}
	
		printCursor();
		
		
		return done;
		
	}
}
