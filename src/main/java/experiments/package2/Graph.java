

package experiments.package2;

import experiments.autofix.classes.AutofixClass_1;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.LinkedList;

import java.util.List;

import java.util.Queue;

import java.util.Scanner;

class Edge {
	private final int startVertex;
	private final int endVertex;
	public Edge(int initialVertex, int terminalVertex) {
		this.startVertex = initialVertex;
		this.endVertex = terminalVertex;
	}
	public int getInitialVertex() {
		return startVertex;
	}
	public int getTerminalVertex() {
		return endVertex;
	}
}

public class Graph {
	private final boolean isDG;
	private final int nodeCount;
	private final int edgeCount;
	private boolean[] isVisited;
	private List<Integer>[] adjList;
	@SuppressWarnings("unchecked")
	public Graph(int numOfNodes, int numOfEdges, boolean isDirectedGraph, List<Edge> edges) {
		this.nodeCount = numOfNodes;
		this.edgeCount = numOfEdges;
		this.isDG = isDirectedGraph;
		this.isVisited = new boolean[numOfNodes + 1];
		this.adjList = new ArrayList[numOfNodes + 1];
		for (int i = 0; i <= numOfNodes; i++) {
			this.adjList[i] = new ArrayList<Integer>();
		}
		if (isDirectedGraph) {
			for (Edge edge : edges) {
				addDirectedEdge(edge);
			}
		} else {
			for (Edge edge : edges) {
				addUndirectedEdge(edge);
			}
		}
	}
	public boolean isDirected() {
		return isDG;
	}
	public int getNumOfNodes() {
		return nodeCount;
	}
	public int getNumOfEdges() {
		return edgeCount;
	}
	private void addDirectedEdge(Edge edge) {
		adjList[edge.getInitialVertex()].add(edge.getTerminalVertex());
	}
	private void addUndirectedEdge(Edge edge) {
		adjList[edge.getInitialVertex()].add(edge.getTerminalVertex());
		adjList[edge.getTerminalVertex()].add(edge.getInitialVertex());
	}
	private void resetVisited() {
		Arrays.fill(isVisited, false);
	}
	public static int readNumOfNodes(Scanner scanner) {
		System.out.print("Enter number of nodes : ");
		int numOfNodes = scanner.nextInt();
		return numOfNodes;
	}
	public static int readNumOfEdges(Scanner scanner) {
		System.out.print("Enter number of edges : ");
		int numOfEdges = scanner.nextInt();
		return numOfEdges;
	}
	public static List<Edge> readEdges(int edgeCount, Scanner scanner) {
		List<Edge> edges = AutofixClass_1.autofixMethod_1(edgeCount, scanner);
		return edges;
	}
	private void exploreNode(int nodeNum) {
		isVisited[nodeNum] = true;
		System.out.printf("%d ", nodeNum);
		for (int adjNode : adjList[nodeNum]) {
			if (isVisited[adjNode] == false) {
				exploreNode(adjNode);
			}
		}
	}
	public void runDFS() {
		for (int nodeNum = 1; nodeNum <= nodeCount; nodeNum++) {
			if (isVisited[nodeNum] == false) {
				exploreNode(nodeNum);
			}
		}
		resetVisited();
		System.out.println();
	}
	public void runBFS(int startNode) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startNode);
		isVisited[startNode] = true;
		int currentNode;
		while (!queue.isEmpty()) {
			currentNode = queue.remove();
			System.out.printf("%d ", currentNode);
			for (int neighbour : adjList[currentNode]) {
				if (isVisited[neighbour] == false) {
					isVisited[neighbour] = true;
					queue.add(neighbour);
				}
			}
		}
		System.out.println();
		resetVisited();
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int nodeCount = Graph.readNumOfNodes(scanner);
		int edgeCount = Graph.readNumOfEdges(scanner);
		List<Edge> edges = Graph.readEdges(edgeCount, scanner);
		scanner.close();
		Graph dupGraph = new Graph(nodeCount, edgeCount, false, edges);
		dupGraph.runDFS();
		dupGraph.runBFS(1);
	}
}