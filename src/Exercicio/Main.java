package Exercicio;

public class Main {

	  public static void main(String[] args) {
	    var graph = new Graph();

	    graph.addVertex("a");
	    graph.addVertex("b");
	    graph.addVertex("c");
	    graph.addVertex("d");

	    graph.addEdge("a", "b");
	    graph.addEdge("a", "c");
	    graph.addEdge("b", "c");
	    graph.addEdge("a", "d");
	    
	    System.out.println(graph);

	    System.out.println("Vizinhos: " + graph.neighbours("a"));

	    if (graph.direct("a", "c"))
	        System.out.println("A e C estao ligados!!");
	    
	    if (!graph.direct("b", "d"))
	        System.out.println("B e D NAO estao ligados!!");
	    
	    var graph2 = new Graph();
	    
	    graph2.addVertex("a");
	    graph2.addVertex("e");
	    graph2.addVertex("f");
	    graph2.addVertex("g");

	    graph2.addEdge("a", "e");
	    graph2.addEdge("a", "f");
	    graph2.addEdge("g", "e");
	    graph2.addEdge("e", "f");
	    
	    var caminho = graph.caminho("a","b");
	    System.out.println("Caminho: " + caminho);
	    
	    
	    var result = graph.uniao(graph2);
        System.out.println("União: " + result);
        
        var result2 = graph.interseccao(graph2);
        System.out.println("Interseccao: " + result2);

	    
	    System.exit(0);
	  }

	}