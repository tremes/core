package org.jboss.weld.examples.weld1262.ftest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.jboss.arquillian.graphene.Graphene.waitModel;
import static org.jboss.arquillian.graphene.Graphene.element;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
@RunAsClient
public class CrossroadTest {
	
    private static final String ARCHIVE_NAME = "WELD-1262-ex.war";
    private static final String BUILD_DIRECTORY = "target";
    
    protected By BEGIN = By.id("j_idt2:begin");
    protected By WITH_GUIDE = By.id("j_idt2:guide");
    protected By CID = By.id("j_idt2:cid");
    protected By MESS = By.id("message");

    
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(ZipImporter.class, ARCHIVE_NAME).importFrom(new File(BUILD_DIRECTORY + '/' + ARCHIVE_NAME))
        .as(WebArchive.class);
    }
	
	@Drone
    WebDriver driver;
    
    @ArquillianResource
    private URL contextPath;


    @Before
    public void openStartUrl() throws MalformedURLException {
        driver.navigate().to(new URL(contextPath.toString() + "crossroad.jsf"));
        waitModel(driver).until(element(BEGIN).isPresent());
    }
    
    @Test
    public void testConversationPropagation(){
    	driver.findElement(BEGIN).click();
    	assertTrue(Integer.valueOf(driver.findElement(CID).getText())>0);
    	driver.findElement(WITH_GUIDE).click();
    	assertTrue(driver.findElement(MESS).getText().equals("Guide is active"));
    }

    @After
    public void resetSession() {
        driver.manage().deleteAllCookies();
    }



}
