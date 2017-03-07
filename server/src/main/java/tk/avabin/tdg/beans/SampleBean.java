package tk.avabin.tdg.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

/**
 * Created by Avabin on 06.03.2017.
 */
@ToString
@Log
public class SampleBean {
    @Getter
    @Setter
    private String message;

    public void printMSG() {
        log.info("[" + this.getClass().getSimpleName() + "] : " + message);
    }
}
