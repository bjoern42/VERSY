package forum.framework;

import java.net.*;
import java.rmi.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public final class ForumClient
{
    private IForumModel modelForwarder;
    private ForumView view;
    
    public ForumClient(final IForumModel modelForwarder) throws Exception {
        super();
        this.modelForwarder = modelForwarder;
        this.view = new ForumView();
    }
    
    private String registerName() throws IOException {
        final String host = InetAddress.getLocalHost().getHostName();
        String qualified_name = "";
        boolean nameAlreadyTaken;
        do {
            nameAlreadyTaken = false;
            final String name = JOptionPane.showInputDialog("Name");
            if (name == null) {
                return null;
            }
            try {
                qualified_name = String.valueOf(host) + ":" + name;
                this.modelForwarder.registerView(qualified_name, this.view);
            }
            catch (AlreadyBoundException e) {
                nameAlreadyTaken = true;
            }
        } while (nameAlreadyTaken);
        return qualified_name;
    }
    
    public synchronized void register() throws IOException {
        final String name = this.registerName();
        if (name == null) {
            return;
        }
        final ForumController controller = new ForumController(name, this.modelForwarder);
        final JFrame clientUI = new JFrame("Forum Client " + name.split(":")[1] + " from " + name.split(":")[0]);
        clientUI.setLocationRelativeTo(null);
        clientUI.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                try {
                    ForumClient.this.modelForwarder.deregisterView(name);
                }
                catch (Exception ex) {}
            }
        });
        clientUI.setDefaultCloseOperation(2);
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        clientUI.add(mainPanel);
        mainPanel.add(this.view, "Center");
        this.addController(mainPanel, controller);
        clientUI.pack();
        clientUI.setSize(900, 600);
        clientUI.setVisible(true);
    }
    
    private void addController(final JPanel panel, final ForumController controller) {
        panel.add(controller, "West");
    }
}
