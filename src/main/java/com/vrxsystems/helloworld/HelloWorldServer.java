package com.vrxsystems.helloworld;

import org.zeromq.ZMQ;

public class HelloWorldServer {
	public static void main(String[] args) {
		
		String request = "hey!";
		
		System.out.println("Connecting to hello server");
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket socket = context.socket(ZMQ.REQ);
		socket.connect("tcp://javahello:5555");
		
//		System.out.println("Sending Hello " + requestNbr);
		socket.send(request.getBytes(ZMQ.CHARSET), 0);
		byte [] helloreply = socket.recv(0);
		String helloreplystr = new String(helloreply, ZMQ.CHARSET);
		
		socket.close();
		context.term();
		
		System.out.println("Connecting to world server");
		
		ZMQ.Context context2 = ZMQ.context(1);
		ZMQ.Socket socket2 = context2.socket(ZMQ.REQ);
		
		//ZMQ.Socket socket = context.socket(ZMQ.REQ);
		socket2.connect("tcp://javaworld:5556");
		socket2.send(request.getBytes(ZMQ.CHARSET), 0);
		byte [] worldreply = socket2.recv(0);
		String worldreplystr = new String(worldreply, ZMQ.CHARSET);
		
		socket2.close();
		context2.term();
		
		String helloWorld = helloreplystr + " " + worldreplystr;
		System.out.println("Final Response: " + helloWorld);
		
	}
}
