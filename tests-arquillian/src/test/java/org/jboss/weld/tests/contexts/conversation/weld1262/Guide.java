package org.jboss.weld.tests.contexts.conversation.weld1262;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;



@ConversationScoped
@Named
public class Guide implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@Inject
//	Instance<Conversation> conversation;
	
	public Guide(){
		
	}
	
	private String message = "Guide is not active";
	
	public String getMessage() {
//		System.out.println("******* TRANSIENT "+conversation.get().isTransient());
//		System.out.println("******* CID "+conversation.get().getId());
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
