package com.nhlstenden.amazonsimulatie.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    private String name;
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    private Map<Node,Integer> adjacentNodes = new HashMap<Node, Integer>();


    public Node(String name){
        this.name = name;
    }
    public void addDestination(Node destination, int distance){
        adjacentNodes.put(destination,distance);
    }
    public String getNode(){
        return this.name;
    }
    public void setNode(String name){
        this.name = name;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    public Integer getDistance(){
        return this.distance;
    }

    public Map<Node, Integer> getAdjecencyNodes() {
        return this.adjacentNodes;
    }
    public List<Node> getShortestPath(){
        return this.shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
}
