package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private FileUtil() {}

    public static List<String> walkThroughFolder(String path){
        File file = new File(path);

        File[] fs = file.listFiles();

        List<String> files = new ArrayList<>();

        for(File f : fs){
            files.add(f.toString());
        }

        return files;
    }
}
