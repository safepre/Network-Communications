package rosenthal;
public class SmallMessage extends Message {
	private String messageLength;
	
	public SmallMessage(int arrivalTime) {
		super(arrivalTime);
		setMessageLength();
	}
	
	public String getMessageLength() {
		return this.messageLength;
	}
	
	public void setMessageLength() {
		this.messageLength = "Small";
	}
	
	public String toString() {
		return (" " + getMessageLength() + "  " + getPriority() + "  " + getMessageID() + " " + getWaitTime() + " ");
	}
}
