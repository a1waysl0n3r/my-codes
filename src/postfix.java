import java.util.Scanner;
import java.util.Stack;


class postfix_stack{
    int top = -1;
    String[] stack  = new String[1000];
    String infix = "";
    String op = "^/+*-";
    int prec(String operator){
        if(operator.equals("^"))
            return 3;
        else if(operator.equals("/") || operator.equals("*"))
            return 2;
        else if (operator.equals("+") || operator.equals("-")) {
            return 1;
        }
        return 0;
    }
    void push(String push_object){
        stack[++top] = push_object;
    }
    void pop(){
        infix = infix.concat(stack[top--]);
    }

    String evaluation(String[] expression){
        for(String push_object : expression){
            if(op.contains(push_object)){
                if(top == -1){
                    push(push_object);
                }
                if(prec(stack[top]) < prec(push_object)){
                    push(push_object);
                }
                else{
                    pop();
                    push(push_object);
                }
            }
            else if(push_object.equals("(")){
                push(push_object);
            }
            else if (push_object.equals(")")){
                while(top != -1 && !stack[top].equals("(")){
                    pop();
                }
                top--;
            }
            else{
                infix = infix.concat(push_object);
            }
        }
        return infix;
    }

}
public class postfix{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String exp = sc.nextLine();
        exp = "(" + exp + ")";
        String[] ac_exp = exp.replaceAll("\\s", "").split("");
        postfix_stack ps = new postfix_stack();
        String new_exp = ps.evaluation(ac_exp);
        System.out.printf("Postfix : %s\n", new_exp);

        ExpressionTree expressionTree = new ExpressionTree(new_exp);
        Node top = expressionTree.Evaluate();
        expressionTree.inOrder(top);
    }

}


