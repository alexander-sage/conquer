package driver;

import java.text.DecimalFormat;

import console.Screen;
import debug.Debug;
import elements.Being;
import elements.places.GameMap;
import elements.places.Location;
import elements.places.RegionalLocation;
import elements.places.RegionalMap;
import elements.places.Site;
import interfaces.outputs.MessageShower;
import interfaces.outputs.PlayerInfo;

public class Player {
	//single source of truth
	private final Screen screen;
	private GameMap gameMap;
	private Location location;
	private Site site;
	private static Player player;
	final private MessageShower feed;
	final private PlayerInfo playerInfo;
	
	final private Being pc;
	
	
	
	public static void main(String[] args) {
		newPlayer();
		Debug.addRandomSites((RegionalMap) player.gameMap);
		while(true){
			player.site.run();
			player.pc.reduceHealth(7);
			player.playerInfo.printStats();
		}
	}
	
	private Player(){
		player = this;
		this.screen = new Screen();	
		this.feed = MessageShower.standard().drawBorder();
		
		this.pc = Being.ofWolfman("Najoi");
		this.playerInfo = new PlayerInfo().drawBorder();
	}
	
	private void setupPlayer(){
		this.gameMap = new RegionalMap();
		
		this.site = new Site(new RegionalLocation(100, -100, 0.8), "Origin point");
		this.gameMap.addSite(site);
		
		
		
		
		playerInfo.printStats();
	}
	
	public synchronized static Player newPlayer(){
		if(player == null){
			player = new Player();
			player.setupPlayer();
		}
		return player;
	}
	
	public synchronized static Player getPlayer(){
		return player;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public Site getSite(){
		return site;
	}
	
	public Screen getScreen(){
		return screen;
	}
	
	public GameMap getMap(){
		return gameMap;
	}
	
	public void travelTo(Site destination){
		double distance = this.site.distanceTo(destination);
		DecimalFormat formatter = new DecimalFormat("#0.00");
		feed.write("You have moved " + formatter.format(distance) + " km.");
		this.site = destination;
		feed.write("You are at "+ site.toString());
		this.location = destination.getPosition();
	}

	public Being getPC() {
		return pc;
	}

	
	

}
