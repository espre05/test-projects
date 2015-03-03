package demo.order;

import javax.jws.WebParam;
import javax.jws.WebService;
//import javax.ws.rs.GET;

import org.codehaus.jra.Get;
import org.codehaus.jra.HttpResource;
import org.codehaus.jra.Post;
//import org.codehaus.jra.Get;

@WebService(targetNamespace="http://demo.order")
public interface OrderProcess {

	@Get
	@HttpResource(location = "/orders")
	public Orders getOrders();
	
	@Get
	@HttpResource(location = "/orders/{id}")
	public Order getOrder(@WebParam(name = "GetOrder") GetOrder getOrder );
	
	@Post
	@HttpResource(location = "/orders")
	public void addOrder(@WebParam(name = "Order") Order order);
}
