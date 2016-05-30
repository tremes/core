package org.jboss.weld.tests.extensions.configurators;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class FruitObserver {

    public void observes(@Observes Apple apple) {

    }
}
