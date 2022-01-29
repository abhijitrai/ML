package graph;

import java.util.Arrays;

public class GraphAdjacencyMatrixImpl {

    private final int[][] matrix;

    GraphAdjacencyMatrixImpl(int n ){
        matrix = new int[n][n];
    }

    void addEdge(int x, int y ) {
        matrix[x][y] = 1;
    }

    void removeEdge(int x, int y){
        matrix[x][y] = 0 ;
    }

    void removeNode(int x){
        int[] rowX = matrix[x];
        for (int i=0 ;i< rowX.length; i++ ){
            rowX[i] = 0 ;
        }
        for(int i = 0 ; i< matrix[x].length; i++){
            matrix[i][x]=0;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sb.append( " " + matrix[i][j]);
            }
            sb.append("\n");

        }
        return sb.toString();
    }


}

class  Demo {

    public static void main(String[] args) {
        GraphAdjacencyMatrixImpl graph = new GraphAdjacencyMatrixImpl(10);
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
        graph.removeNode(3);
        System.out.println("************");
        System.out.println(graph);
    }


}
