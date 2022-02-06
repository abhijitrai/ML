package graph;

import java.util.ArrayList;
import java.util.List;

public class DFS3 {

    /*
     *                       9    3    1         10
     *                        \  /  \ / \      /
     *                         8 -  0 -  2    6
     *                          \  /    / \  /
     *                           4     5   7
     *
     *
     * */

    /*
     *                       9    3    1         10
     *                        \  /  \  \      /
     *                         8    0 -  2    6
     *                          \  /    / \  /
     *                           4     5   7
     *
     *
     * */





    public static void main(String... args){
        int[][] edges = {{0, 1},{ 8 ,0}, {2, 5}, {2, 7}, {7, 6}, {6, 10}, {0, 3}, {0, 4},  {3, 8}, {4, 8}, {8, 9}, { 0,2},  {1, 2} };
        Graph g = new Graph(11,edges, false);
        System.out.println(g);
        DFS3 d = new DFS3();
        d.dfs(g, 3);
        int[][] edgeArr = new int[d.dfsEdges.size()][];
        for(int i=0 ;i< d.dfsEdges.size();i++)
            edgeArr[i]= d.dfsEdges.get(i);
        System.out.println(new Graph(g.edgeArr.length, edgeArr,true));

    }

    int[] discovered;
    int root = -1 ;
    List<int[]> dfsEdges ;

    void dfs ( Graph graph , int v ){
        if(root == -1 ){
            discovered =  new int[graph.edgeArr.length];
            dfsEdges = new ArrayList<>();
            for( int i= 0 ; i<  graph.edgeArr.length ; i++ ){
                discovered[i] = -1 ;
            }
            root = v ;
        }
        EdgeNode e = graph.edgeArr[v];
        System.out.println("Processing Node : " + v);
        while(e != null ){
            System.out.println("Visited Node : " + e.y);
            if( discovered[e.y] == -1 && e.y != root){
                System.out.println("New Node discovered " + e.y + " Edge Added : " + v +"  - " + e.y);
                dfsEdges.add(new int[]{v,e.y});
                discovered[e.y] = v;
                dfs( graph ,  e.y);
            }else{
             // Nothing to do this is not the new edge
            }
            e = e.next ;
        }
    }

    private static class Graph {
        EdgeNode[] edgeArr ;


        Graph ( int v, int[][]edges ,boolean directed){
            edgeArr = new EdgeNode[v];
            for( int i = 0 ; i<  edges.length ; i++){
                insertNode( edges[i][0],edges[i][1],directed );
            }
        }

        private void insertNode(int x, int y, boolean directed) {
            EdgeNode e = edgeArr[x];
            if (e == null) {
                edgeArr[x] = new EdgeNode(y);
            }else{
                EdgeNode nEdge = new EdgeNode(y) ;
                nEdge.next = edgeArr[x] ;
                edgeArr[x] = nEdge ;
            }
            if( !directed){
                insertNode(y,x,true);
            }
        }

        public String toString(){
            StringBuilder sb =  new StringBuilder();
            for ( int i= 0 ;i < edgeArr.length ; i++ ){
                sb.append( "Node " + i );
                EdgeNode curNode =  edgeArr[i];
                while ( curNode != null ){
                    sb.append( "-> " + curNode.y);
                    curNode = curNode.next;
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    private static class EdgeNode {
        EdgeNode( int y){
            this.y = y ;
        }
        int y ;
        EdgeNode next ;
    }
}
