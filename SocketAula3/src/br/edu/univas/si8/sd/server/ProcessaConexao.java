package br.edu.univas.si8.sd.server;

import java.io.IOException;
import java.net.Socket;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import br.edu.univas.si8.sd.helper.IOHelper;

public class ProcessaConexao implements Runnable {

	private Socket sock;
	
	private Logger log = LoggerFactory.getLogger(ProcessaConexao.class);

	public ProcessaConexao(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		try {
			IOHelper helper = new IOHelper(sock);

			while (true) {

				log.error("Lendo os dados.");
				String mensagem = (String) helper.receive();

				System.out.println("Dados recebidos: " + mensagem);

				// processamento qualquer do pedido
				String resposta = "Ola " + mensagem;
				System.out.println("Enviando a resposta: " + resposta);
				helper.send(resposta);

				//System.out.println("Fechando a conexao.");
				// sock.close();
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
