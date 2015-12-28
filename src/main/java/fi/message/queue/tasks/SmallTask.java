package fi.message.queue.tasks;

import java.io.Serializable;

public class SmallTask extends GenericTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SmallTask(String message) {
		super(message);
	}


	//Right now there is no delay, in the future there would be some delay mechanism which simulates a heavy task
	public void doTask() throws InterruptedException
	{
		Thread.sleep(2000);
		System.out.println("Small task completed");
		
	}
	
	
}
