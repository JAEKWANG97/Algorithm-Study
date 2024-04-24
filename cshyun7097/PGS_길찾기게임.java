import java.util.*;

class PGS_길찾기게임 {

    static int[][] result;
    static int idx;

    static class Node {
        int x;
        int y;
        int value;
        Node left;
        Node right;

        public Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                if (n1.y == n2.y) return n1.x - n2.x;
                else return n2.y - n1.y;
            }
        });

        //트리생성
        Node root = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            add(root, nodes[i]);
        }

        result = new int[2][nodeinfo.length];
        idx = 0;
        //전위
        preorder(root);
        idx = 0;
        //후위
        postorder(root);
        return result;
    }

    private static void add(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else add(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else add(parent.right, child);
        }
    }

    private static void preorder(Node root) {
        if (root != null) {
            result[0][idx++] = root.value;
            preorder(root.left);
            preorder(root.right);
        }
    }

    private static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            result[1][idx++] = root.value;
        }
    }
}
