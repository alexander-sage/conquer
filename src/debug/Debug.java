package debug;

import java.util.Random;

import elements.places.RegionalLocation;
import elements.places.RegionalMap;
import elements.places.Site;

public class Debug {
	public static void addRandomSites(RegionalMap g){
		for(int i=0; i<50; i++){
			Random rand = new Random();
			g.addSite(new Site(new RegionalLocation(rand.nextInt()%20000/100.0-100, rand.nextInt()%20000/100.0-100, rand.nextInt()%10000/100.0-5), "Site "+i));
		}
	}
}
