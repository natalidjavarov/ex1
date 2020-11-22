package Testing;


import ex1.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals ;

import static org.junit.Assert.assertEquals ;

        class wGraph_Test {

            @Test
            void testsaveload() {
                weighted_graph g = new WGraph_DS();
                g.addNode(1);
                weighted_graph_algorithms ga = new WGraph_Algo();
                ga.init(g);
                ga.save("C:\\Users\\נטלי\\Desktop/\\natashaGraph.txt");
                ga.load("C:\\Users\\נטלי\\Desktop/\\natashaGraph.txt");


            }

            @Test
            void Test0() {

                WGraph_DS g = new WGraph_DS();
                int a = g.nodeSize();
                int b = g.edgeSize();
                double c = g.getEdge(0, 1);
                boolean d = g.hasEdge(0, 1);

                weighted_graph_algorithms gA = new WGraph_Algo();
                gA.init(g);
                boolean e = gA.isConnected();
                double dist = gA.shortestPathDist(0, 1);

                assertEquals(0, a, "error 0 nodeSize");
                assertEquals(0, b, "error 0 edgeSize");
                assertEquals(-1, c, "error 0 getEdge");
                assertEquals(false, d, "error 0 hasEdge");
                assertEquals(true, e, "error 0 isConnected");
                assertEquals(-1, dist, "error 0 shortestPathDist");

            }

            void Test1() {

                WGraph_DS g = new WGraph_DS();
                g.addNode(0);
                int a = g.nodeSize();//1
                int b = g.edgeSize();//0
                double c = g.getEdge(0, 5);//-1
                boolean d = g.hasEdge(0, 1);//false
                weighted_graph_algorithms gA = new WGraph_Algo();
                gA.init(g);
                g.removeNode(1);
                boolean e = gA.isConnected();//true
                int a2 = g.nodeSize();//0
                double dist = gA.shortestPathDist(0, 0);//0

                assertEquals(1, a, "error 0 nodeSize");
                assertEquals(0, b, "error 0 edgeSize");
                assertEquals(-1, c, "error 0 getEdge");
                assertEquals(false, d, "error 0 nodeSize");
                assertEquals(0, a2, "error 0 hasEdge");
                assertEquals(true, e, "error 0 isConnected");
                assertEquals(0, dist, "error 0 shortestpath");

            }

            @Test
            void Test2() {

                WGraph_DS g = new WGraph_DS();
                g.addNode(0);
                g.addNode(1);
                int a = g.nodeSize();//2
                int b = g.edgeSize();//0
                g.connect(0, 1, 3);
                g.connect(0, 1, 4);
                weighted_graph_algorithms gA = new WGraph_Algo();
                gA.init(g);
                boolean e = gA.isConnected();//true
                double dist = gA.shortestPathDist(0, 0);//0
                int b2 = g.edgeSize();//1
                double c = g.getEdge(0, 1);//4
                g.removeEdge(0, 1);//removing the edge
                boolean e2 = gA.isConnected();//false
                g.connect(1, 0, 4);
                g.removeNode(1);//removing a node
                int b3 = g.edgeSize();//0
                boolean d = g.hasEdge(0, 1);//false
                int a2 = g.nodeSize();//1

                assertEquals(2, a, "error in test2 nodeSize");
                assertEquals(0, b, "error in test2 edgeSize");
                assertEquals(4, c, "error in test2 getEdge");
                assertEquals(1, a2, "error in test2 nodeSize");
                assertEquals(false, d, "error in test2 hasEdge");
                assertEquals(true, e, "error in test2 isConnected");
                assertEquals(0, dist, "error in test2 shortestPath");


            }

            @Test
            void test3() {
                weighted_graph g = new WGraph_DS();
                for (int i = 0; i < 9; i++) {
                    g.addNode(i);
                }
                g.connect(0, 1, 3);
                g.connect(1, 7, 4);
                g.connect(7, 2, 2);
                ////***************need to finish graph****************
                g.connect(2, 3, 6);
                g.connect(3, 4, 1);
                g.connect(4, 8, 8);
                g.connect(8, 0, 4);
                g.connect(2, 5, 1);
                g.connect(5, 6, 8);
                g.connect(0, 3, 2);
                for (node_info n:g.getV()){
                    System.out.println();
                    System.out.print("key = "+n.getKey()+" -> ");
                    for (node_info ni: g.getV(n.getKey())){
                        System.out.print(ni.getKey()+",");
                        System.out.print(g.getEdge(ni.getKey(),n.getKey())+",");
                    }

                }
//                int a = g.nodeSize();//9
//                int b = g.edgeSize();//10
//                double c = g.getEdge(3, 2);//6
//                boolean d = g.hasEdge(3, 4);//true
                weighted_graph_algorithms gA = new WGraph_Algo();
                gA.init(g);
//                boolean e = gA.isConnected();
//                double dist = gA.shortestPathDist(3, 8);//2 + 4 = 6
//                g.removeEdge(2, 5);
//                boolean e2 = gA.isConnected();//false
//                g.removeNode(3);
//                int a2 = g.nodeSize();//8
//                int b2 = g.edgeSize();//6
//                double c2 = g.getEdge(2, 5);//-1
//                boolean d2 = g.hasEdge(0, 3);//false
//                double dist2 = gA.shortestPathDist(2, 8);//12
//
//
//                assertEquals(9, a, "error in test3 nodeSize");
//                assertEquals(10, b, "error in test3 edgeSize");
//                assertEquals(6, c, "error in test3 getEdge");
//                assertEquals(true, d, "error test3 hasEdge");
//                assertEquals(true, e, "error test3 isConnected");
//                assertEquals(6, dist, "error test3 shortestPath");
//                assertEquals(false, e2, "error test3 isConnect after removeEdge");
//                assertEquals(8, a2, "error in test3 nodeSize after removeNode");
//                assertEquals(6, b2, "error test3 edgeSize after removeNode");
//                assertEquals(-1, c2, "error test3 getEdge");
//                assertEquals(false, d2, "error test3 hasEdge");
//                assertEquals(12, dist2, "error test3 shortestPath");
                double o = gA.shortestPathDist(3,8);
                assertEquals(6,o,"dssa");

            }


        }

