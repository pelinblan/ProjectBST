import java.io.*;
import java.util.Scanner;

public class Parser<E extends Comparable<? super E>> {
    //Create a BST tree of Integer type
    private BST<E> mybst = new BST<E>();

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
            case "insert":
                if (command.length == 2) {
                    try {
                        int value = Integer.parseValue(command[1]); //integer
                        mybst.insert(value);
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command: " + command[0], "./result.txt");
                    }
                } else {
                    writeToFile("Invalid Command: " + command[0], "./result.txt");
                }
                break;
            if (command.length == 2) {
                try {
                    E value = parseValue(command[1]);
                    E removedValue = mybst.remove(value);
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
                        E value = parseValue(command[1]);
                        if (mybst.find(value) != null) {
                            writeToFile("Found: " + value, "./result.txt");
                        } else {
                            writeToFile("Not found: " + value, "./result.txt");
                        }
                    } catch (NumberFormatException e) {
                        writeToFile("Invalid Command: " + command[0], "./result.txt");
                    }

                }
                // default case for Invalid Command
                default -> writeToFile("Invalid Command", "./result.txt");
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



