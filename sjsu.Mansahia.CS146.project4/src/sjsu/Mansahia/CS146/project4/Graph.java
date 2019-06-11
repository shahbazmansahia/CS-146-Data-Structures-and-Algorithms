package sjsu.Mansahia.CS146.project4;

import java.awt.Color;

/**
 * 
 * @author Jose Sandoval and Shahbaz Singh Mansahia
 * Shahbaz Singh Mansahia - Depth First and Breadth first Search methods 
 * Jose Sandoval - Everything else
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//import sjsu.Mansahia.CS146.project4.Graph.Vertex;

public class Graph {
   //Array of Vertices
   public Vertex [] adjacencyLists;
   public int noEdges;
   public int noVertices;
   
   public Graph() {
      adjacencyLists = new Vertex[0];
      noEdges = 0;
      noVertices = 0;
   }
   
   // Graph Constructor
   Graph(String fileName) throws FileNotFoundException
   {
      Scanner scanner = new Scanner(new File(fileName));
      
      //Initialize vertex array
      this.noVertices = scanner.nextInt();
      this.adjacencyLists = new Vertex[noVertices];
      
      //Initialize graph 
      for(int v = 0; v < noVertices; v++)
      {
         this.adjacencyLists[v] = new Vertex(v);
      }
      
      this.noEdges = scanner.nextInt();
      
//    System.out.println("Vertices in Graph: " + this.adjacencyLists.length + "\n" + "Edges in Graph: " + noEdges);
      
      //Create the graph connections
      while(scanner.hasNext())
      {
         int vertex1 = scanner.nextInt(); //Value of vertex1
         int vertex2 = scanner.nextInt(); //Value of vertex2
         float vertexWeight = scanner.nextFloat(); // Edge weight between the two vertices
         
         //Since graph is undirected connect both vertices to each other
         adjacencyLists[vertex1].adjacent = new Neighbor(vertex2, this.adjacencyLists[vertex1].adjacent,vertexWeight);
         adjacencyLists[vertex2].adjacent = new Neighbor(vertex1, this.adjacencyLists[vertex2].adjacent,vertexWeight);
      }
      scanner.close();
   }
   
   // Print vertex along with its neighbors and edge weight between them
   public void print()
   {
      for(int v=0; v < this.adjacencyLists.length; v++)
      {
         System.out.println("Vertex " + this.adjacencyLists[v].vertex);
         for(Neighbor neighbor=this.adjacencyLists[v].adjacent; neighbor!=null; neighbor = neighbor.neighborVertex )
         {
            System.out.println("connected to vertex "+this.adjacencyLists[neighbor.vertexNumber].vertex +", Edge weight: "+ neighbor.edgeWeight);
         }
         System.out.println("\n");
      }
   }
   
   // Vertex inner class
   class Vertex
   {
      // The vertex
      int vertex;
      
      // Neighbor of the vertex
      Neighbor adjacent;
      
      Color col; // Color for showing discovery status
      Vertex parent;      // Parent node for keeping track in depth First Search
      int timeStampInitial;       //Keeps track of the initial timeStamp tracking in DFS; represents u.d from the book algorithm
      int timeStampFinal;  // Keeps track of the final timeStamp tracking in DFS; represents u.f from the book algorithm
      public Vertex(int vertex)
      {
         this.vertex= vertex;
         this.adjacent = null;
         this.col = Color.BLACK;
         this.parent = null;
         timeStampInitial = 0;
      }
   }// END INNER CLASS
   
   // Neighbor inner class
   class Neighbor
   {
      int vertexNumber;
      Neighbor neighborVertex;
      float edgeWeight;
      
      // Neighbor constructor
      Neighbor(int vertex, Neighbor neighborVertex, float edgeWeight)
      {
         this.vertexNumber = vertex;
         this.neighborVertex = neighborVertex;
         this.edgeWeight = edgeWeight;
      }
   }// END INNER CLASS
   
   /**
    * Code by Shahbaz begins here
    */

   int time; // global variable necessary for DFS
   public void DepthFirst () {
      //Graph begin = this;
      for (Vertex u : this.adjacencyLists) { // traverse through each vertex V in graph G // HOW TO ITERATE THROUGH GRAPH VERTICES ?!
         u.col = Color.WHITE;
         u.parent = null;
      }
      time = 0;
      for (Vertex u : this.adjacencyLists) {
         if (u.col == Color.WHITE) {
            DepthFirstVisit(this, u);
         }
      }
   }
   
   private void DepthFirstVisit(Graph begin, Vertex u) {
      time++;
      u.timeStampInitial = time;
      u.col = Color.gray;
      //Vertex v;
      for (Vertex v : begin.adjacencyLists) { //EXPLORE EDGES NAVIGATION?!
         if (v.col == Color.white) {
            v.parent = (Vertex) u;
            DepthFirstVisit(begin, v);
         }
      }
      u.col = Color.BLACK;
      time++;
      u.timeStampFinal = time;
   }
   
   public void BreadthFirst (Vertex s) { // 's' represents the node/vertex s as stated on page 595 of the book
      for (int i = 0; i < this.noVertices; ++i) {
         this.adjacencyLists[i].col = Color.WHITE;
         this.adjacencyLists[i].timeStampInitial = -1; // '-1' represents /inf as there a negative timeStamp is not possible
         this.adjacencyLists[i].parent = null;
      }
      s.col = Color.GRAY;
      s.timeStampInitial = -1;
      Queue <Vertex> q = new LinkedList <Vertex>();
      Iterator <Vertex> it = q.iterator();
      q.add(s);
      while (it.hasNext()) {
         Vertex u = it.next();
         for (Vertex v : this.adjacencyLists) {
            if (v.col == Color.WHITE) {
               v.col = Color.GRAY;
               v.timeStampInitial++;
               v.parent = u;
               q.add(v);
            }
         u.col = Color.BLACK;
         }
         
      }
   }
   /**
    * Code by Shahbaz ends here
    */
   public static void main(String[] args) throws FileNotFoundException
   {
      String fileName = "tinyEWG.txt";
      Graph g = new Graph(fileName);
      g.print();
   }
   
}
