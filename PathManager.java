import java.io.File;

public class PathManager {
        private static String path = System.getProperty("user.dir");

        static String getPath() {
            return path;
        }

        static void setPath(String path) {
            PathManager.path = path;
        }

        static String getCorrectPath(String path) {
            String correctPath = null;
            if (path != null && !path.equals(".")) { // я не понимаю, почему, но если ввести точку, то программа себя странно ведёт
                File file = new File(path);
                if (file.getParent() != null) {
                    if (file.exists()){
                        correctPath = path;
                    }
                }
                else {
                    File new_file = new File(PathManager.path, path);
                    if (new_file.exists()){
                        correctPath = new_file.getPath();
                    }
                }
            }
            return correctPath;
        }
}
