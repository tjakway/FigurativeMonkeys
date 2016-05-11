package com.jakway.randwords;

import java.util.Random;
import java.util.Set;

public class RandFunctions
{
    /** includes apostrophes */
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'";
    static Random rnd = new Random();

    /**
     * see http://stackoverflow.com/questions/11677670/when-exactly-are-we-supposed-to-use-public-static-final-string
     */
    public static String randomString( int len )
    {
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
