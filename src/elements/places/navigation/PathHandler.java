package elements.places.navigation;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import elements.places.GraphNode;
import utils.Handler;
import utils.WeightedDjikstraHandler;

public class PathHandler extends Handler<LinkedList<GraphNode>, Path> {
	
	private static PathHandler pathHandler = new PathHandler(10);
	
	public static PathHandler getInstance(){
		return pathHandler;
	}
	
	private PathHandler(int i){
		super(i);
	}
	
	public List<GraphNode> getPathTo(GraphNode origin, GraphNode destination){
		return getPathTo(origin, destination, new HashSet<>());
	}
	
	public List<GraphNode> getPathTo(GraphNode origin, GraphNode destination, Set<? extends GraphNode> ignoreSet){
		
		
		//if(path.steps.contains(origin) && path.steps.getLast().equals(destination)){
		//	return path.steps.subList(path.steps.indexOf(origin), path.steps.size());
			
		
		
		

		return null;
		
	}
	
	@Override
	LinkedList<GraphNode> createElement(Path identifier) {
		WeightedDjikstraHandler.getInstance().
		return null;
	}
}

class Path{
	public final Triplet<GraphNode, GraphNode, Set<GraphNode>> ends;
	
	public Path(GraphNode origin, GraphNode destination){
		this(origin, destination, new HashSet<>());
	}
	
	public Path(GraphNode origin, GraphNode destination, Set<GraphNode> ignore){
		ends = new Triplet<>(origin, destination, ignore);
	}
}
