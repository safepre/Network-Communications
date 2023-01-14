package rosenthal;
public class MediumMessage extends Message {
	String messageLength;
	public MediumMessage(int arrivalTime) {
		super(arrivalTime);
		setMessageLength();
	}
	
	public String getMessageLength() {
		return this.messageLength;
	}
	
	public void setMessageLength() {
		this.messageLength = "Medium";
	}
	
	public String toString() {
		return (" " + getMessageLength() + "  " + getPriority() + " " + getMessageID() + " " + getWaitTime() + " ");
	}
}
