import java.io.*;
import java.util.Scanner;

public class Parser<E extends Comparable<? super E>> {
    //Create a BST tree of Integer type
    private BST<Integer> mybst = new BST<>();

    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Remove extra spaces
                if (!line.isEmpty()) {
                    // Split the line into an array of strings using regular expression to handle spaces
                    String[] command = line.split("\\s+");
                    operate_BST(command);
                }
            }
        }
    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        System.out.println(command[0]);
        switch (command[0]) {
            // add your cases here
            // call writeToFile
            case "print":
                try {
                    FileWriter fileWriter = new FileWriter("result.txt");
                    printInOrder((Node<E>)mybst.root, fileWriter);
                    writeToFile("Printed BST elements to result.txt", "result.txt");
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "insert":
                if (command.length == 2) {
                    try {
                        int value = Integer.parseInt(command[1]); //integer
                        mybst.insert(value);
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command: " + command[0], "result.txt");
                    }
                } else {
                    writeToFile("Invalid Command: " + command[0], "result.txt");
                }
            break;
            case "remove":
                if(command.length == 2) {
                try {
                    int value = Integer.parseInt(command[1]);
                    mybst.remove(value);
                    if (value != 0) {
                        writeToFile("Removed: " + value, "result.txt");
                    } else {
                        writeToFile("Removed failed ", "result.txt");
                    }
                } catch (NumberFormatException e) {
                    writeToFile("Invalid Command: " + command[0], "result.txt");
                }
            } else {
                writeToFile("Invalid Command: " + command[0], "result.txt");
            }
            break;
            case "search":
                if (command.length == 2) {
                    try {
                        int value = Integer.parseInt(command[1]);
                        if (mybst.find(value) != null) {
                            writeToFile("Found: " + value, "result.txt");
                        } else {
                            writeToFile("Search failed", "result.txt");
                        }
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command: " + command[0], "result.txt");
                    }

                }
            default:
                writeToFile("Invalid Command", "result.txt");
                break; // Added 'break' here for the default case
        }
    }

    private void printInOrder(Node<E> node, FileWriter fileWriter) {
        if (node != null) {
            printInOrder(node.getLeft(), fileWriter); // Traverse left subtree
            try {
                fileWriter.write(node.getElement().toString() + "\n"); // Write the element to the file
            } catch (IOException e) {
                e.printStackTrace(); // Handle any IO exceptions
            }
            printInOrder(node.getRight(), fileWriter); // Traverse right subtree
        }
    }

    // Implement the writeToFile method
    // Generate the result file
    private void writeToFile(String content, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



