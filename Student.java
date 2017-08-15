package student;


import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter; 
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;


import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Student extends JFrame implements ActionListener
{
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    
  
    //constructor
    public Student ()
    {
        super("Student record");
        
        //setting up the window
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //creating a panel for the buttons
        JPanel thePanel = new JPanel();
        thePanel.setBackground(Color.DARK_GRAY);
        thePanel.setLayout(new FlowLayout());
        //creating the Read File button 
        JButton read = new JButton("Read File");
        read.setBackground(Color.LIGHT_GRAY);
        read.addActionListener(this);
        thePanel.add(read); // adding it to the panel
        //creating the Average button
        JButton average = new JButton ("Average");
        average.setBackground (Color.LIGHT_GRAY);
        average.addActionListener(this);
        thePanel.add(average); // adding it to the panel
        //creating the Write Result button
        JButton write = new JButton ("Write Result");
        write.setBackground(Color.LIGHT_GRAY);
        write.addActionListener(this);
        thePanel.add(write); //adding it to the panel
        
        add(thePanel, BorderLayout.CENTER); // adding the panel to the window
    }
    public static void main(String[] args) 
    {
        Student theWindow = new Student();
        theWindow.setVisible(true);
    }
    
    public void actionPerformed (ActionEvent e)
    {
        // variable declaration
        StudentData[] students = new StudentData[6];
        String buttonName = e.getActionCommand();
        //properly declaring instance objects
        for (int i = 0; i < 6; i++)
            students[i] = new StudentData();
        
        //when the button "Read File" is press we do the following
        if (buttonName.equals("Read File"))
        {
            //declaring input file variable
            Scanner fileIn = null;

            //checking whether the input file exists
            try
            {
                fileIn = new Scanner (new FileInputStream ("grades.txt"));
            }

            //if file doesn't exist, terminate the program with an error message
            catch (FileNotFoundException ex)
            {
               //pop-up a message to the user
               JOptionPane.showMessageDialog(rootPane, "Input file not found. Program aborting!");
               System.exit (0);
               
            }
            
            // if file exists, the following codes will be executed
            
            boolean answer = true;
            int index = 0;
            //read the file inputs as long as there is a line to be read
            while ((answer == fileIn.hasNextLine()) && (index < 6) )
            {
               
                

                //read the names
                students[index].name = fileIn.next();

                //read the scores
                students[index].score[0] = fileIn.nextFloat();
                students[index].score[1] = fileIn.nextFloat();
                students[index].score[2] = fileIn.nextFloat();
                index++;
            }
            fileIn.close (); //closing the file
            //pop-up a message to the user 
            JOptionPane.showMessageDialog(rootPane, "Each student's record has been read from file: scores.txt, and saved.");
        }
        //when the button "Average" is press 
        else if (buttonName.equals("Average"))
        {
            
            //checking if the input file has been read before pressing this button
            if (students[0].name.equals("N/A"))
            {
                //pop-up a message to the user 
                JOptionPane.showMessageDialog(rootPane, "Student records not read yet. Clik on Read File first!");
            }
          
            else 
            {
                //checking if any score value is negative
                try
                {
                   
                    for (int i = 0; i < 6; i++)
                    {
                        if (students[i].score[0] < 0 || students[i].score[1] < 0 || students[i].score[2] < 0)
                            throw new NegativeScore();
                        else
                            students[i].average = (students[i].score[0] + students[i].score[1] + students[i].score[2])/3;
                    }
                    //pop-up a message to the user 
                    JOptionPane.showMessageDialog(rootPane, "Each student's average has been computed and saved.");
                }
                catch(NegativeScore ex)
                {
                    //pop-up a message to the user 
                    JOptionPane.showMessageDialog(rootPane, "Cannot compute average, one or more student's scores are negative. Check input file!");
                    System.exit (0);
                }
            }
        }
        //when the button "Write Result" is press 
        else if (buttonName.equals("Write Result"))
        {
            
            //checking if the input file has been read before cliking this button
            if (students[0].name.equals("N/A"))
            {
                //pop-up a message to the user
                JOptionPane.showMessageDialog(rootPane, "Student records not read yet. Clik on Read File first!");
            }
            //checking if the averages has been computed before cliking this button
            else if (students[0].average == -1)
            {
                //pop-up a message to the user
                JOptionPane.showMessageDialog(rootPane, "Student average not computed yet. Clik on Average first!");
            }
            //outputing each student's record per line in the output file 
            else 
            {
                //declaring the output file variable 
                PrintWriter outputFile = null;

                //checking whether the output file exists
                try 
                {
                    outputFile = new PrintWriter (new FileOutputStream("average.txt"));
                }
                //if it doesn't exist, exit the program
                catch (FileNotFoundException f)
                {
                   //pop-up a message to the user 
                   JOptionPane.showMessageDialog(rootPane, "Output file not found. Program aborting!");
                   System.exit (0);
                }

                //if the output file exists the following code will be executed
                
                for (int i = 0; i < 6; i++)
                {
                    outputFile.println(students[i].name + "   " + students[i].average);
                }
                
                //pop-up a message to the user
                JOptionPane.showMessageDialog(rootPane, "See result in the file: averages.txt.");
            }
        }
        else 
        {
            
            JOptionPane.showMessageDialog(rootPane, "Unexpected error.");
        }
    }
    //exception class 
    public class NegativeScore extends Exception
    {
        public NegativeScore()
        {
            super ();
        }
    }
    

}