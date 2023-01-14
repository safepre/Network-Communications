package rosenthal;
import java.util.Comparator;

public class MessagePriority implements Comparator<Message> {
	public int compare(Message a, Message b) {
		if(a.getClass().equals( b.getClass() )) {
			
			if (a.getPriority() - b.getPriority() == 0 ) {
				
				return a.getMessageCreationTime() - b.getMessageCreationTime();
			}
			else
				return a.getPriority() - b.getPriority();
		}
		else 
			return b.getMessageLength().compareTo(a.getMessageLength());
	}
}
