package br.edu.univas.si8.sd.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		new SocketServer().execute();
	}

	//TODO: corrigir o bug de apendar as mensagens de diferentes chamadas de clientes
	public void execute() {
		try {
			System.out.println("Iniciando servidor.");
			int port = 3134;
			byte[] dados = new byte[10]; // buffer de leitura de tamanho fixo
			StringBuffer buffer = new StringBuffer(); // buffer geral

			ServerSocket server = new ServerSocket(port); // cria um socket para ficar escutando
			while (true) {
				System.out.println("Aceitando a conexao.");
				Socket sock = server.accept();

				System.out.println("Lendo os dados.");
				InputStream in = sock.getInputStream(); // le os dados a partir do inputStream
				int qtd = in.read(dados); // le 10 bytes
				while (qtd > 0) {
					buffer.append(new String(dados, 0, qtd));
					qtd = in.available(); // verifica se existe dados para ler
					if (qtd > 0) { // isto evita ficar preso no read bloqueante
						qtd = in.read(dados); // le 10 bytes
					}
				}
				System.out.println("Dados recebidos: " + buffer.toString());

				// processamento qualquer do pedido
				String resposta = "Ola " + buffer.toString();
				OutputStream out = sock.getOutputStream();

				System.out.println("Enviando a resposta: " + resposta);
				out.write(resposta.getBytes()); // envia dados através do outputStream
				System.out.println("Fechando a conexao.");
				sock.close();
			}
			// server.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
