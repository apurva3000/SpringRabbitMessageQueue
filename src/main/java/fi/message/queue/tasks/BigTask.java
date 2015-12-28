package fi.message.queue.tasks;

import java.io.Serializable;

public class BigTask extends GenericTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BigTask(String message) {
		super(message);
	}


	//Right now there is no delay, in the future there would be some delay mechanism which simulates a heavy task
	public void doTask() throws InterruptedException
	{
		Thread.sleep(6000);
		System.out.println("Big task completed");
		
	}
	
	
}
