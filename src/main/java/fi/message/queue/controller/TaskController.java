package fi.message.queue.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.message.queue.worker.DelayTask;
import fi.message.queue.worker.RabbitMQConfig;


@Controller
@RequestMapping("/")
public class TaskController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String testTask(@RequestParam("message")String message) {
		
		DelayTask dtask = new DelayTask(message);
		ApplicationContext rabbitMQConfig = new AnnotationConfigApplicationContext(RabbitMQConfig.class);
        AmqpTemplate amqpTemplate = rabbitMQConfig.getBean(AmqpTemplate.class);
        amqpTemplate.convertAndSend(dtask);
		
		return "Message: " + message + " is sent to workers ";
	}



}
