/*
 * JBoss, Home of Professional Open Source
 * Copyright 2017, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.tests.injectionPoint.enterprise;

import java.lang.reflect.Member;
import java.lang.reflect.Type;

import javax.ejb.Singleton;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

@Singleton
public class Baz {

    @Inject
    InjectionPoint injectionPoint;

    public boolean isIPAvailable() {
        return injectionPoint != null ;
    }

    public Bean<?> getInjectionPointMetadata() {
        return injectionPoint != null  ? injectionPoint.getBean() : null;
    }

    public Type getInjectionPointType() {
        return injectionPoint != null  ? injectionPoint.getType() : null;
    }

    public Member getInjectionPointMember() {
        return injectionPoint != null  ? injectionPoint.getMember() : null;
    }
}
