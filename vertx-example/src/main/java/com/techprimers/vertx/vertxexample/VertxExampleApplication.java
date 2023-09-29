package com.techprimers.vertx.vertxexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

@SpringBootApplication
public class VertxExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(VertxExampleApplication.class, args);
		
		Vertx vertx = Vertx.vertx();
		
		HttpServer httpServer = vertx.createHttpServer();
		
		Router router = Router.router(vertx);
		//Router router = (Router) Router.router(vertx).route().method(HttpMethod.GET);
		
		//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
		
		Route hanlder1 = router
				.get("/hello/:name")
				//.get("/hello")
				//.route("/hello")
				.handler(routingContext -> {
					String name = routingContext.request().getParam("name");
					HttpServerResponse response = routingContext.response();
					response.setChunked(true);
					response.write("Hi " + name +"\n");
					response.end();
					//routingContext
					//		.vertx()
					//		.setTimer(5000, tid -> routingContext.next()); 
					//response.putHeader("content-type", "text/plain");
					//response.end("Hi TechPrimers");					
				});
		
		Route hanlder2 = router
				.post("/hello")
				.consumes("*/json")
				//.route("/hello")
				.handler(routingContext -> {
					HttpServerResponse response = routingContext.response();
					response.setChunked(true);
					response.write("Hi TechPrimers from post\n");
					response.end();
					//routingContext
					//		.vertx()
					//		.setTimer(5000, tid -> routingContext.next()); 
				});
		
		/*
		Route hanlder3 = router
				.route("/hello")
				.handler(routingContext -> {
					HttpServerResponse response = routingContext.response();
					response.write("Hi TechPrimers from hello3\n");	
					response.end("ended");
				});
				*/
		
		httpServer.requestHandler(router).listen(8091);
		//httpServer.listen(8091);
		//httpServer.requestHandler(router::accept).listen(8091);
				
	}

}
