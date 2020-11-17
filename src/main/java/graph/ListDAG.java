package graph;

import com.sun.javafx.geom.Edge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ListDAG {

    // 图的邻接表表示法：边表结点
    private class EdgeNode {
        int idx;    // 存储该顶点对应的下标
        EdgeNode next;  // 指向下一个邻接点
    }

    // 顶点表结点
    private class VertexNode {
        char data;  // 存储顶点信息
        EdgeNode firstEdge; // 边表头指针
    }

    private List<VertexNode> vertexNodes; // 顶点数组

    /**
     * 创建图
     * @param   vexs 顶点数组
     * @param edges 边数组
     * @return
     * @date 2020/11/17
     */
    public ListDAG(char[] vexs, char[][] edges) {

        int vLen = vexs.length;
        int eLen = edges.length;

        // 初始化顶点
        vertexNodes = new ArrayList<>();
        for (int i = 0; i < vLen; i++) {
            // 新建VertexNode
            VertexNode vNode = new VertexNode();
            vNode.data = vexs[i];
            vNode.firstEdge = null;
            // 塞到vertexNodes
            vertexNodes.add(vNode);
        }

        // 初始化边
        for (int i = 0; i < eLen; i++) {
            char start = edges[i][0];
            char end = edges[i][1];

            int pos1 = getPosition(start);
            int pos2 = getPosition(end);

            // 初始化边
            EdgeNode tmp = new EdgeNode();
            tmp.idx = pos2;
            // 将tmp链接到"pos1所在链表的末尾"
            if (vertexNodes.get(pos1).firstEdge == null)
                vertexNodes.get(pos1).firstEdge = tmp;
            else
                linkLast(vertexNodes.get(pos1).firstEdge, tmp);
        }
    }

    // 将node结点链接到list的最后
    private void linkLast(EdgeNode list, EdgeNode node) {
        EdgeNode p = list;
        while (p.next != null) {
            p = p.next;
        }
        p.next = node;
    }

    // 返回顶点在vertexNodes中的下标位置
    private int getPosition(char ch) {
        for (VertexNode v : vertexNodes) {
            if (v.data == ch) {
                return vertexNodes.indexOf(v);
            }
        }
        return -1;
    }

    /**
     * DFS
     * @param
     * @return
     * @date 2020/11/17
     */
    public List<Character> DFS() {
        List<Character> res = new ArrayList<>();
        boolean[] visited = new boolean[vertexNodes.size()];

        for (int i = 0; i < vertexNodes.size(); i++) {
            if (!visited[i])
                DFS(i, visited, res);
        }
        return res;
    }



    private void DFS(int i, boolean[] visited, List<Character> ans) {
        EdgeNode node;
        visited[i] = true;
        ans.add(vertexNodes.get(i).data);
        node = vertexNodes.get(i).firstEdge;
        while (node != null) {
            if (!visited[node.idx])
                DFS(node.idx, visited, ans);
            node = node.next;
        }
    }


    /**
     * BFS
     * @param
     * @return
     * @date 2020/11/17
     */
    public List<Character> BFS() {
        List<Character> res = new ArrayList<>();
        int head = 0, rear = 0;
        int[] queue = new int[vertexNodes.size()];
        boolean[] visited = new boolean[vertexNodes.size()];

        for (int i = 0; i < vertexNodes.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                res.add(vertexNodes.get(i).data);
                queue[rear++] = i;
            }

            while (head != rear) {
                int j = queue[head++];  // 出队列
                EdgeNode node = vertexNodes.get(j).firstEdge;
                while (node != null) {
                    int k = node.idx;
                    if (!visited[k]) {
                        visited[k] = true;
                        res.add(vertexNodes.get(k).data);
                        queue[rear++] = k;
                    }
                    node = node.next;
                }
            }
        }
        return res;
    }

    public List<Character> topologicalSort() {
        int index = 0;  // index是tops的下标
        int num = vertexNodes.size();
        int[] ins = new int[num]; // 入度数组
        List<Character> tops = new ArrayList<>(); // 结果
        Queue<Integer> queue = new LinkedList<>();   // 辅助队列

        // 统计每个顶点的入度数
        for (int i = 0; i < num; i++) {
            EdgeNode node = vertexNodes.get(i).firstEdge;
            while (node != null) {
                ins[node.idx]++;
                node = node.next;
            }
        }

        // 将所有入度为0的顶点入队列
        for (int i = 0; i < num; i++) {
            if (ins[i] == 0)
                queue.offer(i); // 入队列
        }

        while (!queue.isEmpty()) {
            int j = queue.poll().intValue();    // 出队列， j是顶点在vertexNodes中的序号
            ++index;
            tops.add(vertexNodes.get(j).data); // 将该顶点添加到tops
            EdgeNode node = vertexNodes.get(j).firstEdge;   // 获取以该顶点为起点的出边队列

            // 将与"node"关联的结点的入度-1
            // 若减1之后，该结点的入度为0， 则将该结点添加到队列中
            while (node != null) {
                // 将结点(序号为node.idx)的入度减1
                ins[node.idx]--;
                if (ins[node.idx] == 0)
                    queue.offer(node.idx);
                node = node.next;
            }
        }
        if (index != num) {
            System.out.println("Graph has a cycle\n");
            return new ArrayList<>();
        }

        return tops;
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][] {
                {'A', 'G'},
                {'B', 'A'},
                {'B', 'D'},
                {'C', 'F'},
                {'C', 'G'},
                {'D', 'E'},
                {'D', 'F'},
                {'G', 'B'}
        };
        ListDAG dag;
        dag = new ListDAG(vexs, edges);
        System.out.println(dag.topologicalSort());
    }
}
