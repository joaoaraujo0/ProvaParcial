package Exercicio;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.ArrayList;

class Edge {
  private float weight = 1;
  private Vertex from;
  private Vertex to;

  public Edge(Vertex from, Vertex to) {
    setFrom(from);
    setTo(to);
  }

  public Edge(Vertex from, Vertex to, float weight) {
    setFrom(from);
    setTo(to);
    setWeight(weight);
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public float getWeight() {
    return weight;
  }

  private void setFrom(Vertex from) {
    this.from = from;
  }

  public Vertex getFrom() {
    return from;
  }

  private void setTo(Vertex to) {
    this.to = to;
  }

  public Vertex getTo() {
    return to;
  }

  @Override
  public String toString() {
    return "[" + 
        this.weight + ", " + 
        this.from.getLabel() + ", " + 
        this.to.getLabel() + "]";
  }
}

class Vertex {
  private String label;
  private List<Edge> edges = new LinkedList<>();

  public Vertex(String label) {
    setLabel(label);
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void addEdge(Edge edge) {
    this.edges.add(edge);
  }

  public void removeEdge(Edge edge) {
    edges.remove(edge);
  }

  public List<Edge> getEdges() {
      return edges;
  } 

  @Override
  public String toString() {
    String ts = "[Label: " + label;
    for (Edge edge : this.edges) 
      ts += edge;
    return ts + "]";
  }
}

public class Graph {
  
  private List<Vertex> vertexes = new LinkedList<>();

  public Graph() {  }

  protected Vertex findVertex(String value) {
    try {
      return vertexes
          .stream()
          .filter(v -> v.getLabel().equals(value))
          .collect(Collectors.toList())
          .get(0);
    } catch(Exception e){
      return null;
    }
  }

  public void addVertex(String value) {
    var vertex = findVertex(value);
    if (vertex == null)
      vertexes.add(new Vertex(value));
  }

  public void removeVertex(String value) {
    var vertex = findVertex(value);
    if (vertex != null)
      vertexes.remove(vertex);
  }
  
  public Graph uniao(Graph graph2) {
	  Graph graph1 = this;
	  Graph union = new Graph();
	
	  for (Vertex v : graph1.vertexes) {
	    union.addVertex(v.getLabel());
	    
	    for (Edge e : v.getEdges()) {
	      Vertex to = e.getTo();
	      union.addVertex(to.getLabel());
	      union.addEdge(v.getLabel(), to.getLabel());
	    }
	  }
	  
	  for (Vertex v : graph2.vertexes) {
	    if (union.findVertex(v.getLabel()) == null) {
	      union.addVertex(v.getLabel());
	      for (Edge e : v.getEdges()) {
	        Vertex to = e.getTo();
	        if (union.findVertex(to.getLabel()) == null) {
	          union.addVertex(to.getLabel());
	          union.addEdge(v.getLabel(), to.getLabel());
	        }
	      }
	    }
	  }
	  
	  return union;
	}
  
  
  public Graph interseccao(Graph graph2) {
	  Graph graph1 = this;
	  Graph intersection = new Graph();
	  
	  for (Vertex v : graph1.vertexes) {
	    if (graph2.findVertex(v.getLabel()) != null) {
	      intersection.addVertex(v.getLabel());

	      for (Edge e : v.getEdges()) {
	        Vertex to = e.getTo();
	        if (graph2.findVertex(to.getLabel()) != null) {
	          intersection.addVertex(to.getLabel());
	          intersection.addEdge(v.getLabel(), to.getLabel());
	        }
	      }
	    }
	  }
	  
	  return intersection;
	}
  
  public List<Vertex> caminho(String a, String b) {
	  var start = findVertex(a);
	  var end = findVertex(b);

	  if (start == null || end == null)
	    return null;

	  var visited = new HashSet<Vertex>();
	  var queue = new LinkedList<List<Vertex>>();

	  visited.add(start);
	  queue.add(Arrays.asList(start));

	  while (!queue.isEmpty()) {
	    var path = queue.remove();
	    var last = path.get(path.size() - 1);

	    if (last == end)
	      return path;

	    for (var neighbor : neighbours(last.getLabel())) {
	      if (!visited.contains(neighbor)) {
	        visited.add(neighbor);
	        var newPath = new ArrayList<>(path);
	        newPath.add(neighbor);
	        queue.add(newPath);
	      }
	    }
	  }

	  return null;
	}
  

  public void addEdge(String valA, String valB) {
    var va = findVertex(valA);
    var vb = findVertex(valB);
    va.addEdge(new Edge(va, vb));
    vb.addEdge(new Edge(vb, va));
  }

  public List<Vertex> neighbours(String value) {
      var v = findVertex(value);
      if (v == null)
          return null;
      List<Vertex> vs = new LinkedList<>();
      for(Edge e : v.getEdges())
          vs.add(e.getTo());
      
      return vs;
  }

  public boolean direct(String vA, String vB) {
      var v = findVertex(vA);
      if (v != null) {
        return v.getEdges()
            .stream()
            .filter(e -> e.getTo().getLabel().equals(vB))
            .count() == 1;
      }
      return false;
  }

  @Override
  public String toString() {
    var s = "";
    for(Vertex v : vertexes)
      s += v;
    return s;
  }
}