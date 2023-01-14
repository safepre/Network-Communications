package rosenthal;
import java.util.*;
public abstract class Message {
	public static Random randyMessage = new Random(5);
	public static int messageIDCounter = 0;
	private int priority, messageCreationTime, startTransmitTime, transmissionDuration, waitTime, totalTime;
	private String messageID;
	private Path messagePath;

	
	public Message(int messageCreationTime) {
		setPriority();
		setMessageID();
		setMessageCreationTime(messageCreationTime);
	}
	
	public int getPathIDNumber() {
		return this.messagePath.getPathIDNumber();
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public int getMessageCreationTime() {
		return this.messageCreationTime;
	}
	
	public int getStartTransmitTime() {
		return this.startTransmitTime;
	}
	
	public int getTransmissionDuration() {
		return this.transmissionDuration;
	}
	
	public int getWaitTime() {
		return this.waitTime;
	}
	
	public int getTotalTIme() {
		return this.totalTime;
	}
	
	public String getMessageID() {
		return this.messageID;
	}
	
	public Path getMessagePath() {
		return this.messagePath;
	}
	
	public void setPriority() {
		this.priority = randyMessage.nextInt(50) + 1;
	}
	
	public void setMessageID() {
		messageIDCounter++;
		this.messageID = "Message " + messageIDCounter;
	}
	
	public void setMessagePath(Path path) {
		messagePath = path;
	}
	
	public void setMessageCreationTime(int creation) {
		this.messageCreationTime = creation;
	}
	
	public void setStartTransmitTime(int time) {
		startTransmitTime = time;
		this.waitTime = startTransmitTime - messageCreationTime;
	}
	
	public void setPathIDNumber(Path messagePath) {
		this.messagePath = messagePath;
	}
	
	public void setTotalTime(int total) {
		this.totalTime = total - messageCreationTime;
	}
	
	public void setTransmissionDuration(int duration) {
		transmissionDuration = duration;
	}
	
	public abstract void setMessageLength();
	public abstract String getMessageLength();
	
}
