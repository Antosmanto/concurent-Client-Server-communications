/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ca.server;

/**
 *
 * @author Antoinette M. bolger
 */
public class Events {

    private String date;
    private String time;
    private String decription;

    public Events(String date, String time, String decription) {
        this.date = date;
        this.time = time;
        this.decription = decription;
    }

    public Events() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public String toString() {
        return "event date: " + date + ", event time: " + time + ", event decription: " + decription;
    }//end tostring

}//end class
