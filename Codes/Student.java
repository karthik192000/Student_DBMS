/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student_database;

/**
 *
 * @author karthik
 */
public class Student {
    private String fullName;
    private long prn;
    private long contact;
    private String branch;
    private String email;
    
     public Student(String fullName, long prn, long contactNumber, String branch,String emailId) {
         this.setFullName(fullName);
         this.setPRN(prn);
        this.setContact(contactNumber);
        this.setBranch(branch);
        this.setEmail(emailId);
    }
    
    public String getFullName(){
        return fullName;
    }
    
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public long getPRN(){
        return this.prn;
    }
    
    public void setPRN(long prn){
        this.prn = prn;
    }
    
    public long getContact(){
     return contact;
    }
    
    public void setContact(long contact){
     this.contact = contact;
    }
    
    public String getBranch(){
        return branch;
    }
    
    public void setBranch(String branch){
        this.branch = branch;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    
    
    
    
}
