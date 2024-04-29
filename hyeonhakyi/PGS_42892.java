import java.util.*;

class Solution {
    static int[][] answer;
    static int idx;
    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        ArrayList<Node> list = new ArrayList();
        for(int i = 0; i < nodeinfo.length; i++){
            list.add(new Node(i+1, nodeinfo[i][0],nodeinfo[i][1],null,null));
        }
        Collections.sort(list);

        Node root = list.get(0);
        for(int i = 1; i < list.size(); i++){
            insertNode(root,list.get(i));
        }

        idx = 0;
        preorder(root);
        idx = 0;
        postorder(root);

        return answer;
    }//solution end

    static void preorder(Node root){
        if(root != null){
            answer[0][idx++] = root.num;
            preorder(root.left);
            preorder(root.right);
        }
    }//preorder end

    static void postorder(Node root){
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            answer[1][idx++] = root.num;
        }
    }//postorder end

    static void insertNode(Node parent,Node child){
        if(parent.x > child.x){
            if(parent.left == null){
                parent.left = child;
            }else{
                insertNode(parent.left, child);
            }
        }else{
            if(parent.right == null){
                parent.right = child;
            }else{
                insertNode(parent.right,child);
            }
        }
    }//insertNode end

    static class Node implements Comparable<Node>{
        int num;
        int x;
        int y;
        Node left;
        Node right;
        public Node(int num,int x,int y, Node left, Node right){
            this.num = num;
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o1){
            if(this.y == o1.y){
                return this.x - o1.x;
            }
            return o1.y - this.y;
        }
    }



}