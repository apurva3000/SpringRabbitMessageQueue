package fi.message.queue.worker;

import java.io.Serializable;

public class DelayTask implements Serializable {
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


	public DelayTask(String message)
	{
		this.message = message;
	}
	
	
}
