
import java.util.Scanner;


public class VirtualCommandPrompt {
    private Directory root;
    private Directory currentDirectory;

    public VirtualCommandPrompt() {
        this.root = new Directory("R:", null);
        this.currentDirectory = root;
    }

    private String getPath() {
        StringBuilder path = new StringBuilder();
        Directory temp = currentDirectory;
        while (temp != null) {
            path.insert(0, temp.getName() + "\\");
            temp = temp.getParent();
        }
        return path.toString();
    }

    private void executeCommand(String command) {
        String[] parts = command.split(" ");
        String operation = parts[0].toLowerCase();

        try {
            switch (operation) {
                case "mkdir":
                    if (parts.length < 2) throw new IllegalArgumentException("Usage: mkdir <directory_name>");
                    String dirName = parts[1];
                    Directory newDir = new Directory(dirName, currentDirectory);
                    if (!currentDirectory.addDirectory(newDir)) {
                        System.out.println("Error: Directory already exists.");
                    }
                    break;
                case "cd":
                    if (parts.length < 2) throw new IllegalArgumentException("Usage: cd <directory_name>");
                    String targetDir = parts[1];
                    boolean found = false;
                    for (Directory child : currentDirectory.getChildren()) {
                        if (child.getName().equals(targetDir)) {
                            currentDirectory = child;
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Error: Directory not found.");
                    break;
                case "bk":
                    if (currentDirectory.getParent() != null) {
                        currentDirectory = currentDirectory.getParent();
                    } else {
                        System.out.println("Error: Already at the root directory.");
                    }
                    break;
                case "ls":
                    if (currentDirectory.getChildren().isEmpty()) {
                        System.out.println("No directories found.");
                    } else {
                        for (Directory child : currentDirectory.getChildren()) {
                            System.out.println(child.getName() + " (" + child.getDateTime() + ")");
                        }
                        System.out.println("Total subfolders: " + currentDirectory.getChildren().size());
                    }
                    break;
                case "find":
                    if (parts.length < 2) throw new IllegalArgumentException("Usage: find <directory_name>");
                    Directory foundDir = currentDirectory.findDirectory(parts[1]);
                    if (foundDir != null) {
                        System.out.println("Directory found: " + foundDir.getName());
                    } else {
                        System.out.println("Directory not found.");
                    }
                    break;
                case "tree":
                    displayTree(currentDirectory, "", true);
                    break;
                case "exit":
                    System.out.println("Exiting Virtual Command Prompt. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error: Invalid command.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayTree(Directory dir, String prefix, boolean isLast) {
        System.out.println(prefix + (isLast ? "\u2514\u2500 " : "\u251c\u2500 ") + dir.getName());
        for (int i = 0; i < dir.getChildren().size(); i++) {
            displayTree(dir.getChildren().get(i), prefix + (isLast ? "    " : "\u2502   "), i == dir.getChildren().size() - 1);
        }
    }

    public static void main(String[] args) {
        VirtualCommandPrompt vcp = new VirtualCommandPrompt();
        Scanner scanner = new Scanner(System.in);
        System.out.println("::::::::::: Welcome to Metacube Virtual Command Prompt :::::::::");

        while (true) {
            System.out.print(vcp.getPath() + "> ");
            String command = scanner.nextLine();
            vcp.executeCommand(command);
        }
    }
}