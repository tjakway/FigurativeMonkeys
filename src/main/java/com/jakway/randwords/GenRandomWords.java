package com.jakway.randwords;


import java.util.Set;
import com.jakway.randwords.CounterStore;

//#1: read in all the words
//#2: find the longest word
//#3: the range for generated strings is (1, length(longest word))
//#4: generate a string
//#5: check if it's a word
//#6: log count
//#7: find percent of words generated in X number of 



class WordGenerator
{
    private static final int UPDATE_INTERVAL = 10000;

    private Set<String> words;
    private CounterStore counter;

    /**
     * @param words
     * @param counter
     */
    public WordGenerator(Set<String> words, CounterStore counter)
    {
        this.words = words;
        this.counter = counter;
    }
    
}


public class GenRandomWords
{

    public static void main(String[] args)
    {
        
    }
}
