package br.edu.univas.si8.sd.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		new SocketClient().execute();
	}

	public void execute() {

		try {
			System.out.println("Iniciando o cliente.");
			int port = 80;
			Socket sock = new Socket("www.google.com", port); // cria um socket
			String mensagem = "GET /ga.js HTTP/1.1\r\n";

			OutputStream out = sock.getOutputStream();
			System.out.println("Enviando dados.");
			out.write(mensagem.getBytes()); // envia dados
			out.flush();

			// buffer de leitura de tamanho fixo
			byte[] dados = new byte[10];
			StringBuffer buffer = new StringBuffer();

			// recebe e imprime os dados
			InputStream in = sock.getInputStream();
			int qtd = in.read(dados); // le 10 bytes
			while (qtd > 0) {
				buffer.append(new String(dados, 0, qtd));
				qtd = in.available(); // verifica se existe dados para ler
				if (qtd > 0) {
					qtd = in.read(dados); // le 10 bytes
				}
			}

			System.out.println("Resposta do servidor: " + buffer.toString());
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
