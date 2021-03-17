package org.geektimes.microprofile.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

import java.util.Objects;

/**
 * TODO
 *
 * @author <a href="mailto:liugd2020@gmail.com">liuguodong</a>
 * @since 1.0
 */
public class BooleanConverter implements Converter<Boolean> {


    @Override
    public Boolean convert(String value) throws IllegalArgumentException, NullPointerException {

        if (Objects.isNull(value)){
            throw new NullPointerException("不能为空");
        }
        try {
            return Boolean.valueOf(value);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("入参不能转换Boolean类型.");
        }
    }
}
