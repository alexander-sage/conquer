package utils;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import elements.places.GraphNode;
import elements.places.WeightedGraphNode;


public class WeightedDjikstra {
	final private Map<GraphNode, Integer> stepsByNode = new HashMap<>();
	final private Map<WeightedGraphNode, Double> distanceByNode = new HashMap<>();
	
	public static WeightedDjikstra of(WeightedGraphNode origin){
		return new WeightedDjikstra(origin);
	}
	
	public static WeightedDjikstra of(WeightedGraphNode origin, Set<? extends WeightedGraphNode> ignore){
		return new WeightedDjikstra(origin, ignore);
	}
	
	public WeightedDjikstra(WeightedGraphNode origin){
		this(origin, new HashSet<>());
	}
	
	public WeightedDjikstra(WeightedGraphNode origin, Set<? extends WeightedGraphNode> ignore){
		if(ignore.contains(origin)){
			return;
		}
		
		Queue<WeightedGraphNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(origin);
		stepsByNode.put(origin, 0);
		distanceByNode.put(origin,  0.0);
		
		while(!nodeQueue.isEmpty()){
			WeightedGraphNode node = nodeQueue.remove();
			Map<? extends WeightedGraphNode, Double> distances = node.getDistanceByNeighbor();
			for(WeightedGraphNode n : distances.keySet()){
				if(!ignore.contains(n)){
					int oldSteps = stepsByNode.getOrDefault(n, Integer.MAX_VALUE);
					double oldDistance = distanceByNode.getOrDefault(n, Double.MAX_VALUE);
					int newSteps = stepsByNode.merge(n, stepsByNode.get(node)+1, Integer::min);
					double newDistance = distanceByNode.merge(n, distanceByNode.get(node)+distances.get(n), Double::min);
					if(oldSteps<newSteps || oldDistance<newDistance){
						nodeQueue.add(n);
					}
				}
			}
		}
	}
	
	public LinkedList<GraphNode> walkGraph(GraphNode start){
		LinkedList<GraphNode> output = new LinkedList<>();
		
		GraphNode step = start;
		int oldScore = Integer.MAX_VALUE;
		int score = stepsByNode.get(step);
		
		while(oldScore>score){
			output.addLast(step);
			step = step.getNeighbors().stream().reduce((a,b)->{return stepsByNode.get(a)<stepsByNode.get(b)?a:b;}).orElse(null);
			oldScore = score;
			score = stepsByNode.get(step);
		}
		return (output);
	}
	
}
