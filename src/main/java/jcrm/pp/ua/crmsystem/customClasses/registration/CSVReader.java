package jcrm.pp.ua.crmsystem.customClasses.registration;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CSVReader {

    static String s = "/home/vlad/example.csv";

    public static void parse(String path) throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim())
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by Header names
                String firstName = csvRecord.get("First Name");
                String surname = csvRecord.get("Surname");
                String position = csvRecord.get("Position");
                String phones = csvRecord.get("Phones");
                String emails = csvRecord.get("Emails");
                String addresses = csvRecord.get("Addresses");
                //String birthday = csvRecord.get("Birthday");
                Date birthday = convertDate(csvRecord.get("Birthday"));

                //createContact(firstName,surname,position,phones,emails,addresses,birthday);

                String companyName = csvRecord.get("Company Name");
                String companyBranch = csvRecord.get("Company Branch");
                String companyPhones = csvRecord.get("Company Phones");
                String companyEmails = csvRecord.get("Company Emails");
                String companyAddresses = csvRecord.get("Company Addresses");
                String companyWebSite = csvRecord.get("Company Web-Site");

                //createCompany();

                System.out.println(firstName + surname + position + phones + emails + addresses + birthday
                + companyName + companyAddresses + companyBranch + companyPhones + companyEmails + companyWebSite);

            }
        }
    }

    private static void createContact(
            String firstName, String surname, String position, String phones,
            String emails, String addresses, Date birthdayDate){

    }
    private static void createCompany(
            String companyName, String companyBranch, String companyPhones,
            String companyEmails, String companyAddresses, String companyWebSite){

    }
    private static Date convertDate(String string){
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
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

