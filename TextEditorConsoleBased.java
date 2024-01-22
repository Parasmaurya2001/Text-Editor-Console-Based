import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;

public class TextEditorConsoleBased {

    public static HashMap<String, String> map = new HashMap<>();

    // Adding a file
    public static void add() {
        System.out.println("Enter the File Name : ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();

        if (map.containsKey(name)) {
            System.out.println("File with this name already exists. Please Rename it.");
            return;
        }

        System.out.println("Enter Content : ");
        sc.nextLine();
        String content = sc.nextLine();
        System.out.println("---------------------------------------------");
        System.out.println("File created Successfully");
        System.out.println("---------------------------------------------");
        map.put(name, content);
    }

    // Opening a file
    public static void open() {
        System.out.println("Enter File Name : ");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (!map.containsKey(input)) {
            System.out.println("---------------------------------------------");
            System.out.println("File does not Exist");
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("The content of the file is : " + map.get(input));
            System.out.println("---------------------------------------------");
        }
    }

    // Delete a file
    public static void delete() {
        System.out.println("Enter File Name : ");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if (!map.containsKey(input)) {
            System.out.println("---------------------------------------------");
            System.out.println("File does not Exist");
            System.out.println("---------------------------------------------");
        } else {
            map.remove(input);
            System.out.println("---------------------------------------------");
            System.out.println("File Deleted Successfully");
            System.out.println("---------------------------------------------");
        }
    }

    // Edit a file
    public static void edit() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "-------------------------------------------------------------------------------------------");
        System.out.println("EDITING MENU");
        System.out.println(
                "-------------------------------------------------------------------------------------------");

