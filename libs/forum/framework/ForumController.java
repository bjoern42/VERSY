package forum.framework;

import java.awt.event.*;
import java.rmi.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public final class ForumController extends JPanel
{
    private static final long serialVersionUID = -6217560793413413345L;
    
    public ForumController(final String name, final IForumModel modelForwarder) {
        super();
        if (modelForwarder == null) {
            throw new NullPointerException();
        }
        final JButton northButton = new JButton("^");
        northButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    modelForwarder.moveNorth(name);
                }
                catch (NotBoundException e3) {
                    throw new AssertionError();
                }
                catch (IOException e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }
            }
        });
        final JButton eastButton = new JButton(">");
        eastButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    modelForwarder.moveEast(name);
                }
                catch (NotBoundException e3) {
                    throw new AssertionError();
                }
                catch (IOException e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }
            }
        });
        final JButton southButton = new JButton("v");
        southButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    modelForwarder.moveSouth(name);
                }
                catch (NotBoundException e3) {
                    throw new AssertionError();
                }
                catch (IOException e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }
            }
        });
        final JButton westButton = new JButton("<");
        westButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    modelForwarder.moveWest(name);
                }
                catch (NotBoundException e3) {
                    throw new AssertionError();
                }
                catch (IOException e2) {
                    JOptionPane.showMessageDialog(null, e2.getMessage());
                }
            }
        });
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(northButton, "North");
        buttonPanel.add(eastButton, "East");
        buttonPanel.add(southButton, "South");
        buttonPanel.add(westButton, "West");
        this.add(buttonPanel);
    }
}
