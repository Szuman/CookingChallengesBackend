package com.cookingchallenges.converter;

import com.cookingchallenges.domain.user.Rank;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, Rank> {
    @Override
    public Rank convert(String source) {
        return Rank.valueOf(source.toUpperCase());
    }

}
