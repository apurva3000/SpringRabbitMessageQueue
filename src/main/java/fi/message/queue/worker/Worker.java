package fi.message.queue.worker;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Worker {


	// This is our worker node, the purpose is to obtain connection to the message queue and get the message
	// The message here is the object which we have sent via our web controller
	
	//You can start multiple instances of workers
	public static void main(String[] args){
		
	ApplicationContext rabbitMQConfig = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
	//Obtain the rabbit connection factory
	ConnectionFactory rabbitConnectionFactory = rabbitMQConfig.getBean(ConnectionFactory.class);
	// obtain the queue
	Queue rabbitQueue = rabbitMQConfig.getBean(Queue.class);
	final MessageConverter messageConverter = new SimpleMessageConverter();
	
	//Declare a container to listen to rabbitmq
	SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
	//Set the different parameters
	messageListenerContainer.setConnectionFactory(rabbitConnectionFactory);
	messageListenerContainer.setQueueNames(rabbitQueue.getName());

	
	messageListenerContainer.setMessageListener(new MessageListener() {
         public void onMessage(Message message) {
             final DelayTask dtask = (DelayTask) messageConverter.fromMessage(message);

             System.out.println("Message received from Message Queue " + dtask.getMessage());
         }
     });
	
	messageListenerContainer.start(); // Start listening to the Rabbit message queue
	}
}

