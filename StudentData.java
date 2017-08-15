/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

/**
 *
 * @author Bertacio
 */
public class StudentData 
{
    public String name;    //every student has a name
    public float [] score = new float [3]; //every student has 3 scores
    public float average;  //the average of the above 3 scores
    public StudentData()
    {
        name= "N/A";
        score[0]= 0;
        score[1]= 0;
        score[2]= 0;
        average = -1;
    }
}