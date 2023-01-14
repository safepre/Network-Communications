package rosenthal;
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
public class NetworkCommsCenter {
	Random randyX;
	PriorityQueue<Message> msgWaitingQ;
	ArrayList<Path> pathAvailableList = new ArrayList<>();
	ArrayList<Path> pathBusyList = new ArrayList<>();
	ArrayList<Message> processedMessages = new ArrayList<>();
	String commCenterName;
	int currentTime = 0;
	
	public NetworkCommsCenter(String name, int seed) {
		commCenterName = name;
		msgWaitingQ = new PriorityQueue<>(new MessagePriority());
		randyX = new Random(seed);		
	}
	public void initializeNetworkCommsCenter(int numPaths) {
		
		for(int i = 0; i < numPaths; i++)
		{
			pathAvailableList.add(new Path());
		}
		for (int i = 0; i < 15; i++) 
		{
			int randGen = randyX.nextInt(10) + 1;
			if (randGen < 3) {
			msgWaitingQ.add(new SmallMessage(currentTime));
			}
			else if (randGen < 6) {
			msgWaitingQ.add(new MediumMessage(currentTime));
			}
			else{
			msgWaitingQ.add(new LargeMessage(currentTime));
			}
		}
		currentTime = 1;
	}
	
	public void operateNetworkCommsCenter(int durationForArrivingMessages) {
		int endArrivalsTime = this.currentTime + durationForArrivingMessages;
		Message tempMessage;
		Path tempPath;
		int transmissionDuration;
		
		while(processedMessages.size() != Message.messageIDCounter || currentTime < endArrivalsTime) {
			if (currentTime < endArrivalsTime) {
				for (int i = 0; i < 3; i++) {
					int randGen = randyX.nextInt(10) + 1;
					if (randGen < 3) {
					msgWaitingQ.add(new SmallMessage(currentTime));
					}
					else if (randGen < 6) {
					msgWaitingQ.add(new MediumMessage(currentTime));
					}
					else{
					msgWaitingQ.add(new LargeMessage(currentTime));
					}
				}
			}
			for (int i = 0; i < pathBusyList.size(); i++) {
				tempPath = pathBusyList.get(i);
				tempPath.decrementTimeRemainingForCurrentTransmission();
				if(tempPath.getTimeRemainingForCurrentTransmission() == 0)
				{
					tempMessage = tempPath.getAssignedMessage();
					processedMessages.add(tempMessage);
					tempMessage.setTotalTime(currentTime);
					tempPath.removeAssignedMessage();
					pathBusyList.remove(i);
					pathAvailableList.add(tempPath);
					i--;
				}
			}
			while(!pathAvailableList.isEmpty() && !msgWaitingQ.isEmpty())
			{
				tempMessage = msgWaitingQ.remove();
				tempMessage.setStartTransmitTime(this.currentTime);
				tempPath = pathAvailableList.remove(0);
				pathBusyList.add(tempPath);
				tempPath.setAssignedMessage(tempMessage);
				tempMessage.setMessagePath(tempPath);
				if (tempMessage instanceof SmallMessage) {
					transmissionDuration = randyX.nextInt(3)+1;
				}
				else if (tempMessage instanceof MediumMessage) {
					transmissionDuration = randyX.nextInt(5)+3;
				}
				else{
					transmissionDuration = randyX.nextInt(5)+7;
				}
				tempMessage.setTransmissionDuration(transmissionDuration);
				tempPath.setTimeRemainingForCurrentTransmission(transmissionDuration);
			}
			currentTime++;
		}
	}
	//end big while loop
	public void generateNetworkCommsCenterResults(String outputFileName) throws IOException
	{
		File outputFile = new File(outputFileName);
		PrintWriter outputWriter = new PrintWriter(outputFile);
		outputWriter.printf("Data For NetWork Comms Center\n");
		outputWriter.printf("%s\n\n",this.commCenterName);
		for(int i = 0; i < pathAvailableList.size(); i++)
		{
			outputWriter.printf("%s\n", pathAvailableList.get(i));
		}
		int incrementSM =0; 
		int incrementMM =0;
		int incrementLM =0;
		int totalSM = 0;
		int totalMM = 0;
		int totalLM = 0;
		for(Message M: processedMessages)
		{
			if(M instanceof SmallMessage)
			{
				incrementSM++;
				totalSM = totalSM + M.getTotalTIme();
			}
			else if(M instanceof MediumMessage)
			{
				incrementMM++;
				totalMM = totalMM + M.getTotalTIme();
			}
			else
			{
				incrementLM++;
				totalLM = totalLM + M.getTotalTIme();
			}
		}
		double total = (double)(totalSM+totalMM+totalLM)/(processedMessages.size()); 
		outputWriter.printf("The average total time for %d Small Messages is %4.2f milliseconds\n",incrementSM,(double)totalSM/incrementSM);
		outputWriter.printf("The average total time for %d Medium Messages is %4.2f milliseconds\n",incrementMM,(double)totalMM/incrementMM);
		outputWriter.printf("The average total time for %d Large Messages is %4.2f milliseconds\n",incrementLM,(double)totalLM/incrementLM);
		outputWriter.printf("The average total time for %d Total Messages is %4.2f milliseconds\n",processedMessages.size(),total);
		outputWriter.printf("\n %13s %13s %12s %6s %15s %11s %15s %15s %16s\n", "MESSAGE ID", "PRIORITY", "  PATH ID", "LENGTH", "CREATION TIME", "WAIT TIME START","TRANSMIT TIME", "TRANSMISSION DURATION", "TOTAL TIME");
		Iterator<Message> iterator = processedMessages.iterator();
		Message temp = null;
		while(iterator.hasNext())
		{
			temp = iterator.next();
			outputWriter.printf("%13s %13s %12s %6s %15s %11s %15s %15s %20s\n", temp.getMessageID(),temp.getPriority(),temp.getPathIDNumber(),temp.getMessageLength(),temp.getMessageCreationTime(),temp.getWaitTime(),temp.getStartTransmitTime(),temp.getTransmissionDuration(),temp.getTotalTIme());
		}
		outputWriter.close();
	}
}
