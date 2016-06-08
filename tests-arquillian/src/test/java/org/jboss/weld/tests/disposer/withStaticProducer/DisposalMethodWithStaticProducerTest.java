/*
 * JBoss, Home of Professional Open Source
 * Copyright 2016, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.tests.disposer.withStaticProducer;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.weld.test.util.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class DisposalMethodWithStaticProducerTest {

    @Inject
    @StaticProducer
    Instance<Foo> fooInstance1;

    @Inject
    @StaticDisposer
    Instance<Foo> fooInstance2;

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap.create(BeanArchive.class, Utils.getDeploymentNameAsHash(DisposalMethodWithStaticProducerTest.class))
                .addPackage(DisposalMethodWithStaticProducerTest.class.getPackage());
    }

    @Test
    public void disposalMethodWithCorrespondingStaticProducer() {
        Foo foo = fooInstance1.get();
        foo.ping();
        fooInstance1.destroy(foo);
        Assert.assertTrue(FooProducer.disposed);
    }

    @Test
    public void staticDisposalMethodWithNonStaticProducer() {
        Foo foo = fooInstance2.get();
        foo.ping();
        fooInstance2.destroy(foo);
        Assert.assertTrue(FooProducer.disposedStatic);
    }
}
