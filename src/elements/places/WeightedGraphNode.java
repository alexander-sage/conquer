package elements.places;

import java.util.Map;

public interface WeightedGraphNode extends GraphNode{
	double distanceTo(GraphNode other);
	double distanceAlong(GraphNode pathStep, GraphNode destination);
	Map<? extends WeightedGraphNode, Double> getDistanceByNeighbor();
}
