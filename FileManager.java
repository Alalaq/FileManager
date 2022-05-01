import java.util.Scanner;

public class FileManager {
    public static void main(String[] args) {
        System.out.println("Welcome to the File Manager!");

        operation();
    }

    public static void operation(){
        try {
            System.out.print(PathManager.getPath()+ ": ");

            Scanner s = new Scanner(System.in);
            String input = s.nextLine();

            String[] str = input.split(" ");
            input = str[0];

            Commands operation = Commands.valueOf(input.toLowerCase());
            System.out.print("Selected command: ");

            switch (operation) {
                case exit:
                    ExitCommand.exit();

                case list:
                    ListCommand.list();
                    operation();

                case info:
                    InfoCommand.info();
                    operation();

                case copy:
                case cd:
                case delete:
                    String first_path = str[1];
                    if (input.equals("copy")) {
                        String second_path = str[2];
                        CopyCommand.copy(first_path, second_path);
                        operation();
                    }

                    else if (input.equals("cd")){
                        ChangeDirectoryCommand.cd(first_path);
                        operation();
                    }

                    else if (input.equals("delete")){
                        DeleteCommand.delete(first_path);
                        operation();
                    }
            }

        } catch (Exception e) {
            System.out.println("Operation does not exist." + "\nTry again.");
            operation();
        }
    }
}
