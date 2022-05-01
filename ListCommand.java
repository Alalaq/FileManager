import java.io.File;

public class ListCommand extends Command{
    public static void list() {
        try {
            System.out.println("Info Command");
            StringBuilder sb = new StringBuilder(System.lineSeparator());
            sb.append(String.format("%-40s", "Name"));
            sb.append(System.lineSeparator()).append(System.lineSeparator());

            File[] files = new File(PathManager.getPath()).listFiles();
            if (files != null) {
                for (File file : files) {
                    sb.append(String.format("%-40.35s", file.getName()));

                    sb.append(System.lineSeparator());
                }
            }
            System.out.println(sb);
        }
        catch (Exception ex){
            System.out.println("Something went wrong. Try again.");
            ex.printStackTrace();
        }
    }
}
