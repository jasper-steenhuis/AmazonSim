package com.nhlstenden.amazonsimulatie.base;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    @org.junit.jupiter.api.Test
    void calculateShortestPathFromSource() {
        Graph graph = new Graph();
        Node nodeA = new Node("nodeA");
        Node nodeB = new Node("nodeB");
        Node nodeC = new Node("nodeC");
        Node nodeD = new Node("nodeD");
        Node nodeE = new Node("nodeE");
        Node nodeF = new Node("nodeF");


        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        Graph result = Dijkstra.calculateShortestPathFromSource(graph,nodeA);

        System.out.println(result);


    }


}