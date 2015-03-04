/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.tests.accessibility;

import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.BeanArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@RunAsClient
public class EarAccessibilityTest {

    @Deployment(testable = false)
    public static Archive<?> getDeployment() {
        // setup shared libraries
        // JavaArchive sharedInterfaceBundle = create(JavaArchive.class).addClasses(Animal.class, AbstractTestListener.class);
        // JavaArchive sharedExtensionLibrary1 = createSimpleExtensionArchive(SharedLibrary1Extension.class,
        //          SharedLibrary1Impl.class);
        // JavaArchive sharedExtensionLibrary2 = createSimpleExtensionArchive(SharedLibrary2Extension.class,
        //        SharedLibrary2Impl.class);
        // setup war 1
        //JavaArchive library1 = createSimpleExtensionArchive(Library1Extension.class, Library1Impl.class);
        WebArchive war1 = create(WebArchive.class, "application.war").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebResource(EarAccessibilityTest.class.getPackage(), "index.html", "index.html")
                .addAsWebResource(EarAccessibilityTest.class.getPackage(), "home.xhtml", "home.xhtml")
                .addAsWebInfResource(EarAccessibilityTest.class.getPackage(), "web.xml", "web.xml")
                .addAsWebInfResource(EarAccessibilityTest.class.getPackage(), "faces-config.xml", "faces-config.xml").addClasses(
                        ReqTestBean1.class, TestServlet.class).setManifest(new StringAsset("Manifest-Version: 1.0\nClass-Path: test-ejb3.jar\n"));
        // setup war 2
        //  JavaArchive library2 = createSimpleExtensionArchive(Library2Extension.class, Library2Impl.class);
        WebArchive war2 = create(WebArchive.class, "application2.war").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addClasses(
                ReqTestBean2.class, TestServlet.class).setManifest(new StringAsset("Manifest-Version: 1.0\nClass-Path: test-ejb3.jar\n"));


        // setup the entire application
        JavaArchive myEjbModule = createSimpleExtensionArchive(TestEjb.class, TestRemoteInterface.class, TestLocalInterface.class);
        EnterpriseArchive ear = create(EnterpriseArchive.class).addAsModules(war1, war2)
                //.addAsLibraries(sharedInterfaceBundle, sharedExtensionLibrary1, sharedExtensionLibrary2)
                .addAsManifestResource(EarAccessibilityTest.class.getPackage(), "application.xml", "application.xml")
                .addAsModule(myEjbModule);
        return ear;
    }

    protected static JavaArchive createSimpleExtensionArchive(Class<?>... classes) {
        return create(BeanArchive.class, "test-ejb3.jar").addClasses(classes);
    }

    @Test
    public void test() {
        // noop
        // the actual validation is done within War1Listener and War2Listener
    }
}
