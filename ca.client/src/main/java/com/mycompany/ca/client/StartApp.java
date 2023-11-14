/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ca.client;

import java.util.Scanner;
import java.io.*;
import java.net.*;


/**
 *
 * @author Antoinette M. bolger
 */
public class StartApp {//start app
    
    private static InetAddress IPlocal;
    private static final int port = 1314;
    static String event;
    static String action;
    static String clientID;
    static String message;
     static Socket link = null;
    //make reference to the scanner
    static Scanner input = new Scanner(System.in);

   
        
    
    

    public static void main(String args[]) {//start main
        try {
            IPlocal = InetAddress.getLocalHost();//get local ip address
        } catch (UnknownHostException e) {
            System.out.println("IP Address ID not found");
            System.exit(1);
        }
        menu();//calling menu for user's choice
    }//end main

    private static void run() {
        try {
            link = new Socket(IPlocal, port);//asign IP adress and port for link to use
            BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
            PrintWriter out = new PrintWriter(link.getOutputStream(), true);
            String response = null;
            out.println(message);
            response = in.readLine();
            if (response.matches("TERMINATE")) {
                try {
                    System.out.println("thank you for usimg Dublin Events Calendar");
                    link.close();
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("Unable to disconnect/close!");
                    System.exit(1);
                }
            }//end else
            else {
                System.out.println(response);
                menu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }//end catch
    }//end method

    private static void menu() {

        System.out.println("Welcome to Dublin event calendar");
        System.out.println("Please choose an options from the menu:");
        System.out.println("| 1 Edit calendar | 2 Log Out |");
        String choice = input.next();
        switch (choice) {//start switch
            case "1":
                editEvent();
                break;
            case "2":
                logOut();
                break;
            default:
                System.out.println("invaild choice, please choise again.");
                menu();

                break;
        }//end Switch

    }//end method

    private static void logOut() {//start method
        message="STOP";
        run();
    }//end method

    private static void eventDetails() {//method take all event details
        System.out.println("Plese enter the date of the event");
        String Date = input.next();
        System.out.println("Please enter the time of the event");
        String Time = input.next();
        System.out.println("Please enter the brief description of the event");
        String description = input.nextLine();
        description += input.nextLine();
        String join = "&";//join to be used as a delinator on server side
        event = join + Date + join + Time + join + description;
    }//end method

    private static void editEvent() {
        eventDetails();
        pickAction();
    }//end method

    private static void pickAction() {//method set action when users enters correct key words and call run method
        try {
            System.out.println("Please enter the action you want \n 'add' or 'remove'");
            action = input.next();
            if (action.matches("add")) {
                message=action + event;
                run();
            } else if (action.matches("remove")) {
                 message=action + event;
                run();
            } else {
                throw new IncorrectActionException(); //tells try when to throw excetion
            }
        }//end try
        catch (IncorrectActionException e) {
            System.out.println(e.getMessage());
            pickAction();// only called when else is used 
        }

    }//end method

   

}//end classes
