package ex1;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class WGraph_DS implements weighted_graph, Serializable {

    private int edges;
    private int mc;
    private HashMap<Integer, node_info> graph;
    private HashMap<Integer, HashMap<node_info, Double>> neighbors;

    public WGraph_DS() {
        this.graph = new HashMap<Integer, node_info>();
        this.neighbors = new HashMap<Integer, HashMap<node_info, Double>>();
        this.edges = 0;
        this.mc = 0;
    }
  // copy constructor //
    public WGraph_DS(weighted_graph original) {
        this.graph = new HashMap<Integer, node_info>();
        this.neighbors = new HashMap<Integer, HashMap<node_info, Double>>();
        for(node_info node: original.getV()){
            this.addNode(node.getKey());
        }

        for(node_info node: original.getV()){
            for(node_info neighbor: original.getV(node.getKey())){
                this.connect(node.getKey(),neighbor.getKey(),
                        original.getEdge(node.getKey(),neighbor.getKey()));
            }
        }
        this.edges = original.edgeSize();
        this.mc = original.getMC();
    }
    @Override
    public boolean equals(Object n){
        WGraph_DS temp = (WGraph_DS)n;
        if(this.getMC()!=temp.getMC()||
                this.nodeSize()!=temp.nodeSize()||
                this.nodeSize()!=temp.nodeSize())return false;
       if(!this.graph.equals(temp.graph))return false;

        if(!this.neighbors.keySet().equals(temp.neighbors.keySet()))return false;
        for (node_info node : this.getV()){
            for (node_info node2:this.getV(node.getKey())){
                if(!temp.hasEdge(node.getKey(),node2.getKey()))return false;
                if(temp.getEdge(node.getKey(),node2.getKey())!=this.getEdge(node.getKey(),node2.getKey()))return false;
            }
        }
    return true;
    }
    @Override
    /**
     * @return the node data by the node id
     */
    public node_info getNode(int key) {

        if(graph.containsKey(key))return graph.get(key);
        return null;
    }

    /**
     * check if exist edge between node1 and node2
     * @param node1
     * @param node2
     * @return true iff (if and only if) there is an edge between node1 and node2
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        if(node1==node2)return false;
        if(!graph.containsKey(node1)||!graph.containsKey(node2)){
            return false;
        }
        node_info n1=this.getNode(node1);
        node_info n2 = this.getNode(node2);
        if(neighbors.get(node1).containsKey(n2) && neighbors.get(node2).containsKey(n1)){
            return true;
        }

        return false;

    }

    @Override
    /**
     * Returns the weight if  exist edge between node1 and node2
     */
    public double getEdge(int node1, int node2) {
        if(hasEdge(node1,node2)){
            node_info n2=this.getNode(node2);
        return this.neighbors.get(node1).get(n2);
        }
        return -1;
    }

    @Override
    /**
     * add a new node to the graph with the given key
     */

    public void addNode(int key) {
        if (this.graph.containsKey(key) ) {
            return;
        }
        NodeInfo newNode = new NodeInfo(key);
        this.graph.put(key, newNode);
        this.neighbors.put(key,new HashMap<node_info, Double>());
        this.mc++;
    }
    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if(node1 == node2) return;
        if(w<0)return;
        //node_info n1 = null,n2 = null;
        node_info n1=this.getNode(node1);
        node_info n2=this.getNode(node2);
        if(n1==null||n2==null)return;


                    neighbors.get(node1).put(graph.get(node2),w);
                    neighbors.get(node2).put(graph.get(node1),w);
                mc++;
                edges++;
        }



    @Override
    /**
     * Return a collection the node in the graph
     */
    public Collection<node_info> getV() {
        return this.graph.values();
    }

    @Override
    /**
     * Return a collection  of neighbors with a given node id.
     */
    public Collection<node_info> getV(int node_id) {
        HashMap<node_info,Double> ne = this.neighbors.get(node_id);
        //not null
        if(ne == null) return null;
        return ne.keySet();
    }
    /**
     * clear the hash map neighbors
     * remove the node from all nodes
     * remove the node from the graph
     */
    @Override
    public node_info removeNode(int key) {
        if (graph.containsKey(key)) {
            node_info node = this.graph.get(key);
            int edgeSize=neighbors.get(key).values().size();
            if (getV(key) != null) {
                //for (node_info ni : neighborsN.get(key).values()) {
                for (node_info ni : neighbors.get(key).keySet()) {
                    neighbors.get(ni.getKey()).remove(key);
                    mc++;
                }
            }
            neighbors.remove(key);
            neighbors.remove(key);
            graph.remove(key);
            edges = edges - edgeSize;
            this.mc++;
            return node;

        }
        return null;
    }

    /**
     * Delete the edge from the graph between node1 and node2
     * Note: this method should run in O(1) time.
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {

          if(hasEdge(node1, node2)){
              neighbors.get(node1).remove(node2);
              neighbors.get(node2).remove(node1);
              this.edges--;
              this.mc++;
        }
    }
    @Override
    /**
     * Return the number of node
     */
    public int nodeSize() {
        return this.graph.size();
    }

    @Override
    /**
     * Return the number of the edge
     */
    public int edgeSize() {
        return this.edges;
    }
    /**
     * Return the mode counter
     */
    @Override
    public int getMC() {
        return this.mc;
    }

    protected class NodeInfo implements node_info,Serializable {
        private int key;
        private String info;
        private double tag;
        private NodeInfo father;

        public NodeInfo(int key) {

            this.key = key;
            this.tag = 0;
            this.info = "";
            this.father = null;
        }
       // copy constructor //
        public NodeInfo(NodeInfo node) {
            this.key = node.key;
            this.tag = node.tag;
            this.info = node.info;
        }
        /**
         * this method implements equals method from object class
         * @return
         */
        @Override
        public boolean equals(Object n){
            NodeInfo temp = (NodeInfo) n;
            if(this.getKey()!=temp.getKey() || this.getTag()!=temp.getTag() || !this.getInfo().equals(temp.getInfo()))return false;
            return true;
        }
        /**
         *  getter of key - Return the key (id) associated with this node.
         * Note: each node_data should have a unique key.
         *
         * @return
         */
        @Override
        public int getKey() {
            return this.key;
        }

        /**
         *  getter of info - return the remark (meta data) associated with this node.
         * @return
         */
        @Override
        public String getInfo() {
            return this.info;
        }

        /**
         * setter of info -  Allows changing the remark (meta data) associated with this node.
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        /**
         *  getter of the tag - Temporal data (aka distance, color, or state).
         * @return double the tag
         */
        @Override
        public double getTag() {
            return tag;
        }

        /**
         * Allow setting the "tag" value for temporal marking an node.
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(double t) {
            this.tag = t;

        }
    }
}