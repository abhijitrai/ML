package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GraphAdjacencyListImpl {
    GraphNode[] vertices;
    int[] outDegree ;

    GraphAdjacencyListImpl(int size) {
        vertices = new GraphNode[size];
        outDegree = new int[size];
        for(int i = 0; i< size ; i++ ){
            vertices[i] = new GraphNode(i);
            outDegree[i] = 0 ;
        }
    }

    private static class GraphNode {
        GraphNode(int x){
            this.x = x ;
        }
        int x;
        List<Edge> edgeList = new LinkedList<>();
    }

    private static class Edge {
        Edge(int i){
            y = i ;
        }
        int y;
        int w = 0 ;

        public boolean equals(Object o){
            if(o == null )
                return false;
            if( ! (o instanceof  Edge) )
                return false ;
            if(this.y == ((Edge) o).y)
                return true ;
            else
                return false;
        }
    }


    void addEdge(int x, int y ){
        vertices[x].edgeList.add(new Edge(y));
    }


    void removeEdge(int x, int y){
        Iterator<Edge> it = vertices[x].edgeList.iterator() ;

        while(it.hasNext()){
            Edge edge = it.next();
            if(edge.y == y )
            {
                vertices[x].edgeList.remove(new Edge(y) );
            }
        }


    }

    public static void main(String[] args) {
        GraphAdjacencyListImpl graph = new GraphAdjacencyListImpl(10);

    }



    @Override
    public String toString() {
        StringBuilder sb  =new StringBuilder();
        for (int i = 0; i < vertices.length ; i++) {
            GraphNode node  = vertices[i];
            List<Edge> edge = node.edgeList ;
            Iterator<Edge> it = edge.iterator();
            sb.append(" Node " + i ) ;
            while(it.hasNext()){
                sb.append(" -> " +it.next().y);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

class  DemoAdjList {

    public static void main(String[] args) {
        GraphAdjacencyListImpl graph = new GraphAdjacencyListImpl(10);
        graph.addEdge(2,3);
        graph.addEdge(3,3);
        graph.addEdge(4,7);
        graph.addEdge(6,5);
        graph.addEdge(5,3);
        graph.addEdge(3,4);
        graph.addEdge(8,7);
        graph.addEdge(9,5);
        graph.addEdge(5,3);
        graph.addEdge(2,1);
        System.out.println(graph);
        graph.removeEdge(3,3);
        System.out.println("************");
        System.out.println(graph);
    }


}




