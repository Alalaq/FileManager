import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoCommand extends Command{
    public static void info() {
        //TODO make sort for Types, size and maybe date
        //like: info sortedBy(...)
        System.out.println("Info Command");
        StringBuilder sb = new StringBuilder(System.lineSeparator());
        sb.append(String.format("%-40s", "Name")).
                append(String.format("%-15s", "Flags")).
                append(String.format("%-15s", "Type")).
                append(String.format("%-15s", "Size")).
                append(String.format("%-25s", "Date of creation")).
                append(String.format("%-25s", "Date of change"));
        sb.append(System.lineSeparator()).append(System.lineSeparator());

        File[] files = new File(PathManager.getPath()).listFiles();
        if (files != null) {
            for (File file : files) {
                sb.append(String.format("%-40.35s", file.getName()));

                String flags = "";
                if (file.canWrite()) {
                    flags += "-w-";
                }
                if (file.canRead()) {
                    flags += "-r-";
                }
                if (file.canExecute()) {
                    flags += "-x-";
                }
                if (!flags.equals("")) {
                    sb.append((String.format("%-15s", flags)));
                }
                sb.append(String.format("%-15s", file.isDirectory() ? "<Directory>" : "<File>"));
                sb.append(String.format("%-15s", getFileSize(file)));
                try {
                    BasicFileAttributes attr = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class).readAttributes();
                    SimpleDateFormat sf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

                    sb.append(String.format("%-25s", sf.format(new Date(attr.creationTime().toMillis()))));
                    sb.append(String.format("%-25s", sf.format(new Date(attr.lastModifiedTime().toMillis()))));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                sb.append(System.lineSeparator());
            }
        }
        System.out.println(sb);
    }

    private static String getFileSize(File file) {
        String result;
        double size = file.length();
        if (size / 1024 < 1) {
            result = String.format("%.1f %s", size, "bytes");
        } else {
            size /= 1024;
            if (size / 1024 < 1) {
                result = String.format("%.1f %s", size, "kb");
            } else {
                size /= 1024;
                if (size / 1024 < 1) {
                    result = String.format("%.1f %s", size, "mb");
                }
                else {
                    result = String.format("%.1f %s", size/ 1024, "gb");
                }
            }
        }
        return result;
    }
}
