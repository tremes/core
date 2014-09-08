package org.jboss.weld.examples.dices;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/dices/throwing")
public class ThrowEndpoint {

    @Inject
    PlayerGame playerGame;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dice> getDices() {
        return playerGame.getDices();
    }

    @POST
    @Path("/")
    public void throwDices(){
        playerGame.throwDices();
    }

}
