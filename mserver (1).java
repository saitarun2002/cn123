//SERVER
import java.io.*;
import java.net.*;

public class mserver
{
  public static Socket s[]=new Socket[10];
  public static String user[]=new String[10];
  public static int total;
 public static void main(String a[])
 {
  int i=0;
   try{
   ServerSocket ss=new ServerSocket(118);
   while(true)
   {
    s[i]=ss.accept();
BufferedReader br=new BufferedReader(new   InputStreamReader(s[i].getInputStream()));
   String msg=br.readLine();
    user[i]=msg;
    System.out.println(msg+" connected");
    try
    {
     reqhandler req=new reqhandler(s[i],i);
     total=i;
     i++;
     Thread t=new Thread(req);
     t.start();
    }catch(Exception e)
     { System.out.println(e); }
   }
  }catch(Exception e)
    { System.out.println(e); }
 }
}
class reqhandler implements Runnable 
{
  public int n;
  public Socket s;
  public reqhandler(Socket soc,int i)
  {
    s=soc;
    n=i;
  }
  public void run()
  {
   String msg=" ";
   BufferedReader br;
   PrintWriter pw;
   try{
     while(true)
     {
     br=new BufferedReader(new InputStreamReader(s.getInputStream()));
     msg=br.readLine();
     if(msg.equals("quit"))
           mserver.total--;
     else
           System.out.println(mserver.user[n]+"->"+msg);
     if(mserver.total==-1)
      {
        System.out.println("server Disconnected..");
        System.exit(0);
      }
    for(int k=0;k<=mserver.total;k++)
    {
     if(!mserver.user[k].equals(mserver.user[n])&&(!msg.equals("quit")))
     {
       pw=new PrintWriter(new OutputStreamWriter(mserver.s[k].getOutputStream()));
       pw.println(mserver.user[n]+":"+msg+"\n");
       pw.flush();
     }
    }
    }
   }catch(Exception e)
    {  }
  }
}







