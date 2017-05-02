package tk.avabin.tdg;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.Callable;

/**
 * Created by Avabin on 21.04.2017.
 */
public class FutureConnectionThread implements Callable {
    @Setter
    @Getter
    private String serverURL;
    @Setter
    @Getter
    private String urlParams;
    @Setter
    @Getter
    private int port;

    @Override
    public Object call() throws Exception {
        return null;
    }
}
