package com.cookingchallenges.converter;

import com.cookingchallenges.domain.user.Grade;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Grade> {
    @Override
    public Grade convert(String source) {
        return Grade.valueOf(source.toUpperCase());
    }

}
