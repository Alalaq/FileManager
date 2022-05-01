import java.io.File;

public class DeleteCommand extends Command {
    public static void delete(String pathToDelete) {
        try {
            System.out.println("Delete Command");
            File filePath = new File(pathToDelete);

            if (!filePath.exists()) {
                System.out.println("File has not been deleted. Probably, file doesn't exist.");
            } else if (filePath.exists()) {
                if (filePath.isDirectory()) {
                    directoryDelete(filePath);
                }

                if (filePath.isFile()) {
                    fileDelete(filePath);
                }
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong. Try again.");
            ex.printStackTrace();
        }
    }

    public static void fileDelete(File file) {
        if (file.delete()) {
            System.out.println("File has been deleted.");
        }
        else {
            System.out.println("File delete has failed.");
        }
    }

    public static void directoryDelete(File file){
            File[] contents = file.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    if (f.isFile()){
                        f.delete();
                    }
                    else{
                        directoryDelete(f);
                    }
                }
            }
        if (file.delete()) {
            System.out.println("Directory has been deleted.");
        }

        else {
            System.out.println("Directory delete has failed.");
        }
    }
}
