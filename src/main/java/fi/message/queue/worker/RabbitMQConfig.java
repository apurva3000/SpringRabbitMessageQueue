package fi.message.queue.worker;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
// This is a configuration class for RabbitMQ which is used to obtain beans in the worker class. 

	@Bean
	 public ConnectionFactory rabbitConnectionFactory() {
	     
	     final CachingConnectionFactory connFactory = new CachingConnectionFactory();
	     connFactory.setUsername("guest");
	     connFactory.setPassword("guest");
	     connFactory.setHost("localhost");
	     connFactory.setPort(5672);
	     connFactory.setVirtualHost("/");

	     return connFactory;
	 }
	
	
	//Although we don't need to obtain a bean for this , this is required in order to create the queue in the first place
	@Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(rabbitConnectionFactory());
    }
	
	 @Bean
	    public RabbitTemplate rabbitTemplate() {
	        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory());
	        template.setRoutingKey("messageQueue");
	        template.setQueue("messageQueue");
	        return template;
	    }

	 
	 @Bean
	    public Queue queue() {
	        return new Queue("messageQueue"); // name of the queue
	    }
}