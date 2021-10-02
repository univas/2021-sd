package br.edu.univas.si8.sd.chat.client;

import java.io.IOException;

import br.edu.univas.si8.sd.helper.IOHelper;

public class ReceiveThread implements Runnable {

	private IOHelper io;

	public ReceiveThread(IOHelper io) {
		this.io = io;
	}

	@Override
	public void run() {
		try {
			while(true) {
				// recebe a resposta do socket
				String resp = (String) io.receive();
				if (resp != null) {
					System.out.println("Mensagem: " + resp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
