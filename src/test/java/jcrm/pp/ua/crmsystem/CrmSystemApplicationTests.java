package jcrm.pp.ua.crmsystem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.kanishka.virustotal.dto.FileScanReport;
import com.kanishka.virustotal.dto.ScanInfo;
import com.kanishka.virustotal.exception.APIKeyNotFoundException;
import com.kanishka.virustotal.exception.UnauthorizedAccessException;
import com.kanishka.virustotalv2.VirusTotalConfig;
import com.kanishka.virustotalv2.VirustotalPublicV2;
import com.kanishka.virustotalv2.VirustotalPublicV2Impl;
import jcrm.pp.ua.crmsystem.customClasses.registration.Branch;
import jcrm.pp.ua.crmsystem.customClasses.registration.VirusResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmSystemApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void branchTest() {
        Branch[] list = Branch.values();
        for (Branch b : list) {
            System.out.println(b.branch());
        }
        List<String> qwe = Arrays.asList(list.toString());
        for (String s : qwe) {
            System.out.println(s);
        }
    }

    @Test
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

    @Test
    public void readTextTest() throws IOException {
        String pathBase = "./src/main/resources/db/";
        String pathComp = "companies.txt";
        String pathName = "names.txt";

        Path testFileCompPath = Paths.get(pathBase + pathComp);
        Path testFileNamePath = Paths.get(pathBase + pathName);

        String[] comp = list(testFileCompPath);
        String[] name = list(testFileNamePath);
        String a ="@";
        String com =".com";

        for (String s : comp) {
            String name2 = name[10];
            s.toLowerCase();
            System.out.println(
                    name2.toLowerCase().replaceAll("\\s", "_") + a +
                            s.toLowerCase().replaceAll("\\s", "-") + com);
        }
    }

    protected String[] list(Path path){
        String[] list = new String[0];
        try (BufferedReader reader = Files.newBufferedReader(path
                .toRealPath(LinkOption.NOFOLLOW_LINKS), StandardCharsets.UTF_8)) {

            String sCurrentLine = "";
            String sq;

            while ((sq = reader.readLine()) != null) {
                sCurrentLine = sCurrentLine + sq;
            }

            list = sCurrentLine.split(";");

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

    @Test
    public void pathTest() throws IOException {

//        System.out.println(System.getProperty("user.home"));
//        System.out.println(System.getProperty("user.dir"));
        //Path path = Paths.get(System.getProperty("user.dir") + "/WEB-INF/resources/db/names.txt");
//        Path path = Paths.get("classes/db/names.txt");
//        System.out.println(path);
//        System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
//        try (BufferedReader reader = Files.newBufferedReader(path
//                .toRealPath(LinkOption.NOFOLLOW_LINKS), StandardCharsets.UTF_8)) {
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Resource resource = new ClassPathResource("db/names.txt");
        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("db/names.txt").getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result.toString());


    }


    @Test
    public void path2Test() throws IOException, ProcessingException {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
        String[] list = new String[0];
        Path path = Paths.get(System.getProperty("user.home") + "/schemas/type.json");

        try (BufferedReader reader = Files.newBufferedReader(path
                .toRealPath(LinkOption.NOFOLLOW_LINKS), StandardCharsets.UTF_8)) {

            String sCurrentLine = "";
            String sq;

            while ((sq = reader.readLine()) != null) {
                sCurrentLine = sCurrentLine + sq;
            }

            list = sCurrentLine.split(";");

            for (int i = 0; i < list.length; i += 1) {
                String q = list[i].trim();
                list[i] = q;
            }

            for (String s:list) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        
        for (String s:list) {
            System.out.println(list.length);
            System.out.println(s);
            actualObj = mapper.readTree(s);
        }
        System.out.println(actualObj.toString());
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema jSchType = factory.getJsonSchema(actualObj);
        System.out.println(jSchType.toString());
    }

    @Test
    public void splitTest() throws IOException, ProcessingException {
//        String[] strings = "type.json".split("\\.");
//        for (String s:strings) {
//            System.out.println(s);
//        }
        try (Stream<Path> paths = Files.walk(Paths.get(System.getProperty("user.home") + "/schemas"))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        }
        //String ext1 = FilenameUtils.getExtension();

        String fileName = "read.txt";
        int lastIndexOfDot = fileName.lastIndexOf('.');

        String fileExtension = null;
        if (lastIndexOfDot > 0) {
            fileExtension = fileName.substring(lastIndexOfDot+1);
        }
        System.out.println(fileExtension);
    }

    @Test(timeout = 1000*61)
            public void scanTest(){
        try {
            VirusTotalConfig.getConfigInstance().setVirusTotalAPIKey("fca649a750fd2c4bc86ad6f8dc85a4a0545798ba2ba537fa47dc157d54c44469");
            VirustotalPublicV2 virusTotalRef = new VirustotalPublicV2Impl();

            ScanInfo scanInformation = virusTotalRef.scanFile(new File(System.getProperty("user.home") + "/schemas/email.json"));

            System.out.println("___SCAN INFORMATION___");
            System.out.println("MD5 :\t" + scanInformation.getMd5());
            System.out.println("Perma Link :\t" + scanInformation.getPermalink());
            System.out.println("Resource :\t" + scanInformation.getResource());
            System.out.println("Scan Date :\t" + scanInformation.getScanDate());
            System.out.println("Scan Id :\t" + scanInformation.getScanId());
            System.out.println("SHA1 :\t" + scanInformation.getSha1());
            System.out.println("SHA256 :\t" + scanInformation.getSha256());
            System.out.println("Verbose Msg :\t" + scanInformation.getVerboseMessage());
            System.out.println("Response Code :\t" + scanInformation.getResponseCode());
            System.out.println("done.");

            int counts = 6;
            if(scanInformation.getResponseCode() == 1){
                wait(1000*10);
                for(int i =0; i<counts; i++){
                    try {
                    boolean b = getReportScan(scanInformation.getResource());
                    if (!b) return;
                    }catch (Exception ex) {
                        System.out.println("Please wait. Count = " + i + "/" + counts);
                    }
                }
            }

        } catch (APIKeyNotFoundException ex) {
            System.err.println("API Key not found! " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Unsupported Encoding Format!" + ex.getMessage());
        } catch (UnauthorizedAccessException ex) {
            System.err.println("Invalid API Key " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Something Bad Happened!1 " + ex.getMessage());
        }
    }

    public void scanInfo(ScanInfo scanInformation) throws InterruptedException {
//        int counts = 6;
//        if(scanInformation.getResponseCode() == 1 && counts < 5 ){
//            wait(1000*10);
//            for(int i =0; i<counts; i++){
//                try {
//                getReportScan(scanInformation.getResource());
//                }catch (Exception ex) {
//                    System.out.println("Please wait. Count = " + i + "/" + counts);
//                }
//            }
            //            wait(1000*30);
//            try {
//                getReportScan(scanInformation.getResource());
//                counts = 0;
//            }catch (Exception ex) {
//                counts = counts +1;
//                scanInfo(scanInformation);
//            }
  //      }
    }

    //@Test
    public boolean getReportScan(String resource1){
                boolean oneMoreTime = false;
        try {
            VirusTotalConfig.getConfigInstance().setVirusTotalAPIKey("fca649a750fd2c4bc86ad6f8dc85a4a0545798ba2ba537fa47dc157d54c44469");
            VirustotalPublicV2 virusTotalRef = new VirustotalPublicV2Impl();

            String resource=resource1;
            FileScanReport report = virusTotalRef.getScanReport(resource);
            if (report == null) return true;

            System.out.println("MD5 :\t" + report.getMd5());
            System.out.println("Perma link :\t" + report.getPermalink());
            System.out.println("Resourve :\t" + report.getResource());
            System.out.println("Scan Date :\t" + report.getScanDate());
            System.out.println("Scan Id :\t" + report.getScanId());
            System.out.println("SHA1 :\t" + report.getSha1());
            System.out.println("SHA256 :\t" + report.getSha256());
            System.out.println("Verbose Msg :\t" + report.getVerboseMessage());
            System.out.println("Response Code :\t" + report.getResponseCode());
            System.out.println("Positives :\t" + report.getPositives());
            System.out.println("Total :\t" + report.getTotal());

            try {
                if (report.getPositives() > 0){
                    System.out.println("Ololo");
                }else System.out.println("All is OK!");
            }catch (Exception ex) {
                return true;
            }


            return false;
//            Map<String, VirusScanInfo> scans = report.getScans();
//            for (String key : scans.keySet()) {
//                VirusScanInfo virusInfo = scans.get(key);
//                System.out.println("Scanner : " + key);
//                System.out.println("\t\t Resut : " + virusInfo.getResult());
//                System.out.println("\t\t Update : " + virusInfo.getUpdate());
//                System.out.println("\t\t Version :" + virusInfo.getVersion());
//            }

        } catch (APIKeyNotFoundException ex) {
            System.err.println("API Key not found! " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Unsupported Encoding Format!" + ex.getMessage());
        } catch (UnauthorizedAccessException ex) {
            System.err.println("Invalid API Key " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Something Bad Happened!2 " + ex.getMessage());
        }
        return oneMoreTime;
    }

    @Test
    public void valueTest(){
        System.out.println(VirusResolver.getInstance().getAPIKEY());
    }

    @Test
    public void extensionTest() throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        File file = new File(System.currentTimeMillis() + ".php" + ".csv");
        System.out.println(file.getAbsolutePath());


        String fileExtentions = ".exe,.dmg,.mp3,.jar";
        String fileName = file.getName();
        String[] list = fileName.split("\\.");

        Thread.sleep(1000*10);

        for (String s:list) {
            System.out.println(s);
        }

        int lastIndex = fileName.lastIndexOf('.');
        System.out.println(lastIndex);
        String substring = fileName.substring(lastIndex, fileName.length());
        System.out.println(substring);

        try {
            File file2 =File.createTempFile("BORAJI", ".tmp");

            if(file2.exists()){
                System.out.println("Temporary File Path: "+file2.getAbsolutePath());
            }
        } catch (IOException e) {
            // Temp file creation failed
            e.printStackTrace();
        }
    }


    @Test
    public void dateTest() {
        String string = "1/11 /1981";
        String stringWithoutSpaces = string.replaceAll(" ","");
        char[] chars = {'/','-','.',','};
        String separator = "";
        boolean separatorFind = false;
        for (char c: stringWithoutSpaces.toCharArray()){
            for (char ch: chars) {
                if (c == ch){
                    separator = String.valueOf(ch);
                    separatorFind = true;
                    break;
                }
            }
            if (separatorFind) break;
        }

        DateFormat format = new SimpleDateFormat("dd" + separator + "MM" + separator + "yyyy", Locale.ENGLISH);

        try {
            Date date = format.parse(stringWithoutSpaces);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        DateFormat format1 = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
//        DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//        List<DateFormat> dateFormats = new ArrayList<>();
//        dateFormats.addAll(Arrays.asList(format1,format2));
//        for (DateFormat df: dateFormats) {
//            try {
//                Date date = df.parse(stringWithoutSpaces);
//                System.out.println(date);
//            } catch (ParseException e) {
//                //e.printStackTrace();
//            }
//        }
    }

}
