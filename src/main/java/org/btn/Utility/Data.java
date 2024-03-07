package org.btn.Utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


class confReader {
    static Properties temp;
    protected static Properties conf() throws IOException {
        Properties prop = new Properties();
        FileReader input = new FileReader("Config.properties");
        prop.load(input);
        temp = prop;
        input.close();
        return temp;
    }
}


public class Data extends confReader{
    String urlHrm, urlNewTab, urlrpa, travelioUrl, email, password;

    public Data() {
        try {
            this.temp = conf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrlHrm(){
        return urlHrm = temp.getProperty("urlHrm");
    }

    public String getUrlNewTab(){
        return urlNewTab = temp.getProperty("urlNewTab");
    }

    public String getUrlRPA(){
        return urlNewTab = temp.getProperty("urlRpa");
    }

    public String getUrlTravelio(){
        return travelioUrl = temp.getProperty("urlTravelio");
    }

    public String getEmailTravelio(){
        return email = temp.getProperty("emailTravelio");
    }

    public String getPassTravelio(){
        return password = temp.getProperty("passTravelio");
    }

}
