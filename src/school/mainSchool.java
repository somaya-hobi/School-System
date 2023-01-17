package school;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class mainSchool { 

        private static int id;
        private static String FName, LName, Name, Email;
        private static int Age, Phone, numberOfcourses;
        private static students search;
        private static dbmsBST bst;
        private static students student;
        private static courses Courses;
        private static course course, helpPtr;

    public static void main(String[] args) throws Exception{
        
        // creat the files ( input and output) 
        File inputFile, outputFile;
        inputFile = new File("input.in.txt");
        outputFile  = new File("output.out.txt");
        
        PrintWriter output;
        
        try (Scanner input = new Scanner (inputFile)) {
            
            output = new PrintWriter(outputFile);
            // check if the input files are exist or not
            if (!inputFile.exists()) {
                System.out.println("Input file is not exit !");
            }
            else {
                System.out.println("Input file is exit !");
            }
            
            int numBEROfCommands = input.nextInt(); // reading the first number (49). it is the number of commands that will be run on the database.
            
            bst = new dbmsBST(); // creating a object from class KAUdbmsBST
            Courses = new courses(); // creating a object from class KAUcourses 

            int i = 0; 
            while (i < numBEROfCommands) { // while loop (49) times 

                String command = input.next(); // reading the command from the input file 
                switch (command) {
                    
                    case "NEWSTUDENT":{
                        
                        id = input.nextInt();
                        FName = input.next();
                        LName = input.next();
                        Name = FName + " " + LName;
                        Email = input.next();
                        Age = input.nextInt();
                        Phone = input.nextInt();
                        boolean isAdded;
                        isAdded = bst.search(id);
                        
                        // calling the NEWSTUDENT method 
                        output.println(students.NEWSTUDENT(bst, id, FName, LName, Name, Email, Age, Phone, isAdded, output));
                        
                        break;} // end of NEWSTUDENT case 
                    
                    /////////////////////////////////////////
                        
                        case "SEARCHID":{
                        
                            id = input.nextInt();
                            search = bst.findNode(id);

                            output.println(SEARCHID(id, search, output, bst)); // calling the SEARCHID method 
                            // this method is used to search for the students by IDs! 
  
                        break;}
                    
                    /////////////////////////////////////////
                        
                        case "SEARCHNAME":{
                        
                            FName = input.next();
                            LName = input.next();
                            Name = FName + " " + LName;

                            student = bst.findNodeName(Name); // creating an object and check if the student is found or not 
                            output.println(SEARCHNAME(Name, student, output, bst)); // calling the SEARCHNAME method 
                            // this method is used to search for the students by names! 
                        
                        break;} // end of SEARCHNAME case 
                    
                    /////////////////////////////////////////
                        
                    case "ADDCOURSE":{
                        
                        id = input.nextInt();
                        String courseID = input.next();
                        int grade = input.nextInt();

                        student = bst.findNode(id); // finding the node by the id's 
                        
                        if (student != null) {
                            setCourse(student.getCourses().search(courseID));
                            students.ADDCOURSE (numberOfcourses, getHelpPtr(), id, courseID, grade, student, output, bst, Courses); 
                        }
                        else{
                            output.println("ADDCOURSE Command \n" + "        ERROR: cannot add course. Student ID # " + id + " was not found in KAUdbms.");
                        }
                        break;} // end of ADDCOURSE case 
                    
                    /////////////////////////////////////////

                }
                output.flush(); 
                
                ++ i; // increment the counter 
            } // end of the loop 
        }
        output.close();
    } // end of main method 

///////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    public static String SEARCHID(int id, students search, PrintWriter output, dbmsBST bst) { // this method is used to search for the students by their IDs! 
  
        if (search != null) {
            return "SEARCHID Command \n" + "        ID " + id + " was found in FCITbook with name: " + search.getFirstName() + " " + search.getLastName() + ".";
        }
        
        else {
            return "SEARCHID Command \n" + "        ID " + id + " was not found in FCITbook.";
        }   
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////      
    
    public static String SEARCHNAME(String name, students student,PrintWriter output, dbmsBST bst) { 
    // this method is used to search for the students by names! 
    
        if (student != null) {
            return "SEARCHNAME Command \n" + "        " + name + " was found in FCITbook with ID " + student.getID() + ".";
        }
        else {
            return "SEARCHNAME Command \n" +  "        " + name + " was not found in FCITbook.";
        }    
    } // end of searchName method 
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////          


    public static course getHelpPtr() {
        return helpPtr;
    }

    public static void setHelpPtr(course aHelpPtr) {
        helpPtr = aHelpPtr;
    }

    public static course getCourse() {
        return course;
    }

    public static void setCourse(course aCourse) {
        course = aCourse;
    }
    
} // end of class mainSchool
