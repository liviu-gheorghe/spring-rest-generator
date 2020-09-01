package util;

import java.io.*;
import java.nio.file.*;

public class FileUtils {

    private static String defaultFileExtension = null;

    public static void copyDirectory(String sourceDirectory, String destinationDirectory) throws IOException {

        Path srcDirPath = Paths.get(sourceDirectory);
        Path destDirPath = Paths.get(destinationDirectory);
        if(destDirPath.startsWith(sourceDirectory)) {
            throw new InvalidPathException(destinationDirectory,"The destination directory cannot be a subdirectory of the source directory");
        }
        if(!Files.exists(srcDirPath)) {
            throw new FileNotFoundException("The source directory does not exist");
        }
        File source = srcDirPath.toFile();
        if(!source.isDirectory()) {
            throw new NotDirectoryException("The file is not a directory");
        }
        File destination = destDirPath.toFile();
        copyDirectoryRecursive(source,destination);
    }

    private static void copyDirectoryRecursive(File source, File dest) throws IOException {

        if(source.isFile()) {
            copyFileToDirectory(source,dest);
            System.out.printf("Copying file %s to file %s\n",source.getAbsolutePath(),dest.getAbsolutePath());
        }
        else {
            createDirectory(source,dest);
            File[] files = source.listFiles();
            if(files != null)
            for(File file : files) {
                copyDirectoryRecursive(file,Paths.get(dest.getAbsolutePath(),File.separator,source.getName()).toFile());
            }
        }
    }

    private static void createDirectory(File source, File dest) {
        String fileName = source.getName();
        File newFile = new File(dest,fileName);
        boolean ok = newFile.mkdir();
    }

    private static void copyFileToDirectory(File source, File dest) throws IOException {
        String fileName = source.getName();
        String extension = "";
        if(fileName.split("\\.").length < 2) {
            extension = (defaultFileExtension != null ? defaultFileExtension : "");
        }
        File newFile = new File(dest,fileName+extension);
        try (
            BufferedReader sourceFileReader = new BufferedReader(new FileReader(source));
            BufferedWriter newFileWriter = new BufferedWriter(new FileWriter(newFile));
        ) {
            String line;
            while ((line = sourceFileReader.readLine()) != null) {
                newFileWriter.write(line);
                newFileWriter.write("\n");
            }
        }
    }

    public static void setDefaultFileExtension(String s) {
        defaultFileExtension = s;
    }

/*    public static void main(String[] args) throws IOException {
        copyDirectory("/home/liviu/Desktop/file_utils/A/","/home/liviu/Desktop/file_utils/copy_dir/B");
    }*/
}