package blatt8.gen;


/**
* blatt8/gen/CorbaForumModelPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Montag, 16. Dezember 2013 13:50 Uhr MEZ
*/

public abstract class CorbaForumModelPOA extends org.omg.PortableServer.Servant
 implements blatt8.gen.CorbaForumModelOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("registerView", new java.lang.Integer (0));
    _methods.put ("deregisterView", new java.lang.Integer (1));
    _methods.put ("moveNorth", new java.lang.Integer (2));
    _methods.put ("moveEast", new java.lang.Integer (3));
    _methods.put ("moveSouth", new java.lang.Integer (4));
    _methods.put ("moveWest", new java.lang.Integer (5));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // blatt8/gen/CorbaForumModel/registerView
       {
         try {
           String name = in.read_string ();
           blatt8.gen.CorbaForumView view = blatt8.gen.CorbaForumViewHelper.read (in);
           this.registerView (name, view);
           out = $rh.createReply();
         } catch (blatt8.gen.AlreadyBoundException $ex) {
           out = $rh.createExceptionReply ();
           blatt8.gen.AlreadyBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // blatt8/gen/CorbaForumModel/deregisterView
       {
         try {
           String name = in.read_string ();
           this.deregisterView (name);
           out = $rh.createReply();
         } catch (blatt8.gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           blatt8.gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // blatt8/gen/CorbaForumModel/moveNorth
       {
         try {
           String name = in.read_string ();
           this.moveNorth (name);
           out = $rh.createReply();
         } catch (blatt8.gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           blatt8.gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 3:  // blatt8/gen/CorbaForumModel/moveEast
       {
         try {
           String name = in.read_string ();
           this.moveEast (name);
           out = $rh.createReply();
         } catch (blatt8.gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           blatt8.gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // blatt8/gen/CorbaForumModel/moveSouth
       {
         try {
           String name = in.read_string ();
           this.moveSouth (name);
           out = $rh.createReply();
         } catch (blatt8.gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           blatt8.gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 5:  // blatt8/gen/CorbaForumModel/moveWest
       {
         try {
           String name = in.read_string ();
           this.moveWest (name);
           out = $rh.createReply();
         } catch (blatt8.gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           blatt8.gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:blatt8/gen/CorbaForumModel:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CorbaForumModel _this() 
  {
    return CorbaForumModelHelper.narrow(
    super._this_object());
  }

  public CorbaForumModel _this(org.omg.CORBA.ORB orb) 
  {
    return CorbaForumModelHelper.narrow(
    super._this_object(orb));
  }


} // class CorbaForumModelPOA
