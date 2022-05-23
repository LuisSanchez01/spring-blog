package com.codeup.springblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class StringService {
    @Lazy
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
