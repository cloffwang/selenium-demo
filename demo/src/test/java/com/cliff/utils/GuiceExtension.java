package com.cliff.utils;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class GuiceExtension implements
        TestInstancePostProcessor, ParameterResolver {
    private Injector injector;

    @Override
    public void postProcessTestInstance(
            Object testInstance,
            ExtensionContext context) {
        injector = Guice.createInjector(new DriverModule());
        injector.injectMembers(testInstance);
    }

    @Override
    public boolean supportsParameter(
            ParameterContext parameterContext,
            ExtensionContext extensionContext) {
        return parameterContext.getParameter().isAnnotationPresent(Inject.class);
    }

    @Override
    public Object resolveParameter(
            ParameterContext parameterContext,
            ExtensionContext extensionContext) {
        return injector.getInstance(parameterContext.getParameter().getType());
    }
}
