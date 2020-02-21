package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFiles {

    private File selectedFile;

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public List<File> getFiles() {
        File[] pairFiles = selectedFile.listFiles();
        List <File> allFiles = new ArrayList<>();

        for (File file: pairFiles) {
            if (file.isDirectory()) {
                openFolder(file,allFiles);
            } else if ((file.getName().contains(".java")) || (file.getName().contains(".cpp"))) {
                allFiles.add(file);
            }
        }

        for (File file: allFiles) {
            System.out.println(file);
        }
        return allFiles;
    }

    public void openFolder(File folder, List<File> allFiles) {
        File[] files = folder.listFiles();
        for (File file: files) {
            if (file.isDirectory()) {
                openFolder(file,allFiles);
            } else if ((file.getName().contains(".java")) || (file.getName().contains(".cpp"))) {
                allFiles.add(file);
            }
        }

    }
}
