package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import support.DriverFactory;

public class TestHooks {

    @Before
    public void setUp() {
        System.out.println("Starting Chrome browser...");

        DriverFactory.createDriver();
    }

    @After
    public void tearDown() {
        System.out.println("Closing Chrome browser...");

        DriverFactory.quitDriver();
    }
}