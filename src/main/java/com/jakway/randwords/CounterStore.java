package com.jakway.randwords;

import java.util.concurrent.atomic.AtomicLong;

/**
 * abstract away whatever system we're using to count
 * just a thread safe counter
 */
public class CounterStore
{
    private volatile AtomicLong validWordCount = new AtomicLong(0);

    public synchronized void add(long delta)
    {
        validWordCount.addAndGet(delta);
    }

    public synchronized long get()
    {
        return validWordCount.get();
    }
}
