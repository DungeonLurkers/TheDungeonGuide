package tk.avabin.tdg;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Avabin on 03.04.2017.
 */
@Log
@NoArgsConstructor
public class ConnectionThread implements Runnable {
    @Setter
    private String serverURL;
    @Setter
    private String urlParams;
    @Setter
    private int port;

    public ConnectionThread(String serverURL, int port, String urlParams) {
        this.serverURL = serverURL;
        this.port = port;
        this.urlParams = urlParams;
    }

    private void connectAndGetResponse() {
        urlParams = urlParams.replaceAll("/", "%2F");
        urlParams = urlParams.replaceAll("\\+", "%2B");
        urlParams = urlParams.replaceAll(" ", "%20");

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(URI.create(serverURL + ":" + String.valueOf(port) + "/" + urlParams));
            log.info("Executing request " + httpGet.getRequestLine());
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
                log.info(httpResponse.getStatusLine().toString());

                log.info("Ping!");
                HttpEntity entity = httpResponse.getEntity();


                if (entity != null) {
                    try (InputStream inputStream = entity.getContent()) {
                        InputStreamReader reader = new InputStreamReader(inputStream);
                        int c = 0;
                        StringBuilder context = new StringBuilder();
                        while (c != -1) {
                            c = reader.read();
                            context.append((char) c);
                        }
                        context.append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        connectAndGetResponse();
    }
}
