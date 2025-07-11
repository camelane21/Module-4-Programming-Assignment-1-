import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <JavaSourceFile>");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.exists() || !file.isFile()) 
            System.err.println("File not found: " + args[0]);
            System.exit(2);
        }

        // We'll use this stack to track opening symbols
        Stack<Character> stack = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

           
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println(line);

                for (char ch : line.toCharArray()) {
                    switch (ch) {
                        case '(':
                        case '{':
                        case '[':
                            stack.push(ch);
                            break;
                        case ')':
                            if (stack.isEmpty() || stack.pop() != '(') {
                                error(lineNumber, ch);
                            }
                            break;
                        case '}':
                            if (stack.isEmpty() || stack.pop() != '{') {
                                error(lineNumber, ch);
                            }
                            break;
                        case ']':
                            if (stack.isEmpty() || stack.pop() != '[') {
                                error(lineNumber, ch);
                            }
                            break;
                        default:
                            // ignore other characters
                    }
                }
            }

            if (!stack.isEmpty()) {
                System.out.println("Grouping symbols are NOT correctly paired.");
                System.exit(3);
            }

            System.out.println("Correct grouping pairs");
        }
        catch (IOException ex) {
            System.err.println("Error reading file: " + ex.getMessage());
        }
    }

    // Report an immediate mismatch and exit
    private static void error(int line, char ch) {
        System.out.printf(
            "Mismatch at line %d: unexpected '%c'%n", line, ch
        );
        System.out.println("Grouping symbols are NOT correctly paired.");
        System.exit(4);
    }
}
