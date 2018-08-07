package jcrm.pp.ua.crmsystem.customClasses.registration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class TxtFileReader {

    private static TxtFileReader singletonInstance = new TxtFileReader();
    //private final static String BASE_PATH = System.getProperty("user.home");
    private static Map<String, String[]> txtFilesInMemory = new HashMap<>();
    private static Map<String, String> jsonFilesInMemory = new HashMap<>();
    private static Map<String, Byte[]> filesInMemory = new HashMap<>();

    public static TxtFileReader getInstance() {
        return singletonInstance;
    }

//    public static void addFileToMemory(String pathToFile) {
//        Path filePath = Paths.get(BASE_PATH + pathToFile);
//        filesInMemory.put(getFileNameFromPath(pathToFile), list(filePath, dataToSplit(getFileNameFromPath(pathToFile))));
//    }

//    public static void addFilesToMemory(Collection<String> pathsToFiles) {
//        for (String path: pathsToFiles) {
//            addFileToMemory(path);
//        }
//    }

    public static void addFileToMemory(Path path){
        try {
            String fileName = getFileNameFromPath(path.toRealPath(LinkOption.NOFOLLOW_LINKS).toString());
            String[] data = list(path, dataToSplit(fileName));
            //filesInMemory.put(fileName,data);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

//    public static String[] readFileFromMemory(String fileName) {
//        return filesInMemory.get(fileName);
//    }

    private static String getFileNameFromPath(String pathToFile){
        int lastIndex = pathToFile.lastIndexOf('/');

        String fileName = null;
        if (lastIndex > 0) {
            fileName = fileName.substring(lastIndex + 1);
        }
        return fileName;
    }

    private static boolean dataToSplit(String fileName){
        String fileExtension = getExtension(fileName);
        if(fileExtension.equals("txt")) return true;
        else if (fileExtension.equals("json")) return false;
        else return false;
    }

    private static String[] list(Path path, boolean split) {
        String[] list = new String[1];
        try (BufferedReader reader = Files.newBufferedReader(path
                .toRealPath(LinkOption.NOFOLLOW_LINKS), StandardCharsets.UTF_8)) {

            String string = "";
            String sCurrentLine;

            while ((sCurrentLine = reader.readLine()) != null) {
                string = string + sCurrentLine;
            }

            if (split) list = string.split(";");
            else list[0] = string;

            for (int i = 0; i < list.length; i += 1) {
                String q = list[i].trim();
                list[i] = q;
            }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');

        String fileExtension = null;
        if (lastIndexOfDot > 0) {
            fileExtension = fileName.substring(lastIndexOfDot+1);
        }
        return fileExtension;
    }
}
