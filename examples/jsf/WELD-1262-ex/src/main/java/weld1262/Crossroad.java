package weld1262;

import java.io.IOException;

import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
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
//		NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
//		navHandler.handleNavigation(facesContext, null, "/road.jsf");
		return "road";
	}
	
	public void stopGuide(){
		conversation.end();
	}
	
}
