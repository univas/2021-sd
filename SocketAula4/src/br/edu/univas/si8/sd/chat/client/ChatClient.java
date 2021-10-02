package br.edu.univas.si8.sd.chat.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import br.edu.univas.si8.sd.helper.IOHelper;

public class ChatClient {

	private String host;
	private int port;

	public ChatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void execute() {
		Socket sock = null;
		IOHelper io = null;
		try {
			
			//abre o socket e armazena input e output
			sock = new Socket(host, port);
			io = new IOHelper(sock);
		} catch (Exception e) {
			System.out.println("Erro na conexão: " + e.getMessage());
			return;
		}
		System.out.println("Conectado...");

		//Cria e inicia a thread de recebimento
		ReceiveThread rt = new ReceiveThread(io);
		Thread th = new Thread(rt);
		th.start();

		String msg = "";

		Scanner sc = new Scanner(System.in);
		try {
			//le um texto do teclado
			msg = sc.next() + sc.nextLine();
			while (!msg.equals("exit")) {
				
				//envia o texto via socket
				io.send(msg);
				//le um texto do teclado
				msg = sc.next() + sc.nextLine();
			}
		} catch (IOException e) {
			System.out.println("Erro enviando mensagem: " + e.getMessage());
		}
	}
}
