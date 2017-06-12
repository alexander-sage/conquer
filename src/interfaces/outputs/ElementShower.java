package interfaces.outputs;

import interfaces.BorderedWindow;
import interfaces.Pane;
import interfaces.PaneFactory;

public class ElementShower implements BorderedWindow{
	private final Pane pane;
	
	public ElementShower(){
		this.pane = PaneFactory.getOtherStatsPanel();
	}
	
	public ElementShower drawBorder(){
		pane.drawBorder();
		return this;
		
	}
}
