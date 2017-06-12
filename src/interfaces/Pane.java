package interfaces;

import java.util.ArrayList;
import java.util.List;

import console.ColorString;
import console.Coordinate;
import console.Printer;
import console.Screen;
import driver.Player;
import net.slashie.libjcsi.CSIColor;
import utils.StringUtils;

public class Pane {
	private Coordinate position;
	private int height; 
	private int width;
	private List<ColorString> content;
	final private Screen screen;
	
	public static Pane withBorder(int width, int height, Coordinate position){
		return new Pane(width-2, height-2, position.plus(1,1));
	}
	
	public static Pane withBorder(int width, int height, Coordinate position, List<String> content, CSIColor color){
		return new Pane(width-2, height-2, position.plus(1,1), content, color);
	}
	
	public static Pane withBorder(int width, int height, Coordinate position, List<ColorString> content){
		return new Pane(width-2, height-2, position.plus(1,1), content);
	}
	
	public Pane(int width, int height, Coordinate position){
		this(width, height, position, new ArrayList<>());
	}
	
	public Pane(int width, int height, Coordinate position, List<String> content, CSIColor color){
		this(width, height, position, ColorString.colorizeList(content, color));
	}
	

	public Pane(int width, int height, Coordinate position, List<ColorString> content){
		this.height = height;
		this.width= width;
		this.content = content;
		this.position = position;
		this.screen = Player.getPlayer().getScreen();
	}
	
	public Pane(Pane other){
		this(other.height, other.width, other.position, null);
		this.content = new ArrayList<>();
		this.content.addAll(other.content);
		
	}
	
	public Pane display(){
		for(int i=0; i<height && i<content.size(); i++){
			screen.printer.printAt(position.plus(0, i), content.get(i).spaceAndtrimTo(width));
		}
		return this;
	}

	public Coordinate getPosition() {
		return position;
	}

	public Pane setPosition(Coordinate position) {
		this.position = position;
		return this;
	}

	public int getHeight() {
		return height;
	}

	public Pane setHeight(int height) {
		
		this.height = height;
		return this;
	}

	public int getWidth() {
		return width;
	}

	public Pane setWidth(int width) {
		this.width = width;
		return this;
	}

	public List<ColorString> getContent() {
		return content;
	}

	public Pane setContent(List<ColorString> content) {
		this.content = content;
		return this;
	}
	
	public Pane setContent(List<String> content, CSIColor color){
		this.content = ColorString.colorizeList(content, color);
		return this;
	}

	public Printer getPrinter() {
		return screen.printer;
	}

	public Screen getScreen() {
		return screen;
	}

	public Pane addContent(String line, CSIColor color) {
		this.content.add(new ColorString(line, color));
		return this;
	}

	public Pane empty() {
		this.content = new ArrayList<>();
		return this;
	}

	public Pane addContent(ColorString line) {
		this.content.add(line);
		return this;
	}

	public Pane drawBorder(){
		List<String> sides = new ArrayList<>();
		for(int i=0; i<height; i++){
			sides.add("|");
		}
		new Pane(width+2, 1, position.minus(1, 1)).addContent("+"+StringUtils.repeated("-", width) + "+", CSIColor.WHITE).display();
		new Pane(width+2, 1, position.plus(-1, height)).addContent("+"+StringUtils.repeated("-", width) + "+", CSIColor.WHITE).display();
		new Pane(1, height, position.minus(1, 0)).setContent(sides, CSIColor.WHITE).display();
		new Pane(1, height, position.plus(width, 0)).setContent(sides, CSIColor.WHITE).display();
		return this;
	}
}
