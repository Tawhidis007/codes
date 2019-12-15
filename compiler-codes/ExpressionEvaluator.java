
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Tahwid Shahriar
 */
public class ExpressionEvaluator {

    public static void main(String[] args) {
        HashMap<String, Integer> identifiers = new HashMap<>();
        ArrayList<String> declarations = new ArrayList<>();
        ArrayList<String> expressions = new ArrayList<>();
        Stack<Character> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        StringBuilder postfix_expr = new StringBuilder();
        int count = 0;
        int count2 = 0;
        String number1 = "";
        boolean finale = true;

        //user inputs how many declarations and what they are
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter how many identifiers you want to work with : ");
        int num_of_declarations = sc.nextInt();
 
        System.out.println("Start entering identifiers with values.");
        for (int i = num_of_declarations; i > 0; i--) {
            String dec = sc.next();
            declarations.add(dec);
        }

        //user inputs how many expressions and what they are
        System.out.println("How many expressions do you want to give? ");
        int num_of_expressions = sc.nextInt();

        System.out.println("Enter Your expressions: ");
        for (int i = num_of_expressions; i > 0; i--) {
            expressions.add(sc.next().trim());//nextLine does not work
        }

        while (count < declarations.size()) {
            String[] tokens = declarations.get(count).split("=");
            identifiers.put(tokens[0], Integer.parseInt(tokens[1]));
            count++;
        }

        while (count2 < expressions.size()) {
            char[] exp = expressions.get(count2).toCharArray();
            for (int i = 0; i < exp.length; i++) {

                //for operands
                if (Character.isLetterOrDigit(exp[i])) {
                    postfix_expr.append(exp[i]);
                } // to check for '('
                else if (exp[i] == '(') {
                    stack1.push(exp[i]);
                } //to check for ')'
                else if (exp[i] == ')') {
                    while (!stack1.empty() && stack1.peek() != '(') {
                        postfix_expr.append(stack1.pop());
                    }
                    if (!stack1.empty() && stack1.peek() != '(') {
                        System.out.println("Compiler Error");
                    } else {
                        stack1.pop();
                    }
                } //checking for operator
                else if (isOperator(exp[i])) {
                    if (stack1.empty()) {
                        stack1.push(exp[i]);
                    } else if (!stack1.empty()) {

                        if (prec(exp[i]) <= prec(stack1.peek())) { //problem here must recheck
                            postfix_expr.append(stack1.pop());
                            //not checking for the second time (!!!)
                            stack1.push(exp[i]);

//                            Iterator<Character> iterator = stack1.iterator();
//                            System.out.print("=====Contents of stack =======\n");//for debug
//                            while (iterator.hasNext()) {
//                                System.out.print(iterator.next());
//                                System.out.println();
//                            }
                        } else if (prec(exp[i]) > prec(stack1.peek())) {
                            stack1.push(exp[i]);
                        }

                        //System.out.println("RIGHT NOW ON TOP : " + stack1.peek());//for debug

                    }

                } //for white space 
                else if (exp[i] == ' ') {
                    continue;
                }

                //System.out.println(postfix_expr);

            }
            while (!stack1.empty()) {
                postfix_expr.append(stack1.pop());
            }
            number1 = postfix_expr.toString();
        System.out.println("======================================================");
        System.out.println("your post fix expression for "+expressions.get(count2)+" is : " + number1);

        //------------------POST FIX EVALUATION NOW-------------------------------------------------
        int result = 0;
        int value = 0;
        char[] eva = number1.toCharArray();
        for (int i = 0; i < eva.length; i++) {

            //to check if operands
            if (Character.isLetterOrDigit(eva[i])) {
                //if the got character is a key in the defined hashmap,rememb(string,int)
                if (identifiers.containsKey(Character.toString(eva[i]))) {
                    value = identifiers.get(Character.toString(eva[i]));
                    stack2.push(value);
                } else {
                    System.out.println("Compilation Error");
                    finale = false;
                    break;
                }
            }
            //to check if operators
            if (isOperator(eva[i])) {
                if (stack2.size() >= 2) {

                    if (eva[i] == '+') {
                        int v1 = stack2.pop();
                        int v2 = stack2.pop();
                        result = v1 + v2;
                        stack2.push(result);
                    } else if (eva[i] == '-') {
                        int v1 = stack2.pop();
                        int v2 = stack2.pop();
                        result = v2 - v1;
                        stack2.push(result);
                    } else if (eva[i] == '*') {
                        int v1 = stack2.pop();
                        int v2 = stack2.pop();
                        result = v1 * v2;
                        stack2.push(result);
                    } else if (eva[i] == '/') {
                        int v1 = stack2.pop();
                        int v2 = stack2.pop();
                        result = v2 / v1;
                        stack2.push(result);
                    } else {
                        System.out.println("Compilation error");
                        finale = false;
                    }
                } else {
                    System.out.println("Wrong postfix expression");
                    finale = false;
                }
            }
            //System.out.println("so far : " + result);//for debugging
        }
        if (finale) {
            System.out.println("Your postfix expression evaluation for it is : "+result);
            System.out.println("======================================================");
        }
        postfix_expr= new StringBuilder();
        number1="";
            count2++;
        }
        
    }

    static boolean isOperator(char c) {
        char[] operators = {'+', '-', '*', '/'};
        boolean flag = false;
        for (char op : operators) {
            if (c == op) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    static int prec(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        } else {
            return -1;
        }
    }

}
