package org.jboss.weld.tests.ejb.stateful.producer;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Stateful
@RequestScoped
public class SFBean2 {

    @Inject
    private Foo foo;

    public void foo() {

    }

}
