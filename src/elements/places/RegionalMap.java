package elements.places;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegionalMap extends GameMap{
	
	
	
	public RegionalMap(){
		super();
		
		siteList.add(new Site(new RegionalLocation(0, 50), this, "North Citadel"));
		siteList.add(new Site(new RegionalLocation(0, -50), this, "South Citadel"));
		siteList.add(new Site(new RegionalLocation(0, 0, 5), this, "Center Mountain"));
		siteList.add(new Site(new RegionalLocation(0, 0, -1), this, "Center Sanctum"));
	}
	
	public RegionalMap(List<Site> siteList){
		super();
		this.siteList.addAll(siteList);
		
	}

	@Override
	public List<Site> findNearest(Site site) {
		List<Site> output =  siteList.stream().sorted((s1, s2) -> Double.compare(s1.distanceTo(site), s2.distanceTo(site))).collect(toList());
		output.remove(site);
		return output;
	}
	
	
	public RegionalMap addSite(Site newSite){
		siteList.add(newSite);
		return this;
	}
	
	public RegionalMap addSites(Collection<Site> newSites){
		siteList.addAll(newSites);
		return this;
	}

	@Override
	public Site findNearestOne(Site site) {
		return siteList.stream().reduce((s1, s2)->s1.distanceTo(site)<s2.distanceTo(site)? s1:s2).orElse(null);
	}
	
	@Override
	public String toString() {
		return "Regional Map, " + siteList.size() + " sites";
	}
	
	
}
