package ru.otus.suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import ru.otus.MainPageTest;

@Suite
@SelectClasses({
        MainPageTest.class
})
public class TestSuite {
}
