import java.io.*;
import java.net.*;
 public class mclient
 {
  public static void main(String a[])
 {
  BufferedReader in;
  PrintWriter pw;
  try{
    Socket s=new Socket("localhost",118);
    System.out.println("Enter name:");
    in =new BufferedReader(new InputStreamReader(System.in));
    String msg=in.readLine();
    pw=new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
    pw.println(msg+"\n");
    pw.flush();
    while(true)
    {
     readdata rd=new readdata(s);
     Thread t=new Thread(rd);
     t.start();
     msg=in.readLine();
     if(msg.equals("quit"))
      { System.exit(0);}
     pw.println(msg);
     pw.flush();
    }
    }catch(Exception e)
     { System.out.println(e); }
  }      }
class readdata implements Runnable
{
  public Socket s;
  public readdata(Socket s)
  { this.s=s; }
  public void run()
  {
   BufferedReader br;
   try
   {
    while(true)
    {
     br= new BufferedReader(new InputStreamReader(s.getInputStream()));
     String msg=br.readLine();
     System.out.println(msg);
    }   }catch(Exception e)
    { System.out.println(e); }       }    }


