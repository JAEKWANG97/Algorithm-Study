package hellomatia;

import java.util.*;

class PGS_42892 {

    class Node {
        int num, x, y;
        Node left;
        Node right;

        Node(int num, int x, int y, Node left, Node right) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }
    }

    int idx;

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};

        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1], null, null);
        }

        //y값이 큰 순서대로, 같으면 x값이 작은 순서대로
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                if (n1.y == n2.y) {
                    return n1.x - n2.x;
                } else return n2.y - n1.y;
            }
        });

        //이진 tree 만들기
        Node parent = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            insertNode(parent, nodes[i]);
        }

        answer = new int[2][nodes.length];

        Node root = nodes[0];
        idx = 0;
        preorder(root, answer);
        idx = 0;
        postorder(root, answer);

        return answer;
    }

    public void insertNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }

    public void preorder(Node node, int[][] answer) {
        if (node != null) {
            answer[0][idx++] = node.num;
            preorder(node.left, answer);
            preorder(node.right, answer);
        }
    }

    public void postorder(Node node, int[][] answer) {
        if (node != null) {
            postorder(node.left, answer);
            postorder(node.right, answer);
            answer[1][idx++] = node.num;
        }
    }
}