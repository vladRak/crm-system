package jcrm.pp.ua.crmsystem.customClasses.registration;

import com.kanishka.virustotal.dto.FileScanReport;
import com.kanishka.virustotal.dto.ScanInfo;
import com.kanishka.virustotal.dto.VirusScanInfo;
import com.kanishka.virustotal.exception.APIKeyNotFoundException;
import com.kanishka.virustotal.exception.UnauthorizedAccessException;
import com.kanishka.virustotalv2.VirusTotalConfig;
import com.kanishka.virustotalv2.VirustotalPublicV2;
import com.kanishka.virustotalv2.VirustotalPublicV2Impl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

//@Component
public class VirusResolver {

    //private static final VirusResolver singletonInstance = new VirusResolver();

//    @Value("${virustotal.api.key}")
    private String APIKEY;

    private static VirusResolver instance;

    private VirusResolver(){}

    public static VirusResolver getInstance(){
        if(instance == null){
            instance = new VirusResolver();
        }
        return instance;
    }

//    public static VirusResolver getInstance() {
//        return singletonInstance;
//    }

    public void setAPIKEY(String apikey){
        APIKEY = apikey;
    }

    public String getAPIKEY(){
        return APIKEY;
    }

    public boolean scanedFileIsSafety(File file) throws InterruptedException {
        String resource = scanFile(file);
        boolean safety = false;
        FileScanReport fileScanReport;

        for(int i = 0; i < 6; i++){
            fileScanReport = getFileScanReport(resource);

            if (fileScanReport.getResponseCode()== -2) Thread.currentThread().wait(1000*10);
            else if (fileScanReport.getPositives() == 0) return true;
            else safety = false;

        }
        return safety;
    }

    private String scanFile(File file) {
        String resource = null;
        try {
            VirusTotalConfig.getConfigInstance().setVirusTotalAPIKey(APIKEY);
            VirustotalPublicV2 virusTotalRef = new VirustotalPublicV2Impl();

            ScanInfo scanInformation = virusTotalRef.scanFile(file);

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

            if (scanInformation.getResponseCode() == 1) resource = scanInformation.getResource();

        } catch (APIKeyNotFoundException ex) {
            System.err.println("API Key not found! " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Unsupported Encoding Format!" + ex.getMessage());
        } catch (UnauthorizedAccessException ex) {
            System.err.println("Invalid API Key " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Something Bad Happened! " + ex.getMessage());
        }
        return resource;
    }

    private FileScanReport getFileScanReport(String resource) {
        String positives = null;
        try {
            VirusTotalConfig.getConfigInstance().setVirusTotalAPIKey(APIKEY);
            VirustotalPublicV2 virusTotalRef = new VirustotalPublicV2Impl();

            FileScanReport report = virusTotalRef.getScanReport(resource);

            positives = String.valueOf(report.getPositives());

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

            Map<String, VirusScanInfo> scans = report.getScans();
            for (String key : scans.keySet()) {
                VirusScanInfo virusInfo = scans.get(key);
                System.out.println("Scanner : " + key);
                System.out.println("\t\t Resut : " + virusInfo.getResult());
                System.out.println("\t\t Update : " + virusInfo.getUpdate());
                System.out.println("\t\t Version :" + virusInfo.getVersion());
            }

            return report;

        } catch (APIKeyNotFoundException ex) {
            System.err.println("API Key not found! " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            System.err.println("Unsupported Encoding Format!" + ex.getMessage());
        } catch (UnauthorizedAccessException ex) {
            System.err.println("Invalid API Key " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Something Bad Happened! " + ex.getMessage());
        }
        return null;
    }
}
