/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ca.server;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Antoinette M. bolger
 */
public class StartApp {
    private static ServerSocket socketServ;
    private static final int PORT = 1314;
    private static String action;
    private static String message;
    private static int numConnections=0;
    //create ArrayList to hold the information
    static ArrayList<Events> addedEvents = new ArrayList<>();

    public static void main(String[] args) {//start main    
        
        System.out.println("listening on asigned port");
        try {
            socketServ = new ServerSocket(PORT);//creating connection to the port
            socketServ.setReuseAddress(true);
        }//end try
        catch (IOException e) {
            System.out.println("unable to connect to port");
            System.exit(1);
        }//end catch
        do {
            run();
        } while (true);
    }
    private static void run(){
        Socket link = null;
        try {
            link = socketServ.accept();
            numConnections++;
            String IDClient= " client: "+ numConnections;
             RunnableImp clientSock = new RunnableImp (link);
             new Thread(clientSock).start();
            BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter out = new PrintWriter(link.getOutputStream(), true);
            message = in.readLine();
            System.out.println(message);
            if (message.matches("STOP")) {
                out.println("TERMINATE");
                try {
                    System.out.println("\n* the conection is closing");
                    link.close();				    //Step 5.
                } catch (IOException e) {
                    System.out.println("Unable to disconnect!");
                    System.exit(1);
                }
            }// end if
            else {
                String[] megsArr = message.split("&");
                action = megsArr[0];
                if (action.matches("add")) {
                    Events event = new Events();

                    String date = megsArr[1];
                    String time = megsArr[2];
                    String decription = megsArr[3];
                    event.setDate(date);
                    event.setTime(time);
                    event.setDecription(decription);
                    addedEvents.add(event);
                     for (int i = 0; i < addedEvents.size(); i++) {
                        if (date.matches(addedEvents.get(i).getDate())) {
                    out.println(addedEvents.get(i).toString());
                        }//end if
                     }//end for                   
                } else {
                    String date = megsArr[1];
                    String time = megsArr[2];
                    for (int i = 0; i < addedEvents.size(); i++) {
                        if (date.matches(addedEvents.get(i).getDate())&&time.matches(addedEvents.get(i).getTime())) {
                            addedEvents.remove(i);
                            out.println(addedEvents.toString());
                        }//end if
                    }//end for
               }//end else
            }// end else
        } catch (IOException e) {
            e.printStackTrace();
            try {
	    System.out.println("\n* Closing connection... *");
            link.close();				   
	}
       catch(IOException e2)
       {
            System.out.println("Unable to disconnect!");
	    System.exit(1);
       }
        }
    }//end main
    
}//end class
