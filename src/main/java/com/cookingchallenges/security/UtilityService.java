package com.cookingchallenges.security;

import com.cookingchallenges.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UtilityService {

    public static String getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static User getLoggedUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
