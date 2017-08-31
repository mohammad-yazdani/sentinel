package com.sentinel.authservice.RSA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSA {

    private static Logger log = LoggerFactory.getLogger(RSA.class);

    private static RSA singleton = new RSA();

    private RSA() {}

    public boolean GenerateKeyPair () {
        try {

            KeyPair kp = this.makeKeys();
            byte[] publicKeyBytes = kp.getPublic().getEncoded();
            byte[] privateKeyBytes = kp.getPrivate().getEncoded();
            this.saveToFile(publicKeyBytes, privateKeyBytes);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private void saveToFile (byte[] publicKeyBytes, byte[] privateKeyBytes)
    throws IOException {
        try {

            String dirPath = "shared/";
            File dir = new File(dirPath);
            if (dir.mkdir()) log.info("Shared folder created.");

            String pubFileName = "publicKey";
            File pubFile = new File(dirPath + pubFileName);
            if (pubFile.delete()) log.info("Old public key deleted.");
            String privFileName = "privateKey";
            File privFile = new File(dirPath + privFileName);
            if (privFile.delete()) log.info("Old private key deleted.");

            FileOutputStream fosPub = new FileOutputStream(dirPath + pubFileName);
            FileOutputStream fosPriv = new FileOutputStream(dirPath + privFileName);

            fosPub.write(publicKeyBytes);
            fosPriv.write(privateKeyBytes);
            fosPub.close();
            fosPriv.close();
        }
        catch (IOException e) {
            log.error("Could not write RSA key pair to file.");
            log.error(e.getMessage());
            e.getStackTrace();
            throw e;
        }
    }

    private KeyPair makeKeys () throws NoSuchAlgorithmException {
        KeyPair kp;
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            kp = kpg.generateKeyPair();
        }
        catch (NoSuchAlgorithmException e) {
            log.error("The mentioned algorithm was not found to generate keypair.");
            log.error(e.getMessage());
            e.getStackTrace();
            throw e;
        }
        return kp;
    }

    public static RSA getInstance () {
        return singleton;
    }
}
