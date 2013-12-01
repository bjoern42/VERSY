package forum.framework;

import java.io.*;
import java.rmi.*;

public interface IForumModel
{
    void registerView(String p0, IForumView p1) throws AlreadyBoundException, IOException;
    
    void deregisterView(String p0) throws NotBoundException, IOException;
    
    void moveNorth(String p0) throws NotBoundException, IOException;
    
    void moveEast(String p0) throws NotBoundException, IOException;
    
    void moveSouth(String p0) throws NotBoundException, IOException;
    
    void moveWest(String p0) throws NotBoundException, IOException;
}
