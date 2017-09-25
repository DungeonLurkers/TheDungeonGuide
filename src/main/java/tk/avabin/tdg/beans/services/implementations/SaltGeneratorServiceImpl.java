package tk.avabin.tdg.beans.services.implementations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import tk.avabin.tdg.beans.services.SaltGeneratorService;

/**
 * Created by Avabin on 15.05.2017.
 */
@Service
public class SaltGeneratorServiceImpl implements SaltGeneratorService {
    private final BytesKeyGenerator bytesKeyGenerator;
    @Getter
    @Setter
    private int keyLength;

    @Autowired
    public SaltGeneratorServiceImpl(Base64SerializableProcessorServiceImpl base64SerializableProcessorService) {
        keyLength = 8;
        bytesKeyGenerator = KeyGenerators.secureRandom(keyLength);
    }

    public String nextSaltAsString() {
        byte[] salt = bytesKeyGenerator.generateKey();
        return Base64Utils.encodeToString(salt);
    }

    public byte[] nextSalt() {
        return bytesKeyGenerator.generateKey();
    }

}
