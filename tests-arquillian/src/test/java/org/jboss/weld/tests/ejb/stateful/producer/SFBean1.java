package org.jboss.weld.tests.ejb.stateful.producer;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

@Stateful
@RequestScoped
public class SFBean1 {

    @Produces
    public Foo produceString() {
        return new Foo();
    }

    public void foo() {

    }
}
