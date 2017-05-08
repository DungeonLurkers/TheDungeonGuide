package tk.avabin.tdg.beans.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by avabin on 08.05.17.
 */
@Data
@Service
public class ApplicationPropertiesService {
    @Value("$(server.ssl.key-store-password:none)")
    private String SSLPassword;

    public ApplicationPropertiesService() {
        this.SSLPassword = System.getenv("SSL_PASS");
    }

}
