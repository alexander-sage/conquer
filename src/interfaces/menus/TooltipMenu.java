package interfaces.menus;

import static interfaces.KeyReader.isDown;
import static interfaces.KeyReader.isUp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import console.Coordinate;
import console.Screen;
import interfaces.KeyReader;
import interfaces.Pane;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;

/**
 * A MenuBox with a extra pane that displays a tooltip related to the menu selection.
 * @author Alexander
 *
 */

public class TooltipMenu extends MenuBox {
	Pane toolPane;
	Map<String, String> tips;
	
	
	
	public TooltipMenu(Pane pane, Pane toolPane){
		super(pane);
		this.toolPane = toolPane;
		this.tips = new HashMap<>();
	}

	public TooltipMenu(Pane pane, Pane toolPane, List<String> entries, Map<String, String> tips) {
		this(pane, toolPane);
		this.addTips(tips);
		this.addEntries(entries);
	}

	
	public TooltipMenu drawBorder(){
		super.drawBorder();
		toolPane.drawBorder();
		return this;
	}
	
	/**
	 * Adds all argument elements to the map of tips.
	 * 
	 * @param tips  A map of the menu selection to its corresponding tooltip
	 * @return self
	 */
	public TooltipMenu addTips(Map<String, String> tips) {
		this.tips.putAll(tips);
		return this;
	}
	
	
	/**
	 * Adds a map Entry to the map of tips.  Replaces the entry if the key already exists.
	 * @param tip  A map entry of the menu item to the corresponding tip
	 * @return self
	 */
	public TooltipMenu addTip(Map.Entry<String, String> tip){
		this.tips.put(tip.getKey(), tip.getValue());
		return this;
	}
	
	/**
	 * Adds a map Entry to the map of tips.  Replaces the entry if the key already exists.
	 * @param menuItem  The key found in the menu
	 * @param tip  The tip tied to the menu item
	 * @return self
	 */
	
	public TooltipMenu addTip(String menuItem, String tip){
		this.tips.put(menuItem, tip);
		return this;
	}

	public int run(){
		if(current>= entries.size()){
			current = 0;
		}
		
		toolPane.drawBorder();
		display();
		printCursor();
		displayTip(entries.get(current));
		
		boolean done = false;
		
		while(!done){
			
			displayTip(entries.get(current));
			done = moveThroughMenu();
		}
		
		
		
		return current;
	}

	private void displayTip(String selection) {
		List<String> output = new ArrayList<>();
		output.add(tips.getOrDefault(selection, ""));
		toolPane.setContent(output, CSIColor.WHITE);
		toolPane.display();
	}

	
}
