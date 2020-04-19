package hw2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab {
    String name;
    int capacity;
    int currentStudentNumber=0;
    StudyGroup studyGroup = null;
    Lock lock = new ReentrantLock();
    Condition cond = lock.newCondition();
    public void incrementStudent(){
        //System.out.println("--- lab increment");
        currentStudentNumber++;
    }
    public void decrementStudent(){
        currentStudentNumber--;
    }
    public int getStudentNumber(){
        return currentStudentNumber;
    }
    public Lab(String name, int capacity){
        this.name=name;
        this.capacity=capacity;
    }
    public String getName(){
        return name;
    }
    public int getCapacity(){
        return capacity;
    }
    public boolean isFull(){
        return currentStudentNumber >= capacity;
    }
    public boolean isEmpty(){
        return currentStudentNumber<=0;
    }
    public StudyGroup getStudyGroup(){
        return studyGroup;
    }
    public void setStudyGroup(StudyGroup sg){
        //System.out.println("--- lab stuy");
        this.studyGroup = sg;
    }
    
}
