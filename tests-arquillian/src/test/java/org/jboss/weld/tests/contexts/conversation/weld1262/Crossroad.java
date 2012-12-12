package org.jboss.weld.tests.contexts.conversation.weld1262;

import java.io.IOException;

import javax.enterprise.context.Conversation;
import javax.enterprise.inject.Model;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
public class Crossroad {
	
	
	@Inject
	Conversation conversation;
	
	@Inject
	Guide guide;
	
	public Guide getGuide() {
		return guide;
	}

	public void startGuide(){
		conversation.begin();
		guide.setMessage("Guide is active");
	}
	
	public void loosingTheGuide() throws IOException{
		String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath+"/road.jsf");
		
	}
	
	public String goingWithGuide(){
//		FacesContext facesContext =  FacesContext.getCurrentInstance();
//		System.out.println("**************** CID in redirect "+conversation.getId());
//		System.out.println("**************** transient "+conversation.isTransient());
//		System.out.println("**************** MESSAGE "+guide.getMessage());
//		NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
//		navHandler.handleNavigation(facesContext, null, "/road.jsf");
		return "road?faces-redirect=true";
	}
	
	public void stopGuide(){
		conversation.end();
	}
	
}
