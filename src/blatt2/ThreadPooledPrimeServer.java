package blatt2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

import blatt1.ServerEndpoint;

public class ThreadPooledPrimeServer {
    private static volatile boolean stopRequested = false;
	private ExecutorService pool;
	private final ServerEndpoint endpoint;
	
	public static void main(String[] args) {
		new ThreadPooledPrimeServer().run();
	}
	
	public ThreadPooledPrimeServer(int pThreads){
		pool = Executors.newFixedThreadPool(pThreads);
		endpoint = new ServerEndpoint();
	}
	
	public ThreadPooledPrimeServer(){
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
		while (!stopRequested) {
		    ServerEndpoint.Request request = endpoint.blockingReceive();
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
			stopRequested = true;
		}
    	
    }
}
