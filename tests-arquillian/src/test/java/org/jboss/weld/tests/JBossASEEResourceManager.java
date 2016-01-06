package org.jboss.weld.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.container.spi.event.StartContainer;
import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.arquillian.core.spi.EventContext;
import org.jboss.as.controller.client.ModelControllerClient;

public class JBossASEEResourceManager {

    private static final Logger logger = Logger.getLogger(JBossASEEResourceManager.class.getName());

    public void checkResources(@Observes EventContext<StartContainer> eventContext) {

        try {
            // First start the container
            eventContext.proceed();

            // Then check resources
            ModelControllerClient client = ModelControllerClient.Factory.create("localhost", 9999);
            JBossASMessaging messaging = new JBossASMessaging(client);
            if (messaging != null) {
                messaging.checkJmsQueue();
                messaging.checkJmsTopic();
            } else {
                /*
                 * JMS subsystem may not be installed (e.g. when debugging against standalone.xml) If this happens, do not attempt to install Queue/Topic as
                 * that always fails
                 */
                logger.log(Level.WARNING, "JMS subsystem not installed. Skipping test Queue/Topic installation.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
