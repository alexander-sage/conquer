package elements.places;

import java.util.Set;

public interface GraphNode {
	int jumpsTo(GraphNode other);
	int jumpsAlong(GraphNode pathStep, GraphNode destination);
	int jumpsToIgnoring(GraphNode other, Set<? extends GraphNode> ignoreSet);
	Set<? extends GraphNode> getNeighbors();
	GraphNode pathTo(GraphNode other);
	
	
}

