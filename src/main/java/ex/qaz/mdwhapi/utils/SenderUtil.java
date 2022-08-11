package ex.qaz.mdwhapi.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SenderUtil {
    public static boolean sendWebhookByJSON(String json, String urlAdress) {
        try {
            json = json.replaceAll("  ","").replaceAll("\n","");
            URL url = new URL(urlAdress);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream stream = connection.getOutputStream();
            stream.write(json.getBytes());
            stream.flush();
            stream.close();

            connection.getInputStream().close(); //I'm not sure why, but it doesn't work without getting the InputStream
            connection.disconnect();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean sendWebhookByJSONWithPlaceholders(String json, String urlAdress, Map placeholders) {
        try {
            json = json.replaceAll("  ","").replaceAll("\n","");
            //TODO placeholders - HashMap With placeholders [placeholder:usage] prim. [%playername%:qazwseer2]
            URL url = new URL(urlAdress);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream stream = connection.getOutputStream();
            stream.write(json.getBytes());
            stream.flush();
            stream.close();

            connection.getInputStream().close(); //I'm not sure why, but it doesn't work without getting the InputStream
            connection.disconnect();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean sendWebhookByDiscordWebhookObject(DiscordWebhook wh) {
        try {
            wh.execute();
            return true;
        } catch (IOException e){
            return false;
        }
    }
}
