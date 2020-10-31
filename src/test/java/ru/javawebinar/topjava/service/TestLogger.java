package ru.javawebinar.topjava.service;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.logging.Logger;

public class TestLogger implements TestRule {

    private final Logger logger = Logger.getLogger("");

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long startTime = System.currentTimeMillis();
                statement.evaluate();
                long endTime = System.currentTimeMillis();
                logger.info(String.format("Runtime test %d ms", endTime - startTime));
            }
        };
    }
}


