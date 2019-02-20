package com.jets.network.server.service.session;

import java.net.InetSocketAddress;
List

public interface Session {
	void sendMessage(InetSocketAddress sender, String message);
}
