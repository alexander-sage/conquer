package interfaces.outputs;

import console.ColorString;
import driver.Player;
import elements.Being;
import interfaces.BorderedWindow;
import interfaces.Pane;
import interfaces.PaneFactory;
import net.slashie.libjcsi.CSIColor;

public class PlayerInfo implements BorderedWindow{
	private final Being player;
	private final Pane pane;
	
	public PlayerInfo(){
		this.player = Player.getPlayer().getPC();
		this.pane = PaneFactory.getMyStatsPanel();
	}
	
	@Override
	public PlayerInfo drawBorder() {
		pane.drawBorder();
		return this;
	}
	
	public PlayerInfo printStats(){
		pane.empty();
		pane.addContent(player.getName(), CSIColor.WHITE);
		
		pane.addContent(getHealthString());
		pane.display();
		return this;
	}
	
	private ColorString getHealthString(){
		CSIColor healthColor;
		double proportion = player.getHealth()*1.0/player.getMaxHealth();
		
		if(proportion>0.5){
			healthColor = CSIColor.GREEN;
		} else if(proportion > 0){
			healthColor = CSIColor.YELLOW;
		} else{
			healthColor = CSIColor.RED;
		}
		
		return ColorString.of(player.getHealth() + " / " + player.getMaxHealth(), healthColor);
	}
}
