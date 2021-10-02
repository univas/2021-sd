package br.edu.univas.si8.sd.chat.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.edu.univas.si8.sd.helper.IOHelper;

/**
 * Mantém um cache associando nome do host e seu respectivo IOHelper.
 * 
 * Essa classe é um Singleton, para permitir somente um cache no sistema.
 */
public class ClientList {

	// cache
	private Map<String, IOHelper> cache = new HashMap<String, IOHelper>();

	// início do singleton
	private static ClientList instance = null;

	private ClientList() {

	}

	public static ClientList getInstance() {
		if (instance == null) {
			instance = new ClientList();
		}
		return instance;
	}
	// fim do singleton

	public void addElement(String hostName, IOHelper sockIO) {
		cache.put(hostName, sockIO);
	}

//	public IOHelper getElement(String hostName) {
//		return cache.get(hostName);
//	}

	public void removeElement(String hostName) {
		cache.remove(hostName);
	}
	
	public int size() {
		return cache.size();
	}

	public void sendMessage(String msg) {

		// envia a mensagem para todos os clientes
		for (String hostname : cache.keySet()) {
			IOHelper io = cache.get(hostname);

			try {
				io.send(msg);
			} catch (IOException e) {
				System.out.println("Erro enviando mensagem para cliente " + hostname);
			}
		}
	}

}
