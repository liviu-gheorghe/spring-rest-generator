package project_generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipArchiveDecoder {


    private final int BUFFER_SIZE = 1024;
    private String path;
    public ZipArchiveDecoder(String path) {
        this.path = path;
    }

    public void decode(InputStream archiveInputStream) throws IOException {

        File destinationDirectory = new File(path);
        byte[] buffer = new byte[BUFFER_SIZE];
        ZipInputStream zipInputStream = new ZipInputStream(archiveInputStream);
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while(zipEntry != null) {
            File newFile = newFile(destinationDirectory,zipEntry);
            if(!newFile.isDirectory()) {
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, len);
                }
                fileOutputStream.close();
            }
            //System.out.println("Entry name is " + zipEntry.getName());
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.closeEntry();
        zipInputStream.close();
    }

    private File newFile(File destinationDirectory, ZipEntry zipEntry) throws IOException {
        /*
        System.out.println(
                "\n\nZip entry : " + zipEntry.getName() +
                " isDir : " + zipEntry.isDirectory() + "\n\n"
        );*/
        String filename = zipEntry.getName();
        String fullPath = destinationDirectory+File.separator+filename;
        //System.out.println("\n\n\nFile full path is " + fullPath + "\n\n\n");

        File file = new File(fullPath);
        if(zipEntry.isDirectory()) {
            file.mkdir();
        }
        //System.out.println("Created file " + file.getCanonicalPath() + " is Directory ? -> " + file.isDirectory());
        String destinationDirectoryPath = destinationDirectory.getCanonicalPath();
        String filePath = file.getCanonicalPath();
        if(!filePath.startsWith(destinationDirectoryPath+File.separator)) {
            throw new IOException("Entry outside of the target directory");
        }
        return file;
    }
}