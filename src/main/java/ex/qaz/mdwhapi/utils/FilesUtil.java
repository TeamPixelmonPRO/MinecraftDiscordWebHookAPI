package ex.qaz.mdwhapi.utils;

import ex.qaz.mdwhapi.mdwhapimain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesUtil {
    public static List<String> getAllFileNames(File parentDir) {
        List<String> filenames = new ArrayList<String>();
        for (int i = 0; i < parentDir.listFiles().length;i++) {
            filenames.add(parentDir.listFiles()[i].getName().replace(".json",""));
        }
        return filenames;
    }
    public static void reload(File parentDir)
    {
        mdwhapimain.configDir = getAllFileNames(parentDir);
    }
    public static boolean isExist(String a, List<String> massStringA) {
        for (String s : massStringA) {
            if (a.equals(s)) {
                return true;
            }
        }
        return false;
    }
    public static String getJsonFromFile(String filename) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get(mdwhapimain.jsonlistpath + "\\" +filename+".json"));
        String line = reader.readLine();
        return line;
    }
}
