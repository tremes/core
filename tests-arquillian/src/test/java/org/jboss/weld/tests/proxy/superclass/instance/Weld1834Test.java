package org.jboss.weld.tests.proxy.superclass.instance;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Yann Diorcet
 * @see https://issues.jboss.org/browse/WELD-1834
 */
@RunWith(Arquillian.class)
public class Weld1834Test {

    @Inject
    private Instance<Foo> instance;

    @Deployment
    public static Archive<?> deploy() {
        return ShrinkWrap.create(BeanArchive.class)
                .addPackage(Weld1834Test.class.getPackage());
    }

    @Test
    public void testDeployment() {
        for(Foo foo: instance) {
            foo.getName();
        }
    }

}
