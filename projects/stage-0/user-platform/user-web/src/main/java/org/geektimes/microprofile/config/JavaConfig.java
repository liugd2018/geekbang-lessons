package org.geektimes.microprofile.config;


import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;
import org.geektimes.microprofile.config.converter.BooleanConverter;
import org.geektimes.microprofile.config.converter.IntegerConverter;
import org.geektimes.microprofile.config.converter.StringConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaConfig implements Config {

    Logger logger = Logger.getLogger(JavaConfig.class.getName());

    /**
     * 内部可变的集合，不要直接暴露在外面
     */
    private List<ConfigSource> configSources = new LinkedList<>();

    private Map<String, Converter> converterMap = new HashMap<>();

    private static Comparator<ConfigSource> configSourceComparator = new Comparator<ConfigSource>() {
        @Override
        public int compare(ConfigSource o1, ConfigSource o2) {
            return Integer.compare(o2.getOrdinal(), o1.getOrdinal());
        }
    };

    public JavaConfig() {
        ClassLoader classLoader = getClass().getClassLoader();
        ServiceLoader<ConfigSource> serviceLoader = ServiceLoader.load(ConfigSource.class, classLoader);
        serviceLoader.forEach(configSources::add);
        // 排序
        configSources.sort(configSourceComparator);

        ServiceLoader<Converter> serviceLoaderConverter = ServiceLoader.load(Converter.class, classLoader);

        serviceLoaderConverter.forEach(converter -> {
            try {
                Converter newInstance = converter.getClass().newInstance();

                if (newInstance instanceof BooleanConverter){
                    converterMap.put(Boolean.class.getName(), converter);
                    converterMap.put(boolean.class.getName(), converter);
                } else if (newInstance instanceof IntegerConverter){
                    converterMap.put(Integer.class.getName(), converter);
                    converterMap.put(int.class.getName(), converter);
                } else if (newInstance instanceof StringConverter){
                    converterMap.put(String.class.getName(), converter);
                }

            }catch (IllegalAccessException | InstantiationException e) {
                logger.log(Level.FINE, e.getMessage(), e);
                System.out.println(e);
            }

        });


    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {
        String propertyValue = getPropertyValue(propertyName);
        if (converterMap.containsKey(propertyType.getName())){
            return (T) converterMap.get(propertyType.getName()).convert(propertyValue);
        }else {
            throw new RuntimeException("不支持此类型.");
        }


    }

    @Override
    public ConfigValue getConfigValue(String propertyName) {
        return null;
    }

    protected String getPropertyValue(String propertyName) {
        String propertyValue = null;
        for (ConfigSource configSource : configSources) {
            propertyValue = configSource.getValue(propertyName);
            if (propertyValue != null) {
                break;
            }
        }
        return propertyValue;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        T value = getValue(propertyName, propertyType);
        return Optional.ofNullable(value);
    }

    @Override
    public Iterable<String> getPropertyNames() {
        List<String> collect = configSources.stream()
                .filter(Objects::nonNull)
                .flatMap(configSource -> configSource.getProperties().keySet().stream())
                .collect(Collectors.toList());
        return Collections.unmodifiableList(collect);
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return Collections.unmodifiableList(configSources);
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> forType) {

        if (converterMap.containsKey(forType.getName())){
            return Optional.ofNullable((Converter<T>)converterMap.get(forType.getName()));
        }else {
            return Optional.empty();
        }

    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }
}
