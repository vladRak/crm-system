package jcrm.pp.ua.crmsystem.customClasses.registration;

import fi.solita.clamav.ClamAVClient;

public class ClamAvClient {
    ClamAVClient cl = new ClamAVClient("127.0.0.1", 3310,5000);


    public boolean scan(byte[] input) throws Exception {
        byte[] reply;
        boolean clear = false;
        try {
            reply = cl.scan(input);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Could not scan the input", e);
        }
        if (!ClamAVClient.isCleanReply(reply)) {
            throw new Exception("aaargh. Something was found");
        }else{
            System.out.println("All right!");
            clear = true;
        }

        return clear;
    }
}
