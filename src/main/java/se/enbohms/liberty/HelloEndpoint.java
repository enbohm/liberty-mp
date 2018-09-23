package se.enbohms.liberty;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.metrics.annotation.Counted;

/**
 * Represent a simple endpoint which just returns a JSON structure taking the
 * path parameter as input (e.g. http://.../helidon/hello/john -> {"Hello " :
 * "John"})
 * <p>
 * This endpoint includes counting of the total number of request (MP
 * Metrics) and a circuit breaker (MP Fault Tolerance). To emulate runtime
 * failures and to demonstrate the circuit breaker, exceptions are randomly
 * thrown in the method below.
 * <p>
 * The metrics can be viewed on {@link http://localhost:8080/metrics} and
 * {@link http://localhost:8080/metrics/application}
 *
 * @author enbohm
 *
 */
@ApplicationScoped
@Path("/")
public class HelloEndpoint {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/hello/{name}")
	@Counted(monotonic = true)
	@CircuitBreaker(failureRatio=0.5, requestVolumeThreshold=2)
	public Response sayHi(@PathParam("name") String name) {
		if (Math.random() > 0.75) //emulate 25% failures
			throw new RuntimeException("An exception occured - this is to trigger the circuit breaker!");
		return Response.ok(Json.createObjectBuilder().add("Hello ", name).build()).build();
	}
}