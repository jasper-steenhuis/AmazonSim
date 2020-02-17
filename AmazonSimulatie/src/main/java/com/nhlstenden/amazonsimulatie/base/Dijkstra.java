package com.nhlstenden.amazonsimulatie.base;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
    public static Graph calculateShortestPathFromSource(Graph graph, Node source){
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while(unsettledNodes.size() != 0){
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node,Integer> entry: currentNode.getAdjecencyNodes().entrySet()) {
                Node adjacentNode = entry.getKey();
                Integer edgeWeight = entry.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimalDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void calculateMinimalDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
        Integer sourceDistance =   sourceNode.getDistance();
        if(sourceDistance + edgeWeight < evaluationNode.getDistance()){
            evaluationNode.setDistance(sourceDistance + edgeWeight);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowesDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes){
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowesDistance){
                lowesDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

}
