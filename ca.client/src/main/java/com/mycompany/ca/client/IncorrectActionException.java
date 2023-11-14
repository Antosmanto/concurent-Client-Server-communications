/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ca.client;

/**
 *
 * @author Antoinette M. bolger
 */
public class IncorrectActionException extends Exception {

   

    public IncorrectActionException() {
        super("This Action can not be proformed, please enter 'add' or 'remove'");
    }

    public IncorrectActionException(String message) {
        super(message);
       
    }

}
