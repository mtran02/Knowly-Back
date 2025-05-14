package com.knowly.rest_api.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.knowly.rest_api.bdd.steps", plugin = {
        "pretty",
        "html:target/cucumber-reports/Cucumber.html",
        "json:target/cucumber-reports/Cucumber.json",
        "junit:target/cucumber-reports/Cucumber.xml"
}, monochrome = true)
public class CucumberRunnerTest {
}