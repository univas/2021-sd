package br.edu.univas.si8.sd.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.edu.univas.si8.sd.helper.IOHelper;

public class SocketClient {

	private Logger log = LoggerFactory.getLogger(SocketClient.class);
	
	private String host;
	private int port;

	public static void main(String[] args) {
		new SocketClient("localhost", 3134).execute();
	}

	public SocketClient(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public void execute() {
		try {
			Socket sock = new Socket(host, port); // cria um socket
			IOHelper helper = new IOHelper(sock);

			log.error("Iniciando cliente. ");
			Scanner sc = new Scanner(System.in);
			System.out.println("Digite uma mensagem: ");
			String msg = sc.next() + sc.nextLine();// le um texto do teclado
			while (!msg.equals("exit")) {
				// envia a msg para o socket via ObjectOutputStream
				helper.send(msg);

				// recebe a resposta do socket via ObjectInputStream
				String resp = (String) helper.receive();
				if (resp != null) {
					log.error("Resposta: " + resp);
				} else {
					System.out.println("Não recebeu resposta do servidor.");
				}

				// le o proximo texto do teclado
				System.out.println("Digite uma mensagem: ");
				msg = sc.next() + sc.nextLine();
			}
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
