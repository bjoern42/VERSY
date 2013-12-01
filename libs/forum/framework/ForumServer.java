package forum.framework;

public final class ForumServer
{
    private final Runnable modelReceiver;
    
    public ForumServer(final Runnable modelReceiver) {
        super();
        this.modelReceiver = modelReceiver;
    }
    
    public void run() throws Exception {
        this.modelReceiver.run();
    }
}
