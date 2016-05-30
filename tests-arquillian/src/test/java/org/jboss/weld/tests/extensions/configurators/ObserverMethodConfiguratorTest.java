package org.jboss.weld.tests.extensions.configurators;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.weld.test.util.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;

/**
 * @author Tomas Remes
 */
@RunWith(Arquillian.class)
public class ObserverMethodConfiguratorTest {

    @Inject
    Event<Apple> appleEvent;

    @Inject
    Event<Pear> pearEvent;

    @Inject
    BeanManager bm;

    @Deployment
    public static Archive<?> deploy() {
        return ShrinkWrap.create(BeanArchive.class, Utils.getDeploymentNameAsHash(ObserverMethodConfiguratorTest.class))
                .addPackage(ObserverMethodConfiguratorTest.class.getPackage())
                .addClass(Utils.class)
                .addAsServiceProvider(Extension.class, ProcesObserverMethodExtension.class);
    }

    @Test
    public void test() {
        pearEvent.select(Any.Literal.INSTANCE, Ripe.RipeLiteral.INSTANCE).fire(new Pear());
        appleEvent.select(Any.Literal.INSTANCE, Delicious.DeliciousLiteral.INSTANCE).fire(new Apple());

        Assert.assertEquals(1, ProcesObserverMethodExtension.consumerNotified.get());
        Assert.assertEquals(1, ProcesObserverMethodExtension.newAppleObserverNotified.get());
    }

}