        System.out.println("Enter the File Name : ");
        String input = sc.next();
        if (!map.containsKey(input)) {
            System.out.println("---------------------------------------------");
            System.out.println("Error !!! File not Found");
            System.out.println("---------------------------------------------");

        } else {

            Stack<String> undo = new Stack<>();
            Stack<String> redo = new Stack<>();
            undo.push(map.get(input));
            while (true) {
                System.out.println("---------------------------------------------");
                System.out.println("Enter the Editing Option :");
                System.out.println("---------------------------------------------");
                System.out.println("1. Insertion in file");
                System.out.println("2. Deletion in file");
                System.out.println("3. Undo Operation");
                System.out.println("4. Redo Operation");
                System.out.println("5. Return ot Main Menu");
                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        insertion(input, undo, redo);
                        break;

                    case "2":
                        deletion(input, undo, redo);
                        break;

                    case "3":
                        undooperation(input, undo, redo);
                        break;

                    case "4":
                        redooperation(input, undo, redo);
                        break;

                    case "5":
                        return;

                    default:
                        System.out.println("---------------------------------------------");
                        System.out.println("Wrong Choice");
                        System.out.println("---------------------------------------------");
                }
            }

        }
    }

    // Insertion -
    public static void insertion(String input, Stack<String> undo, Stack<String> redo) {
        while (true) {
            System.out.println(
                    "-------------------------------------------------------------------------------------------");
            System.out.println("INSERTION MENU");
            System.out.println(
                    "-------------------------------------------------------------------------------------------");
            System.out.println("Please Enter your choice :");
            System.out.println("1. Insertion at begining");
            System.out.println("2. Insertion at Middle");
            System.out.println("3. Insertion at End");
            System.out.println("4. Return to Editing Menu");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    insert_begin(input, undo, redo);
                    break;

                case "2":
                    insert_middle(input, undo, redo);
                    break;

                case "3":
                    insert_end(input, undo, redo);
                    break;

                case "4":
                    return;

                default:
                    System.out.println("---------------------------------------------");
                    System.out.println("Wrong Choice ");
                    System.out.println("---------------------------------------------");
                    break;
            }
        }
    }

    // Insert - begin
    public static void insert_begin(String input, Stack<String> undo, Stack<String> redo) {
        System.out.println("Enter the content that you want to append at the file begining");
        Scanner sc = new Scanner(System.in);
        String content = sc.next();
        undo.push(content + map.get(input));
        map.put(input, content + map.get(input));
        System.out.println("top : " + undo.peek());
        System.out.println("---------------------------------------------");
        System.out.println("New Data after modification is : " + map.get(input));
        System.out.println("---------------------------------------------");
        return;
    }

    // Insert - middle
    public static void insert_middle(String input, Stack<String> undo, Stack<String> redo) {
        System.out.println("The orginal String is : " + map.get(input));
        System.out.println("Enter the index at which you want to insert");
        Scanner sc = new Scanner(System.in);
        int idx = sc.nextInt();
        System.out.println("Enter the content that you want to append in between the file");
        String content = sc.next();
        String original = map.get(input);
        undo.push(original.substring(0, idx) + content + original.substring(idx, original.length()));
        map.put(input,
                original.substring(0, idx) + content + original.substring(idx, original.length()));
        System.out.println("---------------------------------------------");
        System.out.println("New Data after modification is : ");
        System.out.println("---------------------------------------------");
        System.out.println(map.get(input));
    }

    // Insert - end
    public static void insert_end(String input, Stack<String> undo, Stack<String> redo) {
        System.out.println("Enter the content that you want to append at the file end : ");
        Scanner sc = new Scanner(System.in);
        String content = sc.next();
        undo.push(map.get(input) + content);
        map.put(input, map.get(input) + content);
        System.out.println("---------------------------------------------");
        System.out.println("New Data after modification is : ");
        System.out.println("---------------------------------------------");
        System.out.println(map.get(input));
    }

    // Deletion -
    public static void deletion(String input, Stack<String> undo, Stack<String> redo) {
        while (true) {
            System.out.println(
                    "-------------------------------------------------------------------------------------------");
            System.out.println("DELETION MENU");
            System.out.println(
                    "-------------------------------------------------------------------------------------------");
            System.out.println("Please Enter your choice :");
            System.out.println("1. Deletion from begining");
            System.out.println("2. Deletion from Middle");
            System.out.println("3. Deletion from End");
            System.out.println("4. Return to Main Menu");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    delete_begin(input, undo, redo);
                    break;

                case "2":
                    delete_middle(input, undo, redo);
                    break;

                case "3":
                    delete_end(input, undo, redo);
                    break;

                case "4":
                    return;

                default:
                    System.out.println("---------------------------------------------");
                    System.out.println("Wrong Choice ");
                    System.out.println("---------------------------------------------");
                    break;
            }
        }
    }

    // delete - begin
    public static void delete_begin(String input, Stack<String> undo, Stack<String> redo) {
        System.out.println("The original string is : " + map.get(input));
        System.out.println("Enter the index upto which you want to delete : ");
        Scanner sc = new Scanner(System.in);
        int idx = sc.nextInt();
        String original = map.get(input);
        undo.push(original.substring(idx, original.length()));
        map.put(input, original.substring(idx, original.length()));
        System.out.println("---------------------------------------------");
        System.out.println("After Modification the updated String is : " + map.get(input));
        System.out.println("---------------------------------------------");
    }

    // delete - middle
    public static void delete_middle(String input, Stack<String> undo, Stack<String> redo) {
        System.out.println("The original string is : " + map.get(input));
        System.out.println("Enter the starting index of the content that you want to delete : ");
        Scanner sc = new Scanner(System.in);
        int siidx = sc.nextInt();
        System.out.println("Enter the ending index of the content that you want to delete : ");
        int eidx = sc.nextInt();
        String original = map.get(input);
        undo.push(original.substring(0, siidx) + original.substring(eidx, original.length()));
        map.put(input, original.substring(0, siidx) + original.substring(eidx, original.length()));
        System.out.println("---------------------------------------------");
        System.out.println("After Modification the updated String is : " + map.get(input));
        System.out.println("---------------------------------------------");
    }

    // delete - end
    public static void delete_end(String input, Stack<String> undo, Stack<String> redo) {
        System.out.println("The original string is : " + map.get(input));
        System.out.println("Enter the index to which you want to delete");
        Scanner sc = new Scanner(System.in);
        int idx = sc.nextInt();
        String original = map.get(input);
        undo.push(original.substring(0, idx));
        map.put(input, original.substring(0, idx));
        System.out.println("---------------------------------------------");
        System.out.println("After Modification the updated String is : " + map.get(input));
        System.out.println("---------------------------------------------");
    }

    // Undo operation
    public static void undooperation(String input, Stack<String> undo, Stack<String> redo) {
        redo.push(undo.pop());
        if (undo.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("No operations applied");
            System.out.println("---------------------------------------------");
            return;
        }
        System.out.println("Original String is : " + map.get(input));

        map.put(input, undo.peek());
        System.out.println("---------------------------------------------");
        System.out.println("After Undo operation the Content changes to : " + map.get(input));
        System.out.println("---------------------------------------------");
    }

    // Redo operation
    public static void redooperation(String input, Stack<String> undo, Stack<String> redo) {
        if (redo.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("No operations to redo");
            System.out.println("---------------------------------------------");
            return;
        }

        System.out.println("Original String is : " + map.get(input));
        String temp = redo.pop();
        undo.push(temp);
        map.put(input, temp);
        System.out.println("---------------------------------------------");
        System.out.println("After Redo Operation the Content changes to : " + map.get(input));
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {
        System.out
                .println(
                        "************************************************************************************************************************");
        System.out.println(
                "---------------------------------------------------------TEXT EDITOR---------------------------------------------------");
        System.out
                .println(
                        "************************************************************************************************************************");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "-------------------------------------------------------------------------------------------");
            System.out.println("MAIN MENU");
            System.out.println(
                    "-------------------------------------------------------------------------------------------");
            System.out.println("Enter the Operation No. : ");
            System.out.println("1. New");
            System.out.println("2. Open");
            System.out.println("3. Edit");
            System.out.println("4. Delete");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    add();
                    break;

                case "2":
                    open();
                    break;

                case "3":
                    edit();
                    break;

                case "4":
                    delete();
                    break;

                default:
                    System.out.println("Wrong Choice");
                    break;
            }
        }
    }
}
