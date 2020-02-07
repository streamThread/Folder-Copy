import java.io.*;

public class Main {
    public static void main(String[] args) {
        File fromFolder = new File("c:/AmericasCardroom");
        File destFolder = new File("c:/AmericasCardroom/new");

        if (!fromFolder.exists()) {
            System.out.println("Исходная директория не существует");
            System.exit(0);
        } else {
            try {
                copyFolder(fromFolder, destFolder);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        System.out.println("Done");
    }

    public static void copyFolder(File fromDir, File toDir) throws IOException {

        if (fromDir.isDirectory()) {
            if (!toDir.exists()) {
                toDir.mkdir();
                if (!toDir.exists()) {
                    throw new IOException("Вы должны обладать правами администратора для записи новых файлов");
                }
                System.out.println("Directory copied from "
                        + fromDir + "  to " + toDir);
            }
            String files[] = fromDir.list();

            for (String file : files) {
                File srcFile = new File(fromDir, file);
                File destFile = new File(toDir, file);
                if (!destFile.getParentFile().equals(srcFile)) {
                    copyFolder(srcFile, destFile);
                }
            }
        } else {
            InputStream in = new FileInputStream(fromDir);
            OutputStream out = new FileOutputStream(toDir);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
            System.out.println("File copied from " + fromDir + " to " + toDir);
        }
    }
}