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
package org.jboss.weld.tests.interceptors.mdb;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.weld.tests.Timer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MessageDrivenBeanInterceptorInvocationTest {

    @Inject
    SimpleMessageProducer producer;

    @Deployment
    public static Archive<?> deploy() {
        return ShrinkWrap.create(BeanArchive.class)
                .intercept(MissileInterceptor.class)
                .addClass(Timer.class)
                .addPackage(MessageDrivenBeanInterceptorInvocationTest.class.getPackage());
    }

    @Test
    public void testMessageDrivenBeanMethodIntercepted() throws Exception {

        MissileInterceptor.reset();
        producer.sendTopicMessage();

        // Wait for async processing
        new Timer().setDelay(5, TimeUnit.SECONDS).addStopCondition(new Timer.StopCondition() {
            @Override
            public boolean isSatisfied() {
                return MessageDrivenMissile.messageAccepted;
            }
        }).start();

        Assert.assertTrue("MDB didn't receive any message!", MessageDrivenMissile.messageAccepted);
        Assert.assertTrue("Interceptor bound to MDB was not called!", MissileInterceptor.methodIntercepted);
    }

}
