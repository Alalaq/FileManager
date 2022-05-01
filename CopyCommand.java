import java.io.*;
import java.util.regex.*;

public class CopyCommand extends Command{
    public static void copy(String path1, String path2) {
        System.out.println("Copy Command");
        try {
            File new_file = new File(path1 + stringReturner(path2));
            File fileToCopy = new File(path2);

            if (!((new_file.isFile() || new_file.isDirectory()) && (fileToCopy.isFile() || fileToCopy.isDirectory()))){
                throw new IllegalArgumentException("Copying has failed. There's no such path.");
            }

            if (!new_file.createNewFile()){
                throw new IllegalArgumentException("Copying has failed. File with such name already exists in this directory.");
            }

            InputStream in = new FileInputStream(fileToCopy);
            OutputStream out = new FileOutputStream(new_file);

            while (in.available() > 0) {
                out.write(in.read());
                out.flush();
            }
            System.out.println("Copying is successfully finished.");

            in.close();
            out.close(); //Пытался сделать в try/catch но идея почему-то не видела переменные in и out
        }
        catch (FileNotFoundException exc){
            System.out.println("Copying has failed. Not found file to copy.");
            exc.printStackTrace();

        }
        catch (IOException e) {
            System.out.println("Copying has failed. File is not readable.");
            e.printStackTrace();
        }

    }
    protected static String stringReturner(String str){
        String regex = "\\\\([\\w\\d]+[.]{1}[\\w\\d]+)";
        Matcher matcher = Pattern.compile(regex).matcher(str);

        String path = "";
        while (matcher.find()){
            path += matcher.group(0);
        }

        return path;
    }
}
