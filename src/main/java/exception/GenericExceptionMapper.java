package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author xboxm
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception>{
    
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(Exception exception) {
        JsonObject job = new JsonObject();
        if (exception instanceof NotFoundException ) {
            job.addProperty("status", 404);
            job.addProperty("msg", "The requested service does not exist");
            return Response.status(404).entity(gson.toJson(job)).build();
        } else {
            job.addProperty("status", 500);
            job.addProperty("msg", "Internal server error. We apologize for the inconvenience");
            return Response.status(500).entity(gson.toJson(job)).build();
        }
    }
    
}
