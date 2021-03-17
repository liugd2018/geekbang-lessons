package org.geektimes.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.hibernate.annotations.common.util.impl.LoggerFactory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaSystemPropertiesConfigSource implements ConfigSource {

    protected Logger logger = Logger.getLogger(JavaSystemPropertiesConfigSource.class.getName());

    /**
     * Java 系统属性最好通过本地变量保存，使用 Map 保存，尽可能运行期不去调整
     * -Dapplication.name=user-web
     */
    private final Map<String, String> properties;

    public JavaSystemPropertiesConfigSource() {

        Map<String,String> propertiesMap = new HashMap(System.getProperties());

        properties = new HashMap<>(System.getenv());
        properties.putAll(propertiesMap);

        Properties pro = new Properties();
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("META-INF/application.properties");
            pro.load(in);
            properties.putAll(new HashMap(pro));
        } catch (IOException e) {
            System.out.println(e);
            logger.log(Level.FINE, e.getMessage(),e);
        }
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public String getName() {
        return "Java System Properties";
    }
}
