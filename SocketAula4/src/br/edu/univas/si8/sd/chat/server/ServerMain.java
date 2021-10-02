package br.edu.univas.si8.sd.chat.server;

public class ServerMain {

	public static void main(String[] args) {
		ChatServer cs = new ChatServer(3134);
		cs.execute();
	}
}
