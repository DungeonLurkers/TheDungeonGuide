package tk.avabin.tdg.beans.services;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface SaltGeneratorService {
    String nextSaltAsString();
    byte[] nextSalt();
}
