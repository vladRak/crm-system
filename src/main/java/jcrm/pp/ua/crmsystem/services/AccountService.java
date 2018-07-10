package jcrm.pp.ua.crmsystem.services;

import jcrm.pp.ua.crmsystem.entities.Imp.User;

import java.io.File;

public interface AccountService {
    void addAccount(User user, boolean demo);
    boolean importClients(File file) throws InterruptedException;
    boolean importClients(byte[] file) throws Exception;
}
