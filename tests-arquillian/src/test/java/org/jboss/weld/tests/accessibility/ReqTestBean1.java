package org.jboss.weld.tests.accessibility;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Tomas Remes
 */
@Named("req")
@ApplicationScoped
public class ReqTestBean1 {

    public String getMessage() {
        return message;
    }

    private String message;


    @PostConstruct
    public void init() throws NamingException {
        InitialContext ictx = new InitialContext();
        TestRemoteInterface ejbObj = (TestRemoteInterface) ictx.lookup("java:app/test-ejb3/TestEjb!org.jboss.weld.tests.accessibility.TestRemoteInterface");
        this.message = ejbObj.getMessage();
    }


}
