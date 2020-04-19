package hw2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sahin kasap
 */
public class StudyGroup {
    String name;
    Lab lab;
    StudyGroup(String name, Lab lab){
        this.name=name;
        this.lab=lab;
    }
    public String getName(){
        return name;
    }
    public Lab getLab(){
        return lab;
    }
    public void startStudyingWith(){
        lab.lock.lock();
        //System.out.println("--- add locked");
        try{
            if(lab.getCapacity()==0){
                throw new Error("Capacity cannot be 0");
            }
            while(true){
                if(lab.isFull() || (!lab.isEmpty() && !(this.lab.getStudyGroup().getName()==this.getName()))){
                    //todo
                    //System.out.println("--- is Full, await");
                    lab.cond.await();
                    //System.out.println("--- returned back to await");
                }
                else { //if(lab.isEmpty() || this.lab.studyGroup.getName()==this.getName()){
                    //System.out.println("---is Empty, break");
                    lab.setStudyGroup(this);
                    lab.incrementStudent();
                    break;
                }
                
            }
        }catch (InterruptedException ex) {
            //Logger.getLogger(Lab.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //System.out.println("--- add unlocked");
            lab.lock.unlock();
        }
    }
    //: A student associated with this study group will call this method to
    //study in the registered lab. If the lab is occupied by another study group or the lab is completely full, the
    //student should be blocked.
    public void stopStudyingWith(){
        lab.lock.lock();
        //System.out.println("--- remove locked");
        try{
            this.lab.decrementStudent();
            if(this.lab.getStudentNumber()<=0){
                this.lab.studyGroup=null;
            }
            //System.out.println("--- removed signaled");
            lab.cond.signal();
            //System.out.println("--- remove signal done");
        }
        finally{
            //System.out.println("--- remove unlocked");
            lab.lock.unlock();
        }
    }
}
