package org.jboss.weld.tests.extensions.configurators;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ObserverMethod;
import javax.enterprise.inject.spi.ProcessObserverMethod;
import java.util.concurrent.atomic.AtomicInteger;

public class ProcesObserverMethodExtension implements Extension {

    private ObserverMethod<Apple> appleObserverMethod;
    public static AtomicInteger consumerNotified = new AtomicInteger(0);
    public static AtomicInteger newAppleObserverNotified = new AtomicInteger(0);

    void observesApplePOM(@Observes ProcessObserverMethod<Apple, FruitObserver> event) {
        event.configureObserverMethod()
                .addQualifiers(Delicious.DeliciousLiteral.INSTANCE, Any.Literal.INSTANCE)
                .notifyWith((apple, eventMetadata) -> {
                    consumerNotified.incrementAndGet();
                });
        appleObserverMethod = event.getObserverMethod();

    }

    void observesABD(@Observes AfterBeanDiscovery abd) throws NoSuchMethodException {

        // read from ObserverMethod
        abd.<Apple>addObserverMethod().read(appleObserverMethod).beanClass(FruitObserver.class).observedType(Pear.class)
                .addQualifiers(Ripe.RipeLiteral.INSTANCE, Any.Literal.INSTANCE)
                .notifyWith((b) -> {
                    newAppleObserverNotified.incrementAndGet();
                });

    }
}
