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
    String urlBtn, endpoint;

    public Data() {
        try {
            this.temp = conf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUrlBtn(){
        return urlBtn = temp.getProperty("urlBTN");
    }

    public String getCalculatePrice(){
        return endpoint = temp.getProperty("endpointCalculatePrice");
    }

    

}
