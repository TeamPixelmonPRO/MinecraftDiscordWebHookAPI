package ex.qaz.mdwhapi.utils;

import ex.qaz.mdwhapi.mdwhapimain;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static String readJsonFromFile(String filename) throws IOException {
        String content = null;
        try {
            content = readFile(mdwhapimain.jsonlistpath + "\\" +filename+".json", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    public static void writeJsonToFile(String filename, String json) throws IOException {
        File emptyJsonExample = new File(mdwhapimain.jsonlistpath+"\\"+filename+".json");
        PrintWriter writer = new PrintWriter(new FileWriter(emptyJsonExample));
        writer.write(json);
        writer.close();
    }
    public static String readFile(String path, Charset encoding) throws IOException {
        String content = Files.lines(Paths.get(path), encoding)
                .collect(Collectors.joining(System.lineSeparator()));

        return content;
    }
}
