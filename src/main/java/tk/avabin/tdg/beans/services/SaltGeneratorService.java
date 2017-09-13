package tk.avabin.tdg.beans.services;

import java.io.IOException;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface SaltGeneratorService {
    String nextSaltAsString() throws IOException;
    byte[] nextSalt();
}
