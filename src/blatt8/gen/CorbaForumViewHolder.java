package blatt8.gen;

/**
* blatt8/gen/CorbaForumViewHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Montag, 16. Dezember 2013 13:50 Uhr MEZ
*/

public final class CorbaForumViewHolder implements org.omg.CORBA.portable.Streamable
{
  public blatt8.gen.CorbaForumView value = null;

  public CorbaForumViewHolder ()
  {
  }

  public CorbaForumViewHolder (blatt8.gen.CorbaForumView initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = blatt8.gen.CorbaForumViewHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    blatt8.gen.CorbaForumViewHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return blatt8.gen.CorbaForumViewHelper.type ();
  }

}
