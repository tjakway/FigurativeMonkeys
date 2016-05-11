package com.jakway.randwords;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class IO
{

    /**
     * see http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java
     */
    private static int countLines(File file) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }


    /**
     * read all words into a HashSet
     */
    public static final Set<String> readAllWords(File path) throws IOException
    {
        //initialize the hash set with size == number of words
        final int numWords = countLines(path);
        Set<String> hashSet = new HashSet<String>(numWords);

        try (BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                hashSet.add(line);
            }
        }

        return hashSet;
    }
}
