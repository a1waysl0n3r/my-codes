import java.util.Scanner;

class Node{
    String e;
    int val;
    Node left;
    Node right;
    Node(String data) {
        this.e = data;
        left = right = null;
    }
    Node(int data) {
        this.val = data;
        left = right = null;
    }
}

class ExpressionTree{
    Node exp_root = null;
    Node create(String data){
        Node newNode = new Node(data);
        return newNode;
    }
    int top = -1;
    String eval;
    ExpressionTree(String postfix_eval){
        this.eval = postfix_eval;
    }
    void push(Node nodeObj){
        node_stack[++top] = nodeObj;
    }
    Node pop(){
        Node pop_node = node_stack[top--];
        return pop_node;
    }
    void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.printf("%s ", node.e);
        inOrder(node.right);
    }
    postfix_stack ps = new postfix_stack();
    Node[] node_stack = new Node[1000];
    Node Evaluate(){
        String[] evaluation_string = eval.split("");
        for(String e : evaluation_string){
            if(!ps.op.contains(e)){
                Node newNode = create(e);
                push(newNode);
            }
            else{
                Node a = pop();
                Node b = pop();
                Node exp_node = create(e);
                exp_node.left = b;
                exp_node.right = a;
                exp_root = exp_node;
                push(exp_node);
            }
        }
        return exp_root;
    }

}