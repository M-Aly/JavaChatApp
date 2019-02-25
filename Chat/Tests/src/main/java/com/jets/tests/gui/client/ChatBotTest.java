package com.jets.tests.gui.client;

import com.jets.gui.controller.client.ChatBot;

public class ChatBotTest {
	
	public static void main(String argc[])
	{
		ChatBot bot = new ChatBot();
		String response = bot.response("hi");
		System.out.println(response);
	}

}
