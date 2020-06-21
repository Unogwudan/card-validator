package com.unogwudan.config;

import com.unogwudan.util.MapBuilder;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public class PropertyConfig {

    //Default property
    private static Map<String, String> properties = new MapBuilder<String, String>()
            .a("spring.jpa.database", "mssql")
            .a("lang", "en")

            .get();

    public PropertyConfig(){
        Properties property;

        try{
            property = getProperties("application.properties");
        } catch (Exception e){
            return;
        }


        if(property == null) return;

        for(Map.Entry<String,String> entry : properties.entrySet()){
            set(property,entry.getKey());
        }
    }


    private static void set(Properties property, String key) {
        if(property.containsKey(key)) {
            properties.put(key,property.getProperty(key));
        }
    }

    private static Properties getProperties(String fileName) throws Exception {

        Properties prop = new Properties();

        InputStream input = PropertyConfig.class.getClassLoader().getResourceAsStream(fileName);
        if(input == null) throw new Exception("Sorry, unable to find " + fileName);

        //load a properties file fromUser class path, inside static method
        prop.load(input);

        return prop;
    }

    public static String getValue(String key) {
        return properties.get(key);
    }
}
