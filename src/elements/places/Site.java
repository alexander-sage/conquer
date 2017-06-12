package elements.places;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import driver.Playable;
import driver.Player;
import interfaces.menus.MenuFactory;
import interfaces.menus.TooltipMenu;
import utils.Formats;

public class Site implements Playable, WeightedGraphNode{
	private final Location position;
	private final GameMap map;
	private String name;
	private final Map<Site, Double> neighbors;
	
	public Site(Location position, GameMap map, String name){
		this.position = position;
		this.map = map;
		this.name = name;
		this.neighbors = new HashMap<>();
	}
	
	public Site(Location position, String name){
		this(position, Player.getPlayer().getMap(), name);
	}
	
	public List<Site> getNearestSites(){
		return map.findNearest(this);
	}
	
	public Site findNearestOne(){
		return map.findNearestOne(this);
	}
	
	public double distanceTo(Site other){
		if(this.map.equals(other.map)){
			return this.position.distanceTo(other.position);
		}
		else{
			return Double.POSITIVE_INFINITY;
		}
	}
	
	

	public Location getPosition() {
		return position;
	}


	public String getName() {
		return name;
	}

	public Site setName(String name) {
		this.name = name;
		return this;
	}

	public GameMap getMap() {
		return map;
	}
	
	@Override
	public String toString() {
		return name+" "+position;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && this.getClass().equals(obj.getClass())){
			Site other = (Site) obj;
			return this.map.equals(other.map) && this.position.equals(other.position);
		}
		else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (this.getClass().hashCode()*19+this.map.hashCode())*31+this.position.hashCode();
	}
	
	
	@Override
	public void run(){
		TooltipMenu siteMenu = MenuFactory.getStandardMenu();
		List<String> menuEntries = getSiteMenuEntries();
		siteMenu.addEntries(menuEntries);
		
		int choice = siteMenu.run();
		
		if(choice>=0 && menuEntries.get(choice).equals("Navigation")){
			navigate();
		}

	}
	
	private static List<String> getSiteMenuEntries(){
		List<String> output = new ArrayList<>();
		output.add("Navigation");
		return output;
	}
	
	private void navigate(){
		TooltipMenu navMenu = MenuFactory.getStandardMenu();
		List<Site> destinations = getNearestSites();
		navMenu.addEntries(getDestinationStrings(destinations));
		navMenu.addTips(getDestinationTips(destinations));
		
		int choice = navMenu.run();
		Site destination = destinations.get(choice);
		Player.getPlayer().travelTo(destination);
	}
	
	private static Map<String, String> getDestinationTips(List<Site> destinations, List<String> destinationLabels){
		if(destinations.size() != destinationLabels.size()){
			throw new IllegalArgumentException();
		}
		Map<String, String> output = new HashMap<>();
		for(int i=0; i<destinations.size(); i++){
			output.put(destinationLabels.get(i), destinations.get(i).toString());
		}
		
		return output;
	}
	
	private Map<String, String> getDestinationTips(List<Site> destinations){
		return getDestinationTips(destinations, getDestinationStrings(destinations));
	}

	private List<String> getDestinationStrings(List<Site> destinations) {
		List<String> output = new ArrayList<>();
		for(Site s : destinations){
			output.add(s.getName()+ " " + Formats.normal(s.distanceTo(this)) + " km");
		}
		return output;
	}

	@Override
	public int jumpsTo(GraphNode other) {
		return jumpsToIgnoring(other, new HashSet<>());
	}
	
	
	public int jumpsToIgnoring(GraphNode destination, Set<? extends GraphNode> ignoreSet){
		Set<GraphNode> seen = new HashSet<>();
		seen.addAll(ignoreSet);
		Set<GraphNode> current = new HashSet<>();
		current.add(this);
		Set<GraphNode> newNodes;
		int distance = 0;
		int size;
		
		while(! current.contains(destination)){
			distance++;
			size = seen.size();
			newNodes = new HashSet<>();
			for(GraphNode gn : current){
				for(GraphNode neighbor : gn.getNeighbors()){
					if(!seen.contains(neighbor)){
						newNodes.add(neighbor);
						seen.add(neighbor);
					}
				}
			}
			
			current = newNodes;
			
			
			if(size == seen.size()){
				distance = -1;
				break;
			}
		}
		
		
		return distance;
	}

	@Override
	public Set<Site> getNeighbors() {
		return neighbors.keySet();
	}

	@Override
	public GraphNode pathTo(GraphNode other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double distanceTo(GraphNode other) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double distanceAlong(GraphNode pathStep, GraphNode destination) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int jumpsAlong(GraphNode pathStep, GraphNode destination) {
		Set<Site> ignoreSet = new HashSet<>();
		ignoreSet.add(this);
		int steps =  pathStep.jumpsToIgnoring(destination,ignoreSet);
		return steps>0? steps : -1;
	}

	@Override
	public Map<Site, Double> getDistanceByNeighbor() {
		Map<Site, Double> output = new HashMap<>();
		output.putAll(neighbors);
		return output;
	}
}
