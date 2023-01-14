package rosenthal;
public class Path {
	public static int pathIDCounter = 0;
	int pathIDNumber, totalMessagesProcessedByPath, timeRemainingForCurrentTransmission;
	Message assignedMessage;
	
	public Path() {
		setPathIDNumber();
	}
	
	public int getPathIDNumberForPath() {
		return this.pathIDNumber;
	}
	
	public int getTotalMessagesProcessedByPath() {
		return this.totalMessagesProcessedByPath;
	}
	
	public int getTimeRemainingForCurrentTransmission() {
		return this.timeRemainingForCurrentTransmission;
	}

	public Message getAssignedMessage()
	{
		return this.assignedMessage;
	}
	
	public int getPathIDNumber() {
		return this.pathIDNumber;
	}
	
	public void setPathIDNumber() {
		pathIDCounter++;
		pathIDNumber = pathIDCounter;
	}
	
	public void setAssignedMessage(Message message) {
		this.assignedMessage = message;
	}
	
	public void setTimeRemainingForCurrentTransmission(int time) {
		this.timeRemainingForCurrentTransmission = time;
	}
	
	public Message removeAssignedMessage() {
		Message tempMessage = assignedMessage;
		assignedMessage = null;
		totalMessagesProcessedByPath++;
		return tempMessage;
	}
	
	public void decrementTimeRemainingForCurrentTransmission() {
		timeRemainingForCurrentTransmission--;
	}
	
	public String toString() {
		return ("Path " + getPathIDNumberForPath() + " processed " + getTotalMessagesProcessedByPath() + " messages");
	}
}
