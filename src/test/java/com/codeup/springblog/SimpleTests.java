package com.codeup.springblog;

import com.codeup.springblog.services.StringService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleTests {

    private StringService stringService;

    @Before // before every test
    public void setUp() {
        this.stringService = new StringService();
    }

    @Test // sanity testing
    public void testServiceNotNull() {
        assertNotNull(stringService);
    }


    @Test
    public void testGetWordsInCaps() {

        // define a string with a word
        String word = "Hello!";

        String upperCasedWord = stringService.getWordInCaps(word);

        // set string to use string service class
        // to run get words in caps

        assertEquals(upperCasedWord, word.toUpperCase());
        // compare the two values, all letters should be caps
        // assert that the word is in all caps
    }


    @Test
    public void testLimitLength() {

        // should return the full string without the dots if string
        // length is less than the limit
        String words = "Hello I am a longer piece of text.";
        String limitedWords = stringService.limitLength(words, 5);

        // test case
        assertEquals(limitedWords, "Hello...");

        String longLimit = stringService.limitLength(words, 100);

        assertEquals(longLimit, words);



        // should return ... if the string is over the limit
    }





}