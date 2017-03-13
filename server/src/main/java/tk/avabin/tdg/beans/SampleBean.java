package tk.avabin.tdg.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

/**
 * Created by Avabin on 06.03.2017.
 */
@ToString
@Log
@Component
public class SampleBean {
    @Getter
    @Setter
    private String message;

    public void printMSG() {
        log.info("[" + this.getClass().getSimpleName() + "] : " + message);
    }
}
