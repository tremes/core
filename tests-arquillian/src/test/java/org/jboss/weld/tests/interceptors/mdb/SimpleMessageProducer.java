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

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@RequestScoped
public class SimpleMessageProducer {

    @Resource(lookup = "java:/ConnectionFactory")
    private static ConnectionFactory connectionFactory;


    private Topic topic;

    @PostConstruct
    public void init() {
        try {
            this.topic = (Topic) new InitialContext().lookup("java:/topic/test");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void sendTopicMessage() {

        Connection connection = null;
        Session session = null;
        MessageProducer messageProducer = null;
        TextMessage message = null;

        try {
            connection = connectionFactory.createConnection();
         //   connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            messageProducer = session.createProducer(topic);
            message = session.createTextMessage();
            message.setText(SimpleMessageProducer.class.getName());
            messageProducer.send(message);

        } catch (JMSException e) {
            throw new RuntimeException("Cannot send message", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    System.out.println(e);
                }
            }
        }
    }

}
