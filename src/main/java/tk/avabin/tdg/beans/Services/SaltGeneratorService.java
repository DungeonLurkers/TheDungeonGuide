package tk.avabin.tdg.beans.Services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Base64Processor;

import java.io.IOException;

/**
 * Created by Avabin on 15.05.2017.
 */
@Service
public class SaltGeneratorService {
    private final Base64Processor base64Processor;
    private BytesKeyGenerator bytesKeyGenerator;
    @Getter
    @Setter
    private int keyLength;

    @Autowired
    public SaltGeneratorService(Base64Processor base64Processor) {
        keyLength = 16;
        bytesKeyGenerator = KeyGenerators.secureRandom(keyLength);
        this.base64Processor = base64Processor;
    }

    public String nextSaltAsString() throws IOException {
        byte[] salt = bytesKeyGenerator.generateKey();
        return new String(salt, "US-ASCII");
    }

}
