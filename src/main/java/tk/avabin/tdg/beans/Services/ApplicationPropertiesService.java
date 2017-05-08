package tk.avabin.tdg.beans.Services;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by avabin on 08.05.17.
 */
@Data
@Log
@Service
public class ApplicationPropertiesService {
    @Value("$(server.ssl.key-store-password:none)")
    private String SSLPassword;

    public ApplicationPropertiesService() {
        log.info(SSLPassword);
        this.SSLPassword = System.getenv("SSL_PASSWORD");
        log.info(SSLPassword);
        log.info(System.getenv("SSL_PASSWORD"));
    }

}
