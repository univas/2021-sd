package br.edu.univas.si8.sd.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import br.edu.univas.si8.sd.helper.IOHelper;

public class SocketClient {

	public static void main(String[] args) {
		new SocketClient().execute();
	}

	public void execute() {
		int port = 3134;
		try {
			Socket sock = new Socket("localhost", port); // cria um socket
			IOHelper helper = new IOHelper(sock);

			Scanner sc = new Scanner(System.in);
			System.out.println("Digite uma mensagem: ");
			String msg = sc.next() + sc.nextLine();// le um texto do teclado
			while (!msg.equals("exit")) {
				// envia a msg para o socket via ObjectOutputStream
				helper.send(msg);

				// recebe a resposta do socket via ObjectInputStream
				String resp = (String) helper.receive();
				if (resp != null) {
					System.out.println("Resposta: " + resp);
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
