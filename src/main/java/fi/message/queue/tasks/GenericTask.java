package fi.message.queue.tasks;

import java.io.Serializable;

public abstract class GenericTask implements Serializable {
//Right now there is no delay, in the future there would be some delay mechanism which simulates a heavy task
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public GenericTask(String message)
	{
		this.message = message;
	}
	
	/** Method which serves as a mock for the actual operation on task 
	 * @throws InterruptedException **/
	public abstract void doTask() throws Exception;
	
	
}
