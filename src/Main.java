import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) {
        Path fromFolder = Paths.get("c:/AmericasCardroom");
        Path destFolder = Paths.get("c:/new");

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

        Files.walk(fromDir).filter(path -> Files.isDirectory(path))
                .filter(path -> fromDir.toFile().equals(path.toFile().getParentFile()))
                .forEach(path -> {
                    try {
                        Files.createDirectories(Paths.get(path.toString().replace(fromDir.toString(), toDir.toString())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        Files.walkFileTree(fromDir, new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        try {
                            Thread.sleep(1000);
                            Files.copy(file, Paths.get(file.toString().replace(fromDir.toString(), toDir.toString())));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return FileVisitResult.CONTINUE;
                    }
                }
        );
    }
}


//        if (Files.isDirectory(fromDir)) {
//             if (!Files.exists(toDir)) {
//                Files.createDirectory(toDir);
//                if (!Files.exists(toDir)) {
//                    throw new IOException("Вы должны обладать правами администратора для записи новых файлов");
//                }
//                System.out.println("Directory copied from "
//                        + fromDir + "  to " + toDir);
//            }
//            String files[] = fromDir.list();
//
//            for (String file : files) {
//                File srcFile = new File(fromDir, file);
//                File destFile = new File(toDir, file);
//                if (!destFile.getParentFile().equals(srcFile)) {
//                    copyFolder(srcFile, destFile);
//                }
//            }
//        } else {
//            InputStream in = new FileInputStream(fromDir);
//            OutputStream out = new FileOutputStream(toDir);
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = in.read(buffer)) > 0) {
//                out.write(buffer, 0, length);
//            }
//            in.close();
//            out.close();
//            System.out.println("File copied from " + fromDir + " to " + toDir);
//        }
//    }
//}