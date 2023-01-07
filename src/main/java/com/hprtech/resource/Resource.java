package com.hprtech.resource;

import com.hprtech.dto.Account;
import com.hprtech.service.AccountService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {

    public static final Logger LOG = Logger.getLogger(Resource.class);

    @Inject
    AccountService accountService;

    @POST
    @Path("open_account")
    public Response openAccount(@RequestBody Account account) {
        LOG.info("Entering in Response::openAccount()");
        LOG.debug("Response::openAccount() Account "+ account);

        boolean alreadyExist = accountService.isAccountAlreadyExist(account);
        LOG.debug("Response::openAccount() alreadyExist "+ alreadyExist);

        if (alreadyExist) {
            LOG.info("Returning from Resource::openAccount()");
            return Response.ok("Oops! Account already Exist").build();
        } else {
            LOG.info("Returning from Resource::openAccount()");
            return Response.ok("Thanks for opening account....").build();
        }
    }
}
