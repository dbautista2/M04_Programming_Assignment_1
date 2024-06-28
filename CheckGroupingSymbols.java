// 20.11
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class CheckGroupingSymbols {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java CheckGroupingSymbols <filename>");
            return;
        }

        String filename = args[0];
        try {
            if (checkGroupingSymbols(filename)) {
                System.out.println("The file has correct grouping symbols.");
            } else {
                System.out.println("The file has incorrect grouping symbols.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static boolean checkGroupingSymbols(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            Stack<Character> stack = new Stack<>();
            int lineNumber = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    switch (c) {
                        case '(':
                        case '{':
                        case '[':
                            stack.push(c);
                            break;
                        case ')':
                            if (stack.isEmpty() || stack.pop() != '(') {
                                System.out.println("Error: ')' mismatch at line " + lineNumber);
                                return false;
                            }
                            break;
                        case '}':
                            if (stack.isEmpty() || stack.pop() != '{') {
                                System.out.println("Error: '}' mismatch at line " + lineNumber);
                                return false;
                            }
                            break;
                        case ']':
                            if (stack.isEmpty() || stack.pop() != '[') {
                                System.out.println("Error: ']' mismatch at line " + lineNumber);
                                return false;
                            }
                            break;
                        default:
                            // Ignore other characters
                            break;
                    }
                }
                lineNumber++;
            }
            
            // Check if there are unmatched opening symbols left in the stack
            if (!stack.isEmpty()) {
                System.out.println("Error: Unmatched opening symbols at the end of file.");
                return false;
            }
        }
        
        return true;
    }
}