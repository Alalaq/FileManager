import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

//TODO figure out how to use this class or delete this class
public abstract class Command {
    public static String pathInput() {
        System.out.print("Input path: ");
        Scanner u = new Scanner(System.in);
        String input = u.nextLine();
        return input;
    }
}
