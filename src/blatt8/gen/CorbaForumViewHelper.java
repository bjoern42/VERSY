package blatt8.gen;


/**
* blatt8/gen/CorbaForumViewHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Montag, 16. Dezember 2013 13:50 Uhr MEZ
*/

abstract public class CorbaForumViewHelper
{
  private static String  _id = "IDL:blatt8/gen/CorbaForumView:1.0";

  public static void insert (org.omg.CORBA.Any a, blatt8.gen.CorbaForumView that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static blatt8.gen.CorbaForumView extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (blatt8.gen.CorbaForumViewHelper.id (), "CorbaForumView");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static blatt8.gen.CorbaForumView read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_CorbaForumViewStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, blatt8.gen.CorbaForumView value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static blatt8.gen.CorbaForumView narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof blatt8.gen.CorbaForumView)
      return (blatt8.gen.CorbaForumView)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      blatt8.gen._CorbaForumViewStub stub = new blatt8.gen._CorbaForumViewStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static blatt8.gen.CorbaForumView unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof blatt8.gen.CorbaForumView)
      return (blatt8.gen.CorbaForumView)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      blatt8.gen._CorbaForumViewStub stub = new blatt8.gen._CorbaForumViewStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}