package forum.framework;

import java.util.concurrent.*;
import java.util.*;
import java.rmi.*;
import java.io.*;

public enum ForumModel implements IForumModel
{
    INSTANCE("INSTANCE", 0);
    
    private final Map<String, IForumView> clients;
    private Map<String, Position> avatars;
    private final ExecutorService executor;
    
    private void additionalDeregisterView(final String name) {
        this.avatars.remove(name);
    }
    
    private ForumModel(final String s, final int n) {
        this.clients = new ConcurrentHashMap<String, IForumView>();
        this.executor = Executors.newCachedThreadPool();
        this.additionalInitialization();
    }
    
    private void additionalInitialization() {
        this.avatars = new ConcurrentHashMap<String, Position>();
    }
    
    private void notifyAllClients() {
        for (final String name : this.clients.keySet()) {
            final IForumView client_view_forwarder = this.clients.get(name);
            this.executor.execute(new NotificationTask(name, client_view_forwarder, (NotificationTask)null));
        }
    }
    
    public synchronized void registerView(final String name, final IForumView view) throws AlreadyBoundException {
        System.out.println("registerclient: " + name);
        if (this.clients.containsKey(name)) {
            throw new AlreadyBoundException("client already exists");
        }
        this.clients.put(name, view);
        this.additionalRegisterView(name, view);
        this.notifyAllClients();
    }
    
    private void additionalRegisterView(final String name, final IForumView view) {
        this.avatars.put(name, Position.getStartPosition());
    }
    
    public synchronized void deregisterView(final String name) throws NotBoundException {
        System.out.println("deregisterclient: " + name);
        if (!this.clients.containsKey(name)) {
            throw new NotBoundException("client doesn't exist");
        }
        this.clients.remove(name);
        this.additionalDeregisterView(name);
        this.notifyAllClients();
    }
    
    public synchronized void moveNorth(final String name) throws NotBoundException, IOException {
        System.out.println("moveNorth: " + name);
        if (!this.clients.containsKey(name)) {
            throw new NotBoundException("client doesn't exist");
        }
        final Position position = this.avatars.get(name);
        this.avatars.put(name, position.northOf());
        this.notifyAllClients();
    }
    
    public synchronized void moveEast(final String name) throws NotBoundException, IOException {
        System.out.println("moveEast: " + name);
        if (!this.clients.containsKey(name)) {
            throw new NotBoundException("client doesn't exist");
        }
        final Position position = this.avatars.get(name);
        this.avatars.put(name, position.eastOf());
        this.notifyAllClients();
    }
    
    public synchronized void moveSouth(final String name) throws NotBoundException, IOException {
        System.out.println("moveSouth: " + name);
        if (!this.clients.containsKey(name)) {
            throw new NotBoundException("client doesn't exist");
        }
        final Position position = this.avatars.get(name);
        this.avatars.put(name, position.southOf());
        this.notifyAllClients();
    }
    
    public synchronized void moveWest(final String name) throws NotBoundException, IOException {
        System.out.println("moveWest: " + name);
        if (!this.clients.containsKey(name)) {
            throw new NotBoundException("client doesn't exist");
        }
        final Position position = this.avatars.get(name);
        this.avatars.put(name, position.westOf());
        this.notifyAllClients();
    }
    
    private class NotificationTask implements Runnable
    {
        private String name;
        private IForumView forumForwarder;
        
        private NotificationTask(final String name, final IForumView forumForwarder) {
            super();
            this.name = name;
            this.forumForwarder = forumForwarder;
        }
        
        private void notifyClient() throws IOException {
            this.forumForwarder.notifyView(ForumModel.this.avatars);
        }
        
        public void run() {
            try {
                this.notifyClient();
            }
            catch (IOException e) {
                ForumModel.this.clients.remove(this.name);
                ForumModel.this.additionalDeregisterView(this.name);
            }
        }
    }
}
