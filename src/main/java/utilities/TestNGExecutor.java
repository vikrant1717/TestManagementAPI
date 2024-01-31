package utilities;

import org.testng.TestNG;

import java.util.Arrays;
public class TestNGExecutor {

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestSuites(Arrays.asList("testng.xml"));
        testNG.run();
    }
}


