package br.edu.univas.si8.sd.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.edu.univas.si8.sd.helper.IOHelper;

public class ChatServer {

	private int port;

	public ChatServer(int port) {
		this.port = port;
	}

	public void execute() {
		try {
			System.out.println("Iniciando servidor.");
			ServerSocket server = new ServerSocket(port);

			while (true) {
				System.out.println("Esperando nova conexão.");
				Socket sock = server.accept();

				// associa o IOHelper do socket ao clientHostName
				String clientHostName = sock.getInetAddress().getHostName();
				int clientPort = sock.getPort();
				String clientId = clientHostName + ":" + clientPort;
				
				IOHelper io = new IOHelper(sock);
				ClientList.getInstance().addElement(clientId, io);
				System.out.println("Quantidade de clientes conectados: " + ClientList.getInstance().size());

				// cria e executa a thread para processar a conexão recebida
				ProcessaConexao pc = new ProcessaConexao(io, clientId);
				Thread th = new Thread(pc);
				th.start();
			}
			// server.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
