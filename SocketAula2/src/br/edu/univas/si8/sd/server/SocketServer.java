package br.edu.univas.si8.sd.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.edu.univas.si8.sd.helper.IOHelper;

public class SocketServer {

	public static void main(String[] args) {
		new SocketServer().execute();
	}

	public void execute() {
		try {
			System.out.println("Iniciando servidor.");
			int port = 3134;

			ServerSocket server = new ServerSocket(port); // cria um socket para ficar escutando
			while (true) {
				System.out.println("Aceitando a conexao.");
				Socket sock = server.accept();
				System.out.println("Aceitou a conexão.");
				IOHelper helper = new IOHelper(sock);

				System.out.println("Lendo os dados.");
				String mensagem = (String) helper.receive();

				System.out.println("Dados recebidos: " + mensagem);

				// processamento qualquer do pedido
				String resposta = "Ola " + mensagem;
				System.out.println("Enviando a resposta: " + resposta);
				helper.send(resposta);

				System.out.println("Fechando a conexao.");
				sock.close();
			}
			// server.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
