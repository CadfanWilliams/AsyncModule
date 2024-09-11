import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getWebpageRunnable implements Runnable{

    @Override
    public void run() {
        Long threadStartedTime = System.currentTimeMillis();
        System.out.printf("Thread Started at %s%n", threadStartedTime);

        try {
            System.out.println(getWebpageContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Long threadFinishedTime = System.currentTimeMillis();
        System.out.printf("Thread Finished at %s%n", threadFinishedTime);
        System.out.printf("Thread took %s milliseconds%n", (threadFinishedTime - threadStartedTime));
    }

    private static String getWebpageContent() throws IOException {
        URL url = new URL("http://example.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }
}
