package ex1;

import ex0.node_data;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {
    private WGraph_DS graph;
    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    @Override
    public void init(weighted_graph g) {
        this.graph = (WGraph_DS)g;
    }

    @Override
    public weighted_graph getGraph() {
        return this.graph;
    }
    /**
     * Compute a deep copy of this weighted graph.
     * @return
     */
    @Override
    public weighted_graph copy() {
        return new WGraph_DS(this.graph);
    }
    /**
     * traversing the graph using BFS and for every unvisited node raising the counter by one
     * and checking if the counter equals to the node size
     * @return
     */
    @Override
    public boolean isConnected() {
        Iterator<node_info> iterator = this.graph.getV().iterator();
        if (!iterator.hasNext())
            return true;
        int nodes_counter = this.BFS_Algorithm(iterator.next());
        return nodes_counter == this.graph.nodeSize();
    }

    /**
     * this method traversing the graph using BFS and updates the tag of the node with the
     * minimal distance from src.
     * @param src - start node
     * @param dest - end (target) node
     * @return  the length of the shortest path between src to dest
     */
    @Override
    public double shortestPathDist(int src, int dest) {
            if(src == dest && this.graph.getNode(src) != null){
                return 0;
            }
            HashMap<Integer,Double> minDistList = new HashMap<Integer,Double>();
            Queue<node_info> q = new LinkedList<node_info>();
            q.add(this.graph.getNode(src));
            minDistList.put(src,0.0);
            while (!q.isEmpty()){
                node_info node = q.poll();
                if(node!=null){
                for (node_info child:this.graph.getV(node.getKey())) {

                    if (!minDistList.containsKey(child.getKey()) || minDistList.get(child.getKey()) > minDistList.get(node.getKey()) + this.graph.getEdge(child.getKey(), node.getKey())) {
                        minDistList.put(child.getKey(), this.graph.getEdge(child.getKey(), node.getKey()) + minDistList.get(node.getKey()));
                        child.setTag(this.graph.getEdge(child.getKey(), node.getKey()) + minDistList.get(node.getKey()));
                        q.add(child);

                    }
                }
                }
            }

            if(minDistList.containsKey(dest)){
                return minDistList.get(dest);
            }
            return -1;

    }
    /**
     * this method calls to shortestPathDist method and creating a list of the nodes in the path.
     * when we call  shortestPathDist which marks the nodes with the minimal dist
     * and searching of the previous node in the  path.
     * @param src - start node
     * @param dest - end (target) node
     * @return  the length of the shortest path between src to dest
     */
    @Override
    public List<node_info> shortestPath(int src, int dest) {

        LinkedList<node_info> BestPathInTheWorld = new LinkedList<node_info>();
        double ShortestPathInTheWorld = shortestPathDist(src,dest);
        if(ShortestPathInTheWorld == 0){
            BestPathInTheWorld.add(this.graph.getNode(dest));
            return BestPathInTheWorld;
        }
        else if(ShortestPathInTheWorld == -1 ){
            return null;
        }
        else{
            node_info CurrNodeInThePath = this.graph.getNode(dest);
            BestPathInTheWorld.add(CurrNodeInThePath);
            while(CurrNodeInThePath.getKey() !=src ){
                for (node_info BestNi: this.graph.getV(CurrNodeInThePath.getKey())){
                    if(BestNi.getTag() + this.graph.getEdge(BestNi.getKey(),CurrNodeInThePath.getKey()) == CurrNodeInThePath.getTag()){
                        BestPathInTheWorld.addFirst(BestNi);
                        CurrNodeInThePath = BestNi;
                    }
                }
            }
            return BestPathInTheWorld;
        }
    }
    /**
     * Saves this weighted (undirected) graph to the given
     * file name
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.graph);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.graph = (WGraph_DS) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("graph class not found");
            c.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * traversing the graph using BFS and for every unvisited node raising the counter by one
     */
    private int BFS_Algorithm(node_info start) {
        Queue<node_info> q = new LinkedList<node_info>();
        Set<node_info> visited = new HashSet<>();

        q.add(start);
        while (!q.isEmpty()) {
            node_info node = q.poll();
            visited.add(node);
            Iterator<node_info> node_dataIterator = this.graph.getV(node.getKey()).iterator();
            while (node_dataIterator.hasNext()) {
                node_info shachen = node_dataIterator.next();
                if (!visited.contains(shachen)) {
                    q.add(shachen);
                }
            }
        }

        return visited.size();
    }

}
