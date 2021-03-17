package org.geektimes.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.ServiceLoader;

/**
 * TODO
 *
 * @author <a href="mailto:liugd2020@gmail.com">liuguodong</a>
 * @since 1.0
 */
public class TestConfig {

    public static void main(String[] args) {

        ServiceLoader<Config> serviceLoader = ServiceLoader.load(Config.class);

        JavaConfig config = (JavaConfig)serviceLoader.iterator().next();
        String name = config.getPropertyValue("application.name");
        System.out.println( "application.name = " + name);

        Integer value = config.getValue("sun.arch.data.model", int.class);
        System.out.println("sun.arch.data.model = " + value);

        String applicationName = config.getValue("application.name", String.class);
        System.out.println("application.name  = " + applicationName);

        config.getPropertyNames().forEach(System.out::println);

    }
}
