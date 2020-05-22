/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HELLOWORLD;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import java.net.HttpURLConnection;
import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
/**
 * REST Web Service
 *
 * @author ankit
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of HELLOWORLD.GenericResource
     *
     * 
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    
    public Response getText(@QueryParam("u") String u,@QueryParam("p") String p) 
    {
   
    Response rs=Response.status(Response.Status.NOT_IMPLEMENTED).build();
    Hashtable<String, String> environment = new Hashtable<String, String>();
    environment.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
    environment.put(javax.naming.Context.PROVIDER_URL,"ldap://localhost:10389");
    environment.put(javax.naming.Context.SECURITY_AUTHENTICATION, "simple");
    environment.put(javax.naming.Context.SECURITY_PRINCIPAL,"mail="+u+",ou=Employees,o=Nirmit");
    environment.put(javax.naming.Context.SECURITY_CREDENTIALS,p);

    try
    {
            DirContext authContext = new InitialDirContext(environment);
            rs=Response.status(Response.Status.ACCEPTED).build();  
            ResponseBuilder rb;
            NewCookie c=new NewCookie("sso","true");
         
            
            
            rs=Response.ok().cookie(new NewCookie(c)).build();
            
            // user is authenticated

    }
    catch (AuthenticationException ex)
    {

            rs=Response.status(Response.Status.UNAUTHORIZED).build();  

    }
    catch (NamingException ex)
    {
            ex.printStackTrace();
    }
      return rs;
      
  
   }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
