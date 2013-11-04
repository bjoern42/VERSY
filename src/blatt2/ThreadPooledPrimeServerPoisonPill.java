package blatt2;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import blatt1.ClientEndpoint;
import blatt1.ServerEndpoint;

public class ThreadPooledPrimeServerPoisonPill {
    private final int POISON_PILL = -1;
	private ExecutorService pool;
	private final ServerEndpoint endpoint;
	
	public static void main(String[] args) {
		new ThreadPooledPrimeServerPoisonPill().run();
	}
	
	public ThreadPooledPrimeServerPoisonPill(int pThreads){
		pool = Executors.newFixedThreadPool(pThreads);
		endpoint = new ServerEndpoint();
	}
	
	public ThreadPooledPrimeServerPoisonPill(){
		int cores = Runtime.getRuntime().availableProcessors();
		int poolsize = (int) ((int)cores / 0.5);
		endpoint = new ServerEndpoint();
		pool = Executors.newFixedThreadPool(poolsize);
		System.out.println("Set poolsize to "+poolsize);
	}
	
	private boolean isPrime(long number) {
		for (long i = 2; i <= Math.sqrt(number); i++) {
		    if (number % i == 0) {
		    	return false;
		    }
		}
		return true;
    }

    void run() {
		System.out.println("Thread pooled PrimeServer up and running...");
	
		new Thread(new ShutdownGUI()).start();
		while (true) {
		    ServerEndpoint.Request request = endpoint.blockingReceive();
		    if(request.getNumber() == POISON_PILL){
		    	System.out.println("received poison pill! Shutting down...");
		    	break;
		    }
		    pool.execute(new Handler(request));
		}
		
		pool.shutdown();
    }
    
    private class Handler implements Runnable {
    	ServerEndpoint.Request request;
    	
    	public Handler(ServerEndpoint.Request pRequest){
    		request = pRequest;
    	}
    	
		@Override
		public void run() {
		    boolean prime = isPrime(request.getNumber());
		    endpoint.send(request.getSender(), prime);
		}
    }
    
    private class ShutdownGUI implements Runnable{
    	
		@Override
		public void run() {
			JOptionPane.showMessageDialog(null, "Press OK to stop the server.");
			final ClientEndpoint endpoint = new ClientEndpoint();
			SocketAddress server = new InetSocketAddress("localhost", 4711);
				
			endpoint.send(server, POISON_PILL);
		}
    	
    }
}
