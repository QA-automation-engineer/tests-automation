package utils; /**
 * Created by Vladimir Trandafilov on 13.12.2017.
 */

import java.io.*;
import java.util.Properties;

public class CredsHandler {

    private static Properties prop = new Properties();

    public static String getProperty(String name){
        try (InputStream input = new FileInputStream("creds.properties")){
            prop.load(input);
            return prop.getProperty(name);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}