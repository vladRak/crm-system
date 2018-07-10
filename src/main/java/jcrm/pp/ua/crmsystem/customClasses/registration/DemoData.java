package jcrm.pp.ua.crmsystem.customClasses.registration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DemoData {

    final static String BASE_PATH ="./src/main/resources/db/";
    //final static String BASE_PATH ="./WEB-INF/classes/db/";
    final static String BRANCHES_FILE ="branches.txt";
    final static String COMPANIES_FILE ="companies.txt";
    final static String COUNTRIES_FILE ="countries.txt";
    final static String NAMES_FILE ="names.txt";




    public void readText() throws IOException {
//        String pathBase = "./src/main/resources/db/";
//        String pathComp = "companies.txt";
//        String pathName = "names.txt";


//        Path testFileCompPath = Paths.get(BASE_PATH + COMPANIES_FILE);
//        Path testFileNamePath = Paths.get(BASE_PATH + NAMES_FILE);
//
//        String[] comp = list(testFileCompPath);
//        String[] name = list(testFileNamePath);
//        String a ="@";
//        String com =".com";
//
//        for (String s : comp) {
//            String name2 = name[10];
//            s.toLowerCase();
//            System.out.println(
//                    name2.toLowerCase().replaceAll("\\s", "_") + a +
//                            s.toLowerCase().replaceAll("\\s", "-") + com);
//        }
    }

    public void randomTest() {
        //+882753865260
        String number = "+";

        for (int i = 0; i < 12; i++) {
            int nI = (int) (Math.random() * 9);
            if (i == 0 && nI == 0) nI = +1;
            number = number + String.valueOf(nI);
        }

        System.out.println(number);
    }


//    protected String[] list(Path path){
//        String[] list = new String[0];
//        try (BufferedReader reader = Files.newBufferedReader(path
//                .toRealPath(LinkOption.NOFOLLOW_LINKS), StandardCharsets.UTF_8)) {
//
//            String sCurrentLine = "";
//            String sq;
//
//            while ((sq = reader.readLine()) != null) {
//                sCurrentLine = sCurrentLine + sq;
//            }
//
//            list = sCurrentLine.split(";");
//
//            for (int i = 0; i < list.length; i += 1) {
//                String q = list[i].trim();
//                list[i] = q;
//            }
//
//            return list;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }


}
