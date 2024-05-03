import java.util.*;
class Node {
    
    public int value;
    public int x;
    public int y;
    public Node right;
    public Node left;
    
    public Node (int value,int x, int y, Node right, Node left){
        this.value= value;
        this.x = x;
        this.y = y;
        this.right = right;
        this.left =left;
    }
    
}
 
class Solution {
    
int[][] result;
int idx =0;
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        
        Node[] nodes = new Node[nodeinfo.length];
        
        for(int i=0;i<nodeinfo.length;i++){
            nodes[i]=new Node(i+1,nodeinfo[i][0],nodeinfo[i][1],null,null);
        }
        
        //정렬규칙 Y는 내림차 X는 오름차순
        Arrays.sort(nodes,new Comparator<Node>(){
            public int compare(Node a , Node b){
                if(a.y==b.y){
                    return a.x-b.x;
                }
                return b.y-a.y;
            }
        } );
        
        Node parent = nodes[0];
        
        for(int i=1;i<nodes.length;i++){
            MakeTree(parent,nodes[i]);
        }
        
        
        result= new int[2][nodeinfo.length];
        
        
        preorder(parent);
        idx=0;
        postorder(parent);
        
        return answer=result;
    }
    
    public void MakeTree (Node parent, Node child){
      if(parent.x<child.x){      
        if(parent.right==null){
            parent.right=child;
        }
         else{
            MakeTree(parent.right,child);
         }
      }
      else{
        if(parent.left==null){
            parent.left=child;
        }
        else{
            MakeTree(parent.left,child);
        }
      }  
        
    }
    
    public void preorder(Node root){
        if(root!=null){
            result[0][idx++]=root.value;
            preorder(root.left);
            preorder(root.right);
        }
    }
    
    public void postorder(Node root){
        if(root!=null){
            postorder(root.left);
            postorder(root.right);
            result[1][idx++] = root.value;
        }
    }
}