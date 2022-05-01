import java.io.File;

public class ChangeDirectoryCommand extends Command{
    public static void cd(String pathToChange) {
        try {
            System.out.println("Changing directory");

            String old_path = PathManager.getPath();

            if (pathToChange.equals("..")) {
                String back = new File(PathManager.getPath()).getParent();
                if (back != null) {
                    PathManager.setPath(back);
                }

            }
            else {
                String path = PathManager.getCorrectPath(pathToChange);
                if (path != null) {
                    PathManager.setPath(path);
                }
            }

            if (old_path.equals(PathManager.getPath())) {
                System.out.println("Changing directory failed. \nTry again.");
            } else {
                System.out.println("Changing directory was successfully executed.");
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong. Try again.");
            ex.printStackTrace();
        }
        //TODO add possibility to change dir with unabsolute path
    }
}
