package forum.framework;

import java.util.*;
import java.io.*;

public interface IForumView
{
    void notifyView(Map<String, Position> p0) throws IOException;
}
