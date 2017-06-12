package utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.javatuples.Pair;

import elements.places.GraphNode;
import elements.places.WeightedGraphNode;

public class WeightedDjikstraHandler extends Handler<WeightedDjikstra, GraphInfo> {
	private static WeightedDjikstraHandler dh = new WeightedDjikstraHandler(5);
	
	private WeightedDjikstraHandler(int i) {
		super(i);
	}
	
	public static WeightedDjikstraHandler getInstance(){
		return dh;
	}
	
	

	
	
	LinkedList<Pair<GraphInfo, WeightedDjikstra>> graphs;
	
	public WeightedDjikstra getDjikstra(GraphNode origin, Set<? extends GraphNode> ignoreSet){
		//TODO fix hack
		
		WeightedGraphNode wgn = (WeightedGraphNode) origin;
		Set<WeightedGraphNode> s = new HashSet<>();
		for(GraphNode g : ignoreSet){
			s.add((WeightedGraphNode) g);
		}
		
		return getDjikstra(wgn, s);
		
	}
	
	
	
	public WeightedDjikstra getDjikstra(WeightedGraphNode origin, Set<? extends WeightedGraphNode> ignoreSet){
		GraphInfo info = new GraphInfo(origin, ignoreSet);
		
		return getElement (info);
		
	}

	public WeightedDjikstra getDjikstra(WeightedGraphNode origin) {
		return getDjikstra(origin, new HashSet<>());
	}






	@Override
	WeightedDjikstra createElement(GraphInfo identifier) {
		return WeightedDjikstra.of(identifier.origin, identifier.ignoreSet);
	}


}

class GraphInfo{
	final WeightedGraphNode origin;
	final Set<? extends WeightedGraphNode> ignoreSet;
	
	GraphInfo(WeightedGraphNode origin, Set<? extends WeightedGraphNode> ignoreSet){
		this.origin = origin;
		this.ignoreSet = ignoreSet;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof GraphInfo){
			GraphInfo o = (GraphInfo) obj;
			return this.origin.equals(o.origin) && this.ignoreSet.equals(o.ignoreSet);
		}
		else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return origin.hashCode()*31+ignoreSet.hashCode();
	}
}

