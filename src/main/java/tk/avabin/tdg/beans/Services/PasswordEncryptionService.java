package tk.avabin.tdg.beans.Services;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * Created by Avabin on 15.05.2017.
 */
@Service
public class PasswordEncryptionService {

    public boolean authenticate(String attemptedPass, byte[] encryptedPass, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] encryptedAttemptedPass = getEncryptedPass(attemptedPass, salt);
        return Arrays.equals(encryptedAttemptedPass, encryptedPass);
    }

    public byte[] getEncryptedPass(String pass, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLen = 160;
        int iterations = 10000;

        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, iterations, derivedKeyLen);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);

        return keyFactory.generateSecret(spec).getEncoded();
    }
}
