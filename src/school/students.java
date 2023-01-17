package school;

import java.io.PrintWriter;
import static school.mainSchool.getCourse;
import static school.mainSchool.setCourse;

public class students {
    
    private int ID;
    private String firstName;
    private String lastName;
    private String Email;
    private int age;
    private int phone;
    private int level;
    private courses courses = new courses();
    private double gpa;
    private students left;
    private students right;
    

    public students() {
    }
    
    public students(int ID, String firstName, String lastName, String email, int age, int phone) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Email = email;
        this.age = age;
        this.phone = phone;
        this.level = 1; // level start with 1; no level start with 0 ! 
    }

    // getters and setter for all the data fields 
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public courses getCourses() {
        return courses;
    }

    public void setCourses(courses courses) {
        this.courses = courses;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public students getLeft() {
        return left;
    }

    public void setLeft(students left) {
        this.left = left;
    }

    public students getRight() {
        return right;
    }

    public void setRight(students right) {
        this.right = right;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    public static String NEWSTUDENT(dbmsBST bst, int id, String firstName, String lastName, String name, String email, int age, int phone, boolean isAdded, PrintWriter output) {
        // this method is used to add the students and it checks whether the student was added before or not
        if (!isAdded) { 
            bst.insert (id, firstName, lastName, email, age, phone);
            
            return "NEWSTUDENT Command \n" 
                    + "        " + name + "(ID " + id 
                    + ") has been inserted as a new student in KAUdbms"; 
        }
        else {
            return "NEWSTUDENT Command \n" + "    The student is already added to the BST ";
        }  
    } // end of NEWSTUDENT method 
        
///////////////////////////////////////////////////////////////////////////////////////////////////////////   
        
        public static String DELETE ( students search, String name, dbmsBST bst, PrintWriter output ){
            
            if (null != search) { // if the student was found then node will be deleted 
                
                bst.delete(name); // calling the method that will delete the node (student)       
                
                return "DELETE Command \n" 
                        + "        Student (" + name 
                        + ") has been removed from KAUdbms.";
            }
            else { // if the student was not found then no node will be deleted 
                return "DELETE Command \n " 
                        + "        Cannot Perform DELETE Command: \n" 
                        + "                Student (" 
                        + name + ") was not found in KAUdbms.";
            }
        }// end of DELETE method 
        
///////////////////////////////////////////////////////////////////////////////////////////////////////////    
            public static void ADDCOURSE ( int numberOfcourses, course helpPtr,int id, String courseID, int grade, students student, PrintWriter output, dbmsBST bst, courses allCourses ){
            
            if (getCourse() != null) {
                
                getCourse().setGrade(grade);
                output.println("ADDCOURSE Command \n"
                        + "        " + courseID
                        + ": grade has been changed/updated, to a " + grade + ", for Student ID " + id + ".");
            } 
            else {
                
                student.getCourses().insert(courseID, grade);
                
                output.println("ADDCOURSE Command \n" + "        "
                        + courseID + " (Grade: " 
                        + grade + ") has been added to the record of Student ID " + id + ".");
                
                numberOfcourses = student.getCourses().numberOfNodes();
                
                if (numberOfcourses < 0 || numberOfcourses >= 10) { 
                    if (numberOfcourses >= 10 && numberOfcourses < 20) {
                        student.setLevel(2);
                    } 
                    else if (numberOfcourses >= 20 && numberOfcourses < 30) {
                        student.setLevel(3);
                    } 
                    else if (numberOfcourses >= 30 && numberOfcourses < 40) {
                        student.setLevel(4);
                    } 
                    else if (numberOfcourses >= 40 && numberOfcourses < 50) {
                        student.setLevel(5);
                    } 
                    else {
                        student.setLevel(1);
                    }
                }
            }

            double total = 0;
            int hours = 0;
            helpPtr = student.getCourses().getHead();

            while (helpPtr != null) { 
                
                int PTS; 
                PTS = 0;
                
                if (helpPtr.getGrade() < 90) {
                    if (helpPtr.getGrade() >= 80) {
                        PTS = 4 * 3;
                    } 
                    else if (helpPtr.getGrade() >= 70) {
                        PTS = 3 * 3;
                    } 
                    else if (helpPtr.getGrade() >= 60) {
                        PTS = 2 * 3;
                    } 
                    else {
                        PTS = 0;
                    }
                } 
                else {
                    PTS = 5 * 3;
                }
                
                total = total + PTS;
                hours = hours + 3;
                
                helpPtr = helpPtr.getNext(); // MOVE TO THE NEXT NODE 
            } // end of the loop 
            
            float GPA = (float) total / hours;
            student.setGpa(GPA); 
            
            setCourse(allCourses.search(courseID));  
            
            if (null != getCourse()) {
                getCourse().setNum(getCourse().getNum() + 1 );
                getCourse().setGrade(getCourse().getGrade() + grade);

            }
            else {
                allCourses.insert(courseID, grade);
                allCourses.search(courseID).setNum ( 1 );
                
            }
        }// end of ADDCOURSE  method 

///////////////////////////////////////////////////////////////////////////////////////////////////////////      

    @Override
    public String toString() {
        return "KAUstudent{" + "ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", Email=" + Email + ", age=" + age + ", phone=" + phone + ", level=" + level + ", courses=" + courses + ", gpa=" + gpa + ", left=" + left + ", right=" + right + '}';
    } 
}
