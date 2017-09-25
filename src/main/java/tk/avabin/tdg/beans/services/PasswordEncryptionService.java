package tk.avabin.tdg.beans.services;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

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
public class PasswordEncryptionService{

    public boolean authenticate(String attemptedPass, String b64EncryptedPass, String b64Salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] encryptedPass = Base64Utils.decodeFromString(b64EncryptedPass);
        byte[] salt = Base64Utils.decodeFromString(b64Salt);
        byte[] encryptedAttemptedPass = getEncryptedPass(attemptedPass, Base64Utils.encodeToString(salt));
        return Arrays.equals(encryptedAttemptedPass, encryptedPass);
    }

    @SuppressWarnings("WeakerAccess")
    public byte[] getEncryptedPass(String pass, String b64Salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = Base64Utils.decodeFromString(b64Salt);
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLen = 160;
        int iterations = 1000;

        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, iterations, derivedKeyLen);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);

        return keyFactory.generateSecret(spec).getEncoded();
    }

    public String getEncryptedPassAsB64String(String pass, String b64Salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return Base64Utils.encodeToString(getEncryptedPass(pass, b64Salt));
    }
}
