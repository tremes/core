Dices Example
========================

This example demonstrates simple usage of CDI and Weld along with simple AngularJS UI.

Deploying to WildFly
--------------------

Make sure you have assigned the absolute path of your installation to the
`JBOSS_HOME` environment variable.

1. Open terminal and start the server by running 

        $JBOSS_HOME/bin/standalone.sh

2. Deploy example to the server you have started in step 1 using command

        mvn wildfly:deploy

3. Now you can view the application at <http://localhost:8080/weld-numberguess>.


To run the functional tests, execute:

    mvn verify -Darquillian=wildfly-managed-8

Deploying to GlassFish
----------------------

Firstly, verify that the `GLASSFISH_HOME` environment variable points to your
GlassFish installation.

Then, create a new domain for the application:

    mvn glassfish:create-domain

Finally, deploy the application using:

    mvn package glassfish:deploy

The application becomes available at <http://localhost:7070/weld-numberguess>


//TODO rewrite
Using Google App Engine
-----------------------

First, set up the Eclipse environment:

    mvn clean eclipse:clean eclipse:eclipse -Pgae

Make sure you have the Google App Engine Eclipse plugin installed.

Next, put all the needed resources into the `src/main/webapp`

    mvn war:inplace -Pgae

Now, in Eclipse, you can either run the app locally, or deploy it to Google App Engine.
