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


