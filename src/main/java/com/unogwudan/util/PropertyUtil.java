package com.unogwudan.util;

import com.unogwudan.config.PropertyConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Daniel Unogwu on 21/06/20.
 */
public class PropertyUtil {

    private static Properties getProperties(String fileName){
        InputStream input = null;
        Properties prop = new Properties();
        try {
            input = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
            if(input==null){
                return null;
            }
            //load a properties file fromUser class path
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    public static String msg(String key, Map<String, String> replaceWith){
        return StringUtil.doStringReplacements(msg(key), replaceWith);
    }

    public static String msg(String key){
        return msg(PropertyConfig.getValue("lang"), key);
    }

    private static String msg(String lang, String key){
        Properties property;
        String value;

        property = PropertyUtil.getProperties("entity_" + lang  + ".properties");
        if(property != null){
            value = property.getProperty(key);
            if(value != null){
                return value;
            }
        }

        property = PropertyUtil.getProperties("errors_" + lang  + ".properties");
        if(property != null){
            value = property.getProperty(key);
            if(value != null){
                return value;
            }
        }

        property = PropertyUtil.getProperties("settings_" + lang + ".properties");
        if(property != null){
            value = property.getProperty(key);
            if(value != null){
                return value;
            }
        }

        property = PropertyUtil.getProperties("messages_" + lang + ".properties");
        if(property != null){
            value = property.getProperty(key);
            if(value != null){
                return value;
            }
        }

        property = PropertyUtil.getProperties("validation_" + lang + ".properties");
        if(property != null){
            value = property.getProperty(key);
            if(value != null){
                return value;
            }
        }

        return "Message Not Found {" + key + "}";
    }

}
