package org.news.client;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {

    public Logger log = LoggerFactory.getLogger(getClass());

    protected static boolean SKIP_REST = false;// exception one test break all test
    protected final static int RED = 31;
    protected final static int GREEN = 32;
    protected final static int YELLOW = 33;
    protected final static int BLUE = 34;

    @Before
    public final void baseTestBefore() {
        Assume.assumeTrue(!SKIP_REST);
        printColor("Start test: " + getClass().getName(),YELLOW);
    }

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void succeeded(long nanos, Description description){
            printColor(String.format("Success test method: %s took: %s\n ms ", description.getDisplayName(), nanos/1000/1000), GREEN);
        }
        @Override
        protected void failed(long nanos, Throwable e, Description description) {
            SKIP_REST = true;
            printColor(String.format("Fail!!! test method: %s took: %s ms", description.getDisplayName(),nanos/1000/1000), RED);
        }
    };

    protected void printColor(String text, int color){
        System.out.println((char)27 + "["+ color+"m" + text + (char)27 + "[0m");
    }
}