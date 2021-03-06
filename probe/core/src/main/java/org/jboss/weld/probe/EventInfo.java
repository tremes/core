/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.probe;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.ObserverMethod;

/**
 *
 * @author Jozef Hartinger
 */
class EventInfo {

    private final boolean containerEvent;

    private final Type type;

    private final Set<Annotation> qualifiers;

    private final String eventString;

    private final InjectionPoint injectionPoint;

    private final List<ObserverMethod<?>> observers;

    private final long timestamp;

    /**
     *
     * @param type
     * @param qualifiers
     * @param event
     * @param injectionPoint
     * @param observers
     * @param containerEvent
     * @param timestamp
     */
    EventInfo(Type type, Set<Annotation> qualifiers, Object event, InjectionPoint injectionPoint, List<ObserverMethod<?>> observers, boolean containerEvent,
            long timestamp) {
        this.type = type;
        this.qualifiers = qualifiers;
        this.injectionPoint = injectionPoint;
        this.containerEvent = containerEvent;
        this.eventString = event.toString();
        this.observers = observers;
        this.timestamp = timestamp;
    }

    boolean isContainerEvent() {
        return containerEvent;
    }

    Type getType() {
        return type;
    }

    Set<Annotation> getQualifiers() {
        return qualifiers;
    }

    String getEventString() {
        return eventString;
    }

    InjectionPoint getInjectionPoint() {
        return injectionPoint;
    }

    List<ObserverMethod<?>> getObservers() {
        return observers;
    }

    long getTimestamp() {
        return timestamp;
    }

}