package com.example.myservice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class UdpHelper implements Runnable
{
	public Boolean IsThreadDisable = false;
	Handler _handle;
	
	public UdpHelper(Handler handler)
	{
		_handle = handler;
	}

	public void run() 
	{
		try
		{
			Integer port = 9999;
			byte[] message = new byte[100];
			DatagramSocket datagramSocket = new DatagramSocket(port);
			datagramSocket.setBroadcast(true);
			DatagramPacket datagramPacket = new DatagramPacket(message, message.length);
			while (!IsThreadDisable)
			{
				datagramSocket.receive(datagramPacket);
				String strMsg = new String(datagramPacket.getData()).trim();

				Message msg = _handle.obtainMessage();
				msg.obj = strMsg;
				_handle.sendMessage(msg);
			}
		}		catch(IOException e)
		{
			Log.e("XUE", e.getMessage());

		}

	}

	public static void send(String message)
	{
		message = (message == null ? "Hello IdeasAndroid!" : message);
		int server_port = 8904;
		Log.d("UDP Demo", "UDP·¢ËÍÊý¾Ý:" + message);
		DatagramSocket s = null;
		try
		{
			s = new DatagramSocket();
		} catch (SocketException e)
		{
			e.printStackTrace();
		}
		InetAddress local = null;
		try
		{
			local = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		int msg_length = message.length();
		byte[] messageByte = message.getBytes();
		DatagramPacket p = new DatagramPacket(messageByte, msg_length, local, server_port);
		try
		{

			s.send(p);
			s.close();

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}