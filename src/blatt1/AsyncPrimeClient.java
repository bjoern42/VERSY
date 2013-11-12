package blatt1;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import blatt1.ClientEndpoint;

public class AsyncPrimeClient{
	private final long number;
    private Boolean isPrime = null;
    
    public static void main(String[] args) {
		for ( long i = 1000000000000000000L; i < 1000000000000000010L; i++ ) {
		    new AsyncPrimeClient(i).run();
		}
    }
    
    public AsyncPrimeClient(long number) {
    	this.number = number;
    }
    
    public void run() {
		final ClientEndpoint endpoint = new ClientEndpoint();
		SocketAddress server = new InetSocketAddress("localhost", 4711);
			
		endpoint.send(server, number);
		System.out.print("Die Zahl " + number + " ist ");
		
		new Thread(){
			public void run(){
				isPrime = endpoint.blockingReceive();
				System.out.println((isPrime ? " eine " : " keine ") + "Primzahl");
			}
		}.start();
		
		while (isPrime == null) {
			System.out.print('.');
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    } 
}
