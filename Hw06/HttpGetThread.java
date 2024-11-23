//2022427833 니스타

package callbackInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpGetThread extends Thread {
    private String urlString;
    private HttpGetCallbackInterface callback;
    private volatile boolean running = true;
    private BufferedReader reader;

    public HttpGetThread(String url) {
        this.urlString = url;
    }

    public void setCallback(HttpGetCallbackInterface callback) {
        this.callback = callback;
    }

    public void stopRunning() {
        this.running = false;
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readNextLine() throws Exception {
        if (reader != null) {
            return reader.readLine();
        }
        return null;
    }

    public void run() {
        String line = "";
        try {
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            int responseCode = httpConn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader inReader = new InputStreamReader(httpConn.getInputStream(), "UTF-8");
                reader = new BufferedReader(inReader);
                while (running && (line = reader.readLine()) != null) {
                    callback.updateProgress(line);
                    Thread.sleep(10);
                }
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = dateFormat.format(new Date());
                callback.updateDate(dateString);

                httpConn.disconnect();
                inReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    if (running) {
                        e.printStackTrace();
                    }else {
                        System.out.println("Reader closed.");
                    }
                }
            }
        }
    }
}
