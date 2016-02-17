package org.jboss.weld.environment.se.test.resource;

public class GroovyClass {

    private static class Inner {
        def _ = [1, 2, 3].each {}
    }

}
