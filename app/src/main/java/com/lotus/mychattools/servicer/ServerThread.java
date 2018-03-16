package com.lotus.mychattools.servicer;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread implements Runnable
{

	Socket s = null;

	BufferedReader br = null;
	public ServerThread(Socket s)
		throws IOException
	{
		this.s = s;

		br = new BufferedReader(new InputStreamReader(
			s.getInputStream() , "utf-8"));
	}
	public void run()
	{
		try
		{
			String content = null;

			while ((content = readFromClient()) != null)
			{
				System.out.println("---" + Arrays.toString(content.getBytes("utf-8")));
				System.out.println("---" + content);

				for (Iterator<Socket> it = MyServer.socketList.iterator(); it.hasNext(); )
				{
					Socket s = it.next();
					try{

						OutputStream os = s.getOutputStream();
						os.write((content + "\n").getBytes("utf-8"));
					}
					catch(SocketException e)
					{
						e.printStackTrace();

						it.remove();
						System.out.println(MyServer.socketList);
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private String readFromClient()
	{
		try
		{
			return br.readLine();
		}

		catch (IOException e)
		{
			e.printStackTrace();

			MyServer.socketList.remove(s);
		}
		return null;
	}
}
