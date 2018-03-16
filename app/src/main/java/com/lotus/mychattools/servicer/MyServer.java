package com.lotus.mychattools.servicer;

import java.net.*;
import java.io.*;
import java.util.*;

public class MyServer
{

	public static ArrayList<Socket> socketList
		= new ArrayList<Socket>();
    public static void main(String[] args)
		throws IOException
    {
		ServerSocket ss = new ServerSocket(30000);
		while(true)
		{

			Socket s = ss.accept();
			socketList.add(s);

			new Thread(new ServerThread(s)).start();
		}
    }
}
