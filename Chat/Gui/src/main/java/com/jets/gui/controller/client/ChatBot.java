package com.jets.gui.controller.client;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

/**
 * @author Amer Salah
 */

public class ChatBot {

	String resourcesPath = "D:\\ITI\\Chat Application Project\\AIML Library\\program-ab-0.0.4.3";

    Bot bot = new Bot("super", resourcesPath);
    Chat chatSession = new Chat(bot);
    
    public String response(String message){
        String response = chatSession.multisentenceRespond(message);
        return response;
     }
    
    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }    
}
