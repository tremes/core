package org.jboss.weld.examples.dices;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/dices")
public class GameEndpoint {

    @Inject
    PlayerGame playerGame;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Dice> getDices() {
        return playerGame.getDices();
    }


    @PUT
    @Path("/")
    public void hold(int index){
        playerGame.hold(index);
    }

    @POST
    public void start(){
         playerGame.start();
    }

}
