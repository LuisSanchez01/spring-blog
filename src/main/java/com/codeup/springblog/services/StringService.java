package com.codeup.springblog.services;

import org.springframework.beans.factory.annotation.Autowired;

public class StringService {

    @Autowired
    public StringService stringService;

    public String getWordInCaps(String word) {
        return word.toUpperCase();
    }

    public String limitLength(String string, int limit) {
        if(limit < string.length()) return string;

        return string.substring(0, limit);
    }
}
