package blatt8.gen;


/**
* blatt8/gen/FolksHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Montag, 16. Dezember 2013 13:50 Uhr MEZ
*/

abstract public class FolksHelper
{
  private static String  _id = "IDL:blatt8/gen/Folks:1.0";

  public static void insert (org.omg.CORBA.Any a, blatt8.gen.PositionedAvatar[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static blatt8.gen.PositionedAvatar[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = blatt8.gen.PositionedAvatarHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (blatt8.gen.FolksHelper.id (), "Folks", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static blatt8.gen.PositionedAvatar[] read (org.omg.CORBA.portable.InputStream istream)
  {
    blatt8.gen.PositionedAvatar value[] = null;
    int _len0 = istream.read_long ();
    value = new blatt8.gen.PositionedAvatar[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = blatt8.gen.PositionedAvatarHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, blatt8.gen.PositionedAvatar[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      blatt8.gen.PositionedAvatarHelper.write (ostream, value[_i0]);
  }

}
