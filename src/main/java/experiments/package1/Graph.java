

package experiments.package1;

import experiments.autofix.classes.AutofixClass_1;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.LinkedList;

import java.util.List;

import java.util.Queue;

import java.util.Scanner;

class Edge {
	private final int initialVertex;
	private final int terminalVertex;
	public Edge(int initialVertex, int terminalVertex) {
		this.initialVertex = initialVertex;
		this.terminalVertex = terminalVertex;
	}
	public int getInitialVertex() {
		return initialVertex;
	}
	public int getTerminalVertex() {
		return terminalVertex;
	}
}

public class Graph {
	private final boolean isDirectedGraph;
	private final int numOfNodes;
	private final int numOfEdges;
	private boolean[] visited;
	private List<Integer>[] adjacencyList;
	@SuppressWarnings("unchecked")
	public Graph(int numOfNodes, int numOfEdges, boolean isDirectedGraph, List<Edge> edges) {
		this.numOfNodes = numOfNodes;
		this.numOfEdges = numOfEdges;
		this.isDirectedGraph = isDirectedGraph;
		this.visited = new boolean[numOfNodes + 1];
		this.adjacencyList = new ArrayList[numOfNodes + 1];
		for (int i = 0; i <= numOfNodes; i++) {
			this.adjacencyList[i] = new ArrayList<Integer>();
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
		return isDirectedGraph;
	}
	public int getNumOfNodes() {
		return numOfNodes;
	}
	public int getNumOfEdges() {
		return numOfEdges;
	}
	private void addDirectedEdge(Edge edge) {
		adjacencyList[edge.getInitialVertex()].add(edge.getTerminalVertex());
	}
	private void addUndirectedEdge(Edge edge) {
		adjacencyList[edge.getInitialVertex()].add(edge.getTerminalVertex());
		adjacencyList[edge.getTerminalVertex()].add(edge.getInitialVertex());
	}
	private void resetVisited() {
		Arrays.fill(visited, false);
	}
	public static int readNumOfNodes(Scanner sc) {
		System.out.print("Enter number of nodes : ");
		int numOfNodes = sc.nextInt();
		return numOfNodes;
	}
	public static int readNumOfEdges(Scanner sc) {
		System.out.print("Enter number of edges : ");
		int numOfEdges = sc.nextInt();
		return numOfEdges;
	}
	public static List<Edge> readEdges(int numOfEdges, Scanner sc) {
		List<Edge> edges = AutofixClass_1.autofixMethod_1(numOfEdges, sc);
		return edges;
	}
	private void exploreNode(int nodeNum) {
		visited[nodeNum] = true;
		System.out.printf("%d ", nodeNum);
		for (int neighbour : adjacencyList[nodeNum]) {
			if (visited[neighbour] == false) {
				exploreNode(neighbour);
			}
		}
	}
	public void runDFS() {
		for (int nodeNum = 1; nodeNum <= numOfNodes; nodeNum++) {
			if (visited[nodeNum] == false) {
				exploreNode(nodeNum);
			}
		}
		resetVisited();
		System.out.println();
	}
	public void runBFS(int startNode) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startNode);
		visited[startNode] = true;
		int currentNode;
		while (!queue.isEmpty()) {
			currentNode = queue.remove();
			System.out.printf("%d ", currentNode);
			for (int neighbour : adjacencyList[currentNode]) {
				if (visited[neighbour] == false) {
					visited[neighbour] = true;
					queue.add(neighbour);
				}
			}
		}
		System.out.println();
		resetVisited();
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numOfNodes = Graph.readNumOfNodes(sc);
		int numOfEdges = Graph.readNumOfEdges(sc);
		List<Edge> edges = Graph.readEdges(numOfEdges, sc);
		sc.close();
		Graph graph = new Graph(numOfNodes, numOfEdges, false, edges);
		graph.runDFS();
		graph.runBFS(1);
	}
}