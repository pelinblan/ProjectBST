import java.io.*;
import java.util.Scanner;

public class Parser<E extends Comparable<? super E>> {
    //Create a BST tree of Integer type
    private BST<Integer> mybst = new BST<Integer>();

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
        switch (command[0]) {
            // add your cases here
            // call writeToFile
            case "print":
            try {
                FileWriter fileWriter = new FileWriter("./result.txt");
                printInOrder(mybst.root, fileWriter);
                fileWriter.close();
                writeToFile("Printed BST elements to result.txt", "./result.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            case "insert":
                if (command.length == 2) {
                    try {
                        int value = Integer.parseInt(command[1]); //integer
                        E element = (E)Integer.valueOf(value); // Create an object of type E with the integer value
                        mybst.insert((Integer)element);
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command: " + command[0], "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command: " + command[0], "./result.txt");
                }
            break;
            case "remove":
                if(command.length == 2) {
                try {
                    int value = Integer.parseInt(command[1]);
                    E removedValue = (E)Integer.valueOf(value);
                    mybst.remove((Integer)removedValue);
                    if (removedValue != null) {
                        writeToFile("Deleted: " + value, "./result.txt");
                    } else {
                        writeToFile("Value not found: " + value, "./result.txt");
                    }
                } catch (NumberFormatException e) {
                    writeToFile("Invalid Command: " + command[0], "./result.txt");
                }
            } else {
                writeToFile("Invalid Command: " + command[0], "./result.txt");
            }
            break;
            case "search":
                if (command.length == 2) {
                    try {
                        int value = Integer.parseInt(command[1]);
                        E searchVal = (E)Integer.valueOf(value);
                        if (mybst.find((Integer)searchVal) != null) {
                            writeToFile("Found: " + value, "./result.txt");
                        } else {
                            writeToFile("Not found: " + value, "./result.txt");
                        }
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command: " + command[0], "./result.txt");
                    }

                }
            default:
                writeToFile("Invalid Command", "./result.txt");
                break; // Added 'break' here for the default case
        }
    }

    private void printInOrder(Node<E> node, FileWriter fileWriter) {
        if (node != null) {
            printInOrder(node.getRight(), fileWriter);
            fileWriter.write(node.getElement().toString() + "\n"); //
            printInOrder(node.getRight(), fileWriter);
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



