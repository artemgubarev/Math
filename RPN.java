import java.math.BigInteger;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Алгоритм обратной польской нотации для выражений с + и *.
 * По сути можно конечно дописать умножение и деление.
 *
 */
public class RPN {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        //RPN - reverse polish notation
        Stack<String> stackRPN = convertToRPN(expression);

//        Collections.reverse(stackRPN);
//        stackRPN.stream().forEach(x -> System.out.print(x + " "));

        System.out.println("Result: " + calculate(stackRPN));
    }

    public static BigInteger calculate(Stack<String> stackRPN){
        Collections.reverse(stackRPN);
        Stack<BigInteger> stackCalc = new Stack<>();

        while (!stackRPN.isEmpty()) {
            String token = stackRPN.pop();

            if(isNumeric(token)){
                stackCalc.push(BigInteger.valueOf(Long.parseLong(token)));
            } else if (isOperator(token)){
                BigInteger operand1 = stackCalc.pop();
                BigInteger operand2 = stackCalc.pop();

                switch (token){
                    case "+" :
                        stackCalc.push(operand1.add(operand2));
                        break;
                    case "*" :
                        stackCalc.push(operand1.multiply(operand2));
                        break;
                }
            }
        }

        return stackCalc.pop();
    }

    public static Stack<String> convertToRPN(String expression){

        Stack<String> stackRPN = new Stack<>();
        Stack<String> symbolStack = new Stack<>();

        String[] arr = expression.split(" ");

        for(int i = 0; i < arr.length; i++) {
            if (arr[i].isEmpty()) {
                continue;
            } else if (isNumeric(arr[i])) {
                stackRPN.push(arr[i]);
            } else {
                if (symbolStack.isEmpty()) {
                    symbolStack.push(arr[i]);
                } else if (symbolStack.lastElement().equals("*")) {
                    stackRPN.push(symbolStack.pop());
                    symbolStack.push(arr[i]);
                } else {
                    symbolStack.push(arr[i]);
                }
            }
        }

        while (!symbolStack.isEmpty()){
            stackRPN.push(symbolStack.pop());
        }
        return stackRPN;
    }

    public static boolean isNumeric(String s){
        return s.chars().allMatch(Character::isDigit);
    }

    public static boolean isOperator(String s){
        return s.equals("*") | s.equals("+");
    }
}