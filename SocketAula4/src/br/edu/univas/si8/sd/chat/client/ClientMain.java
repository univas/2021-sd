package br.edu.univas.si8.sd.chat.client;

public class ClientMain {

	public static void main(String[] args) {
		String host = "localhost";
		System.out.println("Iniciando Chat com " + host);
		ChatClient cc = new ChatClient(host, 3134);
		cc.execute();
	}
}
