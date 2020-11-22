ex1;

Summary:
The project focus on unweighted undirected graph and has three main Classes.

node_data - an interface that represents a node in the graph implemented by NodeData.
graph - an interface that represents an undirected unweighted graph implemented by Graph_DS.
graph_algorithms - an interface that represents a set of algorithms to perform on a graph implemented by Graph_Algo.

NodeInfo class methods:

getKey – returns the key (id) of the node
getInfo – returns the info of the node
setInfo – set the info of the node
getTag – returns the tag of the node
setTag - set the tag of the node
WGraph_DS class methods:
getNode – returns a node contained in the graph 
hasEdge – checks if theres an edge between 2 nodes 
addNode – Add a node to the graph 
connect – connects an edge between 2 nodes with a given weight 
getV() - returns a collection of all the nodes in the graph 
getV(node_id) - returns a collection of all the adjacent nodes of a node with the node_id 
removeNode - removes a node from the graph and all its edges 
removeEdge – Removes an edge between 2 given nodes from the graph 
nodeSize – Returns the number of nodes in the graph 
edgeSize – returns the number of edges in the graph 
getMC – returns the number of changes made in the graph 
WGraph_Algo class methods:
init - inits a graph that the algorithms performs on 
GetGraph - returns the graph the algorithms performs on
copy - makes a deep copy of the graph 
isConnected - checks if there is a path from every 2 nodes in the graph
shortestPathDist - returns the shortest path 
shortestPath - returns a list containing the node's in the shortest path
save - save the graph that the algorithms performs on
load - loads a graph from a file to perform algorithms on