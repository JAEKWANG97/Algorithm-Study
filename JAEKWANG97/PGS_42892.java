import java.util.*;

class Solution {
    static class Node {
        int id;
        int x;
        int y;
        Node left;
        Node right;

        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    static class BinaryTree {
        Node root;

        public void insert(Node node) {
            if (root == null) {
                root = node;
            } else {
                insertRec(root, node);
            }
        }

        private void insertRec(Node current, Node node) {
            if (node.x < current.x) {
                if (current.left == null) {
                    current.left = node;
                } else {
                    insertRec(current.left, node);
                }
            } else {
                if (current.right == null) {
                    current.right = node;
                } else {
                    insertRec(current.right, node);
                }
            }
        }

        public List<Integer> preorderTraversal() {
            List<Integer> result = new ArrayList<>();
            preorder(root, result);
            return result;
        }

        private void preorder(Node node, List<Integer> result) {
            if (node != null) {
                result.add(node.id);
                preorder(node.left, result);
                preorder(node.right, result);
            }
        }

        public List<Integer> postorderTraversal() {
            List<Integer> result = new ArrayList<>();
            postorder(root, result);
            return result;
        }

        private void postorder(Node node, List<Integer> result) {
            if (node != null) {
                postorder(node.left, result);
                postorder(node.right, result);
                result.add(node.id);
            }
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        Arrays.sort(nodes, (n1, n2) -> n2.y - n1.y);
        
        BinaryTree tree = new BinaryTree();
        for (Node node : nodes) {
            tree.insert(node);
        }

        List<Integer> preorder = tree.preorderTraversal();
        List<Integer> postorder = tree.postorderTraversal();

        return new int[][]{
            preorder.stream().mapToInt(i -> i).toArray(),
            postorder.stream().mapToInt(i -> i).toArray()
        };
    }
}
