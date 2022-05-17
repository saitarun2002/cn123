#ChaT APP

Server.py
from socket import *
serverPort = 1201
serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(("localhost", serverPort))
print ("The server is ready to receive")
while 1:
    b, clientAddress = serverSocket.recvfrom(1201)
    message=b.decode()
    print(message)
    replymessage = input("Server:")
    print(replymessage)
    c=replymessage.encode()
    serverSocket.sendto(c,clientAddress)

Client.py
from socket import *
serverName = "localhost"
serverPort = 1201
#for i in range (0,10):
clientSocket = socket(AF_INET, SOCK_DGRAM)
message = input("Client:")
b=message.encode()
clientSocket.sendto(b,(serverName, serverPort))
c, serverAddress = clientSocket.recvfrom(1201)
modifiedMessage=c.decode()
print (modifiedMessage)
clientSocket.close()



mClient:

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


mserver:

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
