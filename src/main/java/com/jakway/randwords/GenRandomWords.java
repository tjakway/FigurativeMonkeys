package com.jakway.randwords;


import java.io.File;
import java.io.IOException;
import java.util.Set;

import com.jakway.randwords.CounterStore;

//#1: read in all the words
//#2: find the longest word
//#3: the range for generated strings is (1, length(longest word))
//#4: generate a string
//#5: check if it's a word
//#6: log count
//#7: find percent of words generated in X number of 



class WordGenerator implements Runnable
{
    private static final int UPDATE_INTERVAL = 10000;
    public static int maxWordLength = 0;

    private Set<String> words;
    private CounterStore counter;
    private long numToGenerate;

    /**
     * @param words
     * @param counter
     */
    public WordGenerator(Set<String> words, CounterStore counter, long numToGenerate)
    {
        this.words = words;
        this.counter = counter;
        this.numToGenerate = numToGenerate;

        //since the set of words is immutable we only have to do this once
        if(maxWordLength != 0)
        {
            maxWordLength = RandFunctions.findLongestWordLength(words);
        }
    }

    
    @Override
    public void run()
    {
        //to reduce synchronization overhead only update the atomic counter every so often
        int localCount = 0;

        for(long i = 0; i < numToGenerate; i++)
        {
            //the actual work--generate a random string of the correct length and see if it's a word
            String randStr = RandFunctions.randomString(maxWordLength);
            if(words.contains(randStr))
            {
                localCount++;
            }

            //see if we need to update the count
            if(localCount >= UPDATE_INTERVAL)
            {
                counter.add(localCount);
                localCount = 0;
            }
        }

        //need to manually check at the end in case we have any left over
        if(localCount >= UPDATE_INTERVAL)
        {
            counter.add(localCount);
            localCount = 0;
        }
    }
}


public class GenRandomWords
{
    /** path to the English dictionary */
    private static final File WORDS_PATH = new File("/usr/share/dict/words");
    private static Thread[] threads;

    public static void main(String[] args) throws Exception
    {
        if(args.length != 1)
        {
            System.err.println("USAGE: [numToGenerate]");
            System.exit(1);
        }
        
        //will throw a NumberFormatException if the string is not numeric
        final long numToGenerate = Long.parseLong(args[0]);


        //read in the words set
        Set<String> allWords = IO.readAllWords(WORDS_PATH);
        CounterStore counter = new CounterStore();

        //run as many threads as available processors
        final int numProcessors = Runtime.getRuntime().availableProcessors();        

        //launch the threads
        threads = new Thread[numProcessors];
        for(int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread(new WordGenerator(allWords, counter, numToGenerate));
        }

        //wait for all threads to finish their work
        for(Thread thisThread : threads)
        {
            thisThread.join();
        }

        //print the results
        printResults(numToGenerate, counter);
    }

    private static void printResults(long numToGenerate, CounterStore counter)
    {
        final double percent = (counter.get() / numToGenerate) * 100;
        System.out.println("Out of "+numToGenerate+" generated strings, "+counter.get()+" were valid words ("+percent+"%).");
    }
}
