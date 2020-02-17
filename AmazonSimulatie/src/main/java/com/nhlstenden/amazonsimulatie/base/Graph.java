package com.nhlstenden.amazonsimulatie.base;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Node> nodes = new HashSet<>();

    public void addNode(com.nhlstenden.amazonsimulatie.base.Node nodeA) {
        nodes.add(nodeA);
    }
}
