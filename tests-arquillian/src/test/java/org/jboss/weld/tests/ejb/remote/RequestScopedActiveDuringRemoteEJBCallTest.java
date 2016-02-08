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
package org.jboss.weld.tests.ejb.remote;

import javax.naming.NamingException;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.weld.test.util.Utils;
import org.jboss.weld.tests.category.Integration;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

/**
 * @author Tomas Remes
 */
@Category(Integration.class)
@RunWith(Arquillian.class)
public class RequestScopedActiveDuringRemoteEJBCallTest {

    private final static String SERVER = "server";
    private final static String CLIENT = "client";

    @Deployment(name = SERVER, order = 0, testable = false)
    public static Archive<?> deployServer() {
        return ShrinkWrap.create(BeanArchive.class, Utils.getDeploymentNameAsHash(RequestScopedActiveDuringRemoteEJBCallTest.class, Utils.ARCHIVE_TYPE.JAR))
                .addClasses(CDITestBean.class, CDITestRemote.class, TransactionHandler.class, VolumeEvent.class, VolumeEventObserver.class,
                        Utils.class);
    }

    @Deployment(name = CLIENT, order = 1)
    public static Archive<?> deployClient() {
        return ShrinkWrap.create(BeanArchive.class)
                .addClasses(RemoteEJBClient.class, Utils.class, CDITestRemote.class);
    }

    @Test
    @OperateOnDeployment(CLIENT)
    public void test() throws NamingException {
        int transactionalObserverCalls = RemoteEJBClient.invokeStatelessBean();
        // expecting 2 calls because requestScoped instance of TransactionHandler should be reused in VolumeEventObserver
        Assert.assertEquals(2, transactionalObserverCalls);

    }
}
