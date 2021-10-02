package br.edu.univas.si8.sd.chat.server;

import java.io.IOException;

import br.edu.univas.si8.sd.helper.IOHelper;

public class ProcessaConexao implements Runnable {

	private IOHelper io;
	private String sourceHost;

	public ProcessaConexao(IOHelper io, String sourceHost) {
		this.io = io;
		this.sourceHost = sourceHost;
	}

	@Override
	public void run() {
		try {
			while (true) {
				// recebe a mensagem
				String msg = (String) io.receive();

				// processa a mensagem
				msg = sourceHost + ": " + msg + '\n';
				System.out.println("Msg processada: " + msg);

				// envia a resposta
				ClientList.getInstance().sendMessage(msg);
			}
		} catch (IOException e) {
			System.out.println("Cliente " + sourceHost + " saiu.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// tira o cliente que desconectou, do cache interno
		ClientList.getInstance().removeElement(sourceHost);

		// envia mensagem para o restante indicando a saída dele
		ClientList.getInstance().sendMessage("Cliente " + sourceHost + " saiu.");
	}
}
