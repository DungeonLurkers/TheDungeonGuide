package tk.avabin.tdg.beans.Services;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface Base64SerializableProcessorService {
    Object fromString(String s) throws IOException, ClassNotFoundException;
    String objectToString(Serializable o) throws IOException;
}
