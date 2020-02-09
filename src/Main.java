import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path fromFolder = Paths.get("c:/AmericasCardroom");
        Path destFolder = Paths.get("c:/AmericasCardroom/new");

        if (!fromFolder.toFile().exists()) {
            System.out.println("Исходная директория не существует");
            System.exit(0);
        } else {
            try {
                copyFolder(fromFolder, destFolder);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        System.out.println("Done");
    }

    public static void copyFolder(Path fromDir, Path toDir) throws Exception {

        Files.walk(fromDir)
                .filter(path -> !toDir.relativize(path).toString().equals(""))
                .forEach(path ->
                {
                    try {
                        Files.copy(path, Paths.get(path.toString().replace(fromDir.toString(), toDir.toString())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}