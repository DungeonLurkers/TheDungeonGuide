package tk.avabin.tdg.beans;

import org.springframework.util.Base64Utils;

import java.io.*;
import java.util.Base64;

/**
 * Created by Avabin on 10.04.2017.
 */
public class Base64SerializableProcessor {
    public Object fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64Utils.decodeFromString(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    public String objectToString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64Utils.encodeToString(baos.toByteArray());
    }
}
