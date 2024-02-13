/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mw.gov.agriculture.assestmanager.models;

/**
 *
 * @author USER
 */
public class Report {
    int score;
    String department;

    public Report(int score, String department) {
        this.score = score;
        this.department = department;
    }
    
    
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    
    
}
