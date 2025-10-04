package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Запускает набор тестов в одном раннере.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BaseTest.class,
        ConstructorTest.class,
        LoginTest.class,
        LogoutTest.class,
        NavigationTest.class,
        RegistrationTest.class
})
public class AllureTestRunner {
    // Пустой класс — используется только для агрегации тестов
}