package interfaces.menus;

import console.Screen;
import interfaces.PaneFactory;

public class MenuFactory {
	
	
	
	
	public MenuFactory() {
		
	}

	public static TooltipMenu getStandardMenu(){
		return new TooltipMenu(PaneFactory.getStandardMenuPane(), PaneFactory.getStandardToolPane());
	}
}
