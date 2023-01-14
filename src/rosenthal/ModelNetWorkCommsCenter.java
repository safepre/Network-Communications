package rosenthal;
import java.util.Scanner;
import java.io.IOException;

public class ModelNetWorkCommsCenter {

	public static void main(String[] args) throws IOException{
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Please enter the name of the Network Comms Center: \n");
		String name = keyboard.nextLine();
		System.out.printf("Please enter a seed value as an int: \n");
		int seed = keyboard.nextInt();
		NetworkCommsCenter net = new NetworkCommsCenter(name,seed);
		System.out.printf("Please enter the number of Paths as an int: \n");
		int numOfPaths = keyboard.nextInt();
		net.initializeNetworkCommsCenter(numOfPaths);
		System.out.printf("Please enter the number of milliseconds to keep Network Comms Center operating for new messages: \n");
		int milliseconds = keyboard.nextInt();
		net.operateNetworkCommsCenter(milliseconds);
		System.out.printf("Please enter the name of the output file for Network Comms Center results: ");
		String output = keyboard.next();
		net.generateNetworkCommsCenterResults(output);
		keyboard.close();
	}

}
