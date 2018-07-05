

package experiments.autofix.classes;

import java.util.List;

import java.util.Arrays;

import java.util.Scanner;

import java.util.Queue;

import java.util.LinkedList;

import java.util.ArrayList;

public class AutofixClass_1 {
	public static List<Edge> autofixMethod_1(int numOfEdges, Scanner sc) {
		int i, initialVertex, terminalVertex;
		List<Edge> edges = new ArrayList<>();
		System.out.println("Enter edges :");
		for (i = 0; i < numOfEdges; i++) {
			initialVertex = sc.nextInt();
			terminalVertex = sc.nextInt();
			edges.add(new Edge(initialVertex, terminalVertex));
		}
		return edges;
	}
}