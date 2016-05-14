package com.jakway.randwords;

import java.util.Random;
import java.util.Set;

public class RandFunctions
{
    /** includes apostrophes */
    static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'";
    private Random rnd = new Random();

    /**
     * (inclusive)
     */
    private int getRandInRange(int min, int max)
    {
        //see http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range
        //add 1 to make it inclusive
        return min + rnd.nextInt(max - min + 1);
    }

    public RandFunctions()
    {
    	
    }
    /**
     * see http://stackoverflow.com/questions/11677670/when-exactly-are-we-supposed-to-use-public-static-final-string
     */
    public String randomString( int maxLen )
    {
        //the length of the generated string is randomly generated
        final int minLength = 1; // no zero length strings
        final int len = getRandInRange(minLength, maxLen);

        StringBuilder sb = new StringBuilder( len );

        //add the first character manually to ensure the string is at least 1 character long
        sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        //loop through the remaining characters
        for( int i = 0; i < (len - 1); i++ ) 
        {
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        }
        return sb.toString();
    }


    /**
     * exhaustively search the set to find the longest word length
     */
    public static int findLongestWordLength(Set<String> words)
    {
        int length = 0;
        for(String thisWord : words)
        {
            if(thisWord.length() > length)
            {
                length = thisWord.length();
            }
        }

        return length;
    }
}
