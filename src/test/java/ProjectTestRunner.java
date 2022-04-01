import org.testng.TestNG;

import java.util.List;

public class ProjectTestRunner {
    public static void main(String[] args) {

        TestNG runner = new TestNG();
        runner.setTestSuites(List.of("src/test/java/suites/main_suite.xml"));
        runner.run();
    }
}
