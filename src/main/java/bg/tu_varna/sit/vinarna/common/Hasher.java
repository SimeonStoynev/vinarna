package bg.tu_varna.sit.vinarna.common;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    public static class MD5 {
        private static final Logger log = Logger.getLogger(Hasher.MD5.class);
        public static String hash(String text) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(text.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                String hashText = no.toString(16);
                while (hashText.length() < 32) {
                    hashText = "0" + hashText;
                }
                return hashText;
            } catch (NoSuchAlgorithmException e) {
                log.error("MD5 Password hashing error: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
