package elements.places;

import java.util.ArrayList;
import java.util.List;

public abstract class GameMap {

	final List<Site> siteList;
	
	public GameMap(){
		siteList = new ArrayList<>();
	}
	
	public GameMap(List<Site> siteList){
		this.siteList = siteList;
	}
	
	public abstract List<Site> findNearest(Site site);

	public abstract Site findNearestOne(Site site);

	public abstract GameMap addSite(Site site) ;
	
	
	
}
