package rosenthal;
public class LargeMessage extends Message {
	private String messageLength;
	public LargeMessage(int arrivalTime) {
		super(arrivalTime);
		setMessageLength();
	}
	
	public String getMessageLength() {
		return this.messageLength;
	}
	
	public void setMessageLength() {
		this.messageLength = "Large";
	}
	
	public String toString() {
		return (" " + getMessageLength() + "  " + getPriority() + " " + getMessageID() + " " + getWaitTime() + " ");
	}
}
