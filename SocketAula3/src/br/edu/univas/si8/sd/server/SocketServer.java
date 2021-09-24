package br.edu.univas.si8.sd.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class SocketServer {

	private int port;
	
	private Logger log = LoggerFactory.getLogger(SocketServer.class);
	
	public static void main(String[] args) {
		new SocketServer(3134).execute();
	}

	public SocketServer(int port) {
		this.port = port;
	}
	
	public void execute() {
		try {
			log.error("Iniciando servidor.");

			ServerSocket server = new ServerSocket(port); // cria um socket para ficar escutando
			while (true) {
				log.error("Aceitando a conexao.");
				Socket sock = server.accept();
				log.error("Aceitou a conexão.");
				
				// cria e executa a thread para processar a conexao recebida
				ProcessaConexao pc = new ProcessaConexao(sock);
				Thread th = new Thread(pc);
				th.start();
			}
			// server.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
