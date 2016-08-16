package org.jboss.weld.tests.ejb.stateful.producer;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Stateful
@RequestScoped
public class SFBean3 {

    @Inject
    private SFBean1 bean1;

    @Inject
    private SFBean2 bean2;

    public void ping() {
        bean1.foo();
        bean2.foo();
    }
}
