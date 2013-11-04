package blatt1;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import blatt1.ClientEndpoint;

public class PollingPrimeClient {
	private final long number;
    
    public static void main(String[] args) {
		for ( long i = 1000000000000000000L; i < 1000000000000000010L; i++ ) {
		    new PollingPrimeClient(i).run();
		}
    }
    
    public PollingPrimeClient(long number) {
    	this.number = number;
    }
    
    public void run() {
		ClientEndpoint endpoint = new ClientEndpoint();
		SocketAddress server = new InetSocketAddress("localhost", 4711);
			
		endpoint.send(server, number);
		
		System.out.print("Die Zahl " + number + " ist ");
		
		Boolean isPrime;
		while((isPrime = endpoint.nonBlockingReceive()) == null){
			System.out.print('.');
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println((isPrime ? " eine " : " keine ") + "Primzahl");
    } 
}
