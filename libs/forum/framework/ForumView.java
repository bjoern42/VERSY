package forum.framework;

import javax.swing.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.*;
import java.util.*;

public final class ForumView extends JPanel implements IForumView
{
    private static final long serialVersionUID = -7895126227414811297L;
    private Map<String, Position> playerPos;
    private final int ROWS = 20;
    private final int COLS = 20;
    private final int PAD = 30;
    
    public ForumView() {
        super();
        this.playerPos = null;
        this.setLayout(new BorderLayout());
    }
    
    public void notifyView(final Map<String, Position> folks) throws IOException {
        this.playerPos = folks;
        this.repaint();
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final int w = this.getWidth();
        final int h = this.getHeight();
        final Insets insets = this.getInsets();
        final double xInc = (w - insets.left - insets.right - 60) / 20;
        final double yInc = (h - insets.top - insets.bottom - 60) / 20;
        Font font = g2.getFont().deriveFont(18.0f);
        g2.setFont(font);
        g2.setPaint(Color.BLACK);
        g2.setPaint(Color.blue);
        double x1 = insets.left + 30;
        double y1 = insets.top + 30;
        for (int j = 0; j <= 20; ++j) {
            g2.draw(new Line2D.Double(x1, y1, x1, y1 + 20.0 * yInc));
            g2.setPaint(Color.red);
            if (j < 10) {
                g2.drawString(Integer.toString(j), (float)(x1 + xInc / 3.0), (float)(y1 + 20.0 * yInc + 20.0));
            }
            else if (j < 20) {
                g2.drawString(Integer.toString(j), (float)(x1 + xInc / 4.0), (float)(y1 + 20.0 * yInc + 20.0));
            }
            g2.setPaint(Color.blue);
            x1 += xInc;
        }
        final double x2 = x1 - xInc;
        x1 = insets.left + 30;
        for (int j = 0; j <= 20; ++j) {
            g2.draw(new Line2D.Double(x1, y1, x2, y1));
            g2.setPaint(Color.red);
            if (19 - j < 10 && 19 - j >= 0) {
                g2.drawString(Integer.toString(19 - j), 15.0f, (float)(y1 + yInc * 0.7));
            }
            else if (19 - j < 20 && 19 - j >= 0) {
                g2.drawString(Integer.toString(19 - j), 6.0f, (float)(y1 + yInc * 0.7));
            }
            g2.setPaint(Color.blue);
            y1 += yInc;
        }
        if (this.playerPos != null) {
            final Iterator<String> iterator = this.playerPos.keySet().iterator();
            while (iterator.hasNext()) {
                final String player_name;
                final String name = player_name = iterator.next();
                final Position position = this.playerPos.get(player_name);
                System.out.println(String.valueOf(name) + " : " + this.playerPos.get(name) + "\n");
                final float device_coordinate_x = (float)(30.0 + position.getX() * xInc + 5.0);
                final float device_coordinate_y = (float)(y1 - yInc - position.getY() * yInc - 5.0);
                font = g2.getFont().deriveFont(12.0f);
                g2.setFont(font);
                g2.drawString(player_name.split(":")[1], device_coordinate_x, device_coordinate_y);
            }
        }
    }
}
