package sjsu.Mansahia.CS146.project4;
/**
 * 
 * @author Jose Sandoval
 *
 */
import java.util.Arrays;

public class MSTPrim 
{
   Graph graph; // the Graph
   boolean inList[]; // Will handle vertices in the comparison list
   float[] minEdgeValue; // Edge cost
   int[] parent;

   public void minimumSpanningTreeOfGraph() 
   {
      this.initializeArrays();
      // The starting vertex to traverse through
      Graph.Vertex startVertex = graph.adjacencyLists[0];

      minEdgeValue[0] = 0;// Step 1 - setting value=0 for the starting vertex
      inList[0] = false;// removing the first starting node from the fringe

      // While the list is not empty keep exploring vertices to find their neighbors with minimum edge weight
      while (!isListEmpty()) 
      {
         Graph.Neighbor neighbor = startVertex.adjacent;
         
         // Check for neighbors of the current vertex
         while (neighbor != null) 
         {
            //if neighbor is still in the list vertex to neighbor edge weight < current value in the list
            // change the weight of edge between the two vertices
            if ((inList[neighbor.vertexNumber] == true) && minEdgeValue[neighbor.vertexNumber] >= neighbor.edgeWeight) 
            {
               minEdgeValue[neighbor.vertexNumber] = neighbor.edgeWeight;
               parent[neighbor.vertexNumber] = startVertex.vertex;
            }
            // Move onto the neighbor 
            neighbor = neighbor.neighborVertex;
         }
         
         // extract the minimum value in the list
         int minValueIndex = extractMin();
         
         //Print the Edges that contributed to the minimum value of the edge
         printMinEdge(minValueIndex);
         
         // Remove Vertex from the list
         inList[minValueIndex] = false;
         
         // choose starting vertex to be the vertex with the minimum value and explore its neighbors
         startVertex = graph.adjacencyLists[minValueIndex];
      }
   }

   //Initialize all the values of the arrays used
   public void initializeArrays()
   {
      int noOfVertex = graph.noVertices;
      this.minEdgeValue = new float[noOfVertex];
      this.parent = new int[noOfVertex];
      this.inList = new boolean[noOfVertex];
      // All vertices are in the list to start
      Arrays.fill(inList, true);
      // initialize all vertices with value of infinity
      Arrays.fill(minEdgeValue, Integer.MAX_VALUE);
      // set  parent of vertex to negative value
      Arrays.fill(parent, Integer.MIN_VALUE);
   }
   
   // Check if the list is empty 
   public boolean isListEmpty() 
   {
      //If there is a true, indicating there is still a vertex in the list return not empty
      for(boolean b: inList)
      {
         if(b) return false;
      }
      return true;
   }
   // Extracts the minimum value in the list 
   public int extractMin() 
   {
      float min = Integer.MAX_VALUE;
      int minVertexIndex = -99;
      for (int i = 0; i < minEdgeValue.length; i++) 
      {
         if ((minEdgeValue[i] < min) && (inList[i] == true)) 
         {
            min = minEdgeValue[i];
            minVertexIndex = i;
         }
      }
      return minVertexIndex;
   }

   // Print Tree edges along with the weight of the edges
   public void printMinEdge(int minValue) 
   {
      int startOfEdge = graph.adjacencyLists[(parent[minValue])].vertex;
      int endOfEdge = graph.adjacencyLists[minValue].vertex;
      System.out.println(startOfEdge + "  --> " + endOfEdge + " Edge Weight: " + minEdgeValue[minValue]);
   }

   public static void main(String[] args) throws Exception
   {
      String file = "largeEWG.txt";
      MSTPrim tree = new MSTPrim();
      tree.graph = new Graph(file);
      tree.minimumSpanningTreeOfGraph();
   }
}
