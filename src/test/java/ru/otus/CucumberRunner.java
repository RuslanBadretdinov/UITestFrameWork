package ru.otus;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectPackages("ru.otus")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ru.otus")
public class CucumberRunner {
}
