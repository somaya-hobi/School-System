package school; 


import java.io.PrintWriter;



public class dbmsBST {
    
    private students root;

    public dbmsBST() {
    }

    public dbmsBST(students root) {
        this.root = root;
    }
     
    // getters and setters 
    public students getRoot() {
        return root;
    }

    public void setRoot(students root) {
        this.root = root;
    }
    
    // methods 
    public boolean isEmpty() {
        
        if (root != null) {
            return false;
        } 
        else {
            return true;
        } 
    }
    
    public boolean search(int id) {
        
        if (!isEmpty()) {
            students current = root;
            while (current.getID() != id) {
                if (id >= current.getID()) {
                    current = current.getRight();
                } 
                else {
                    current = current.getLeft();}
                if (current != null) {
                } 
                else {
                    return false;
                }
            }
            return true; 
        }
        else {
            return false;
        }  
    }
    
    public students findNode(int id) {
        
        if (!isEmpty()) {
            
            students current = root;
            while (current.getID() != id) {
                if (id >= current.getID()) {
                    current = current.getRight();
                } 
                else {
                    current = current.getLeft();
                }
                if (current != null) {
                } 
                else {
                    return null;
                }
            }
            return current;
        }
        else {
            return null;
        }
    }
    
    public students findNodeName(String name) {
        
        if (!isEmpty()) {
            
            students current = root;
            String Name = current.getFirstName() + " " + current.getLastName();

            while (!name.equals(Name)) {
                if (name.compareTo(Name) >= 0) {
                    current = current.getRight();
                } 
                else {  
                    current = current.getLeft();
                }
                if (current != null) {
                } 
                else {
                    return null;
                } 
                Name = current.getFirstName() + " " + current.getLastName();
            }
            
            return current;    
        }
        else {
            return null;
        }  
    }
    public void printRecords(PrintWriter output) {
        printRecords(root, output);  
    }
    
    private void printRecords(students root, PrintWriter output) {
        
        if (root == null) {
        } 
        else {
            String fullName = root.getFirstName() + " " + root.getLastName();
            
            String year = "";
            
            switch (root.getLevel()) {
                case 1:
                    year = "1st Year";
                    break;
                case 2:
                    year = "2nd Year";
                    break;
                case 3:
                    year = "3rd Year";
                    break;
                default:
                    year = root.getLevel() + "th Year";
                    break;
            }
            // left - root - right 
            printRecords(root.getLeft(), output);
            output.printf("        %-14d%-24s%-7d%-14s%.2f\n", root.getID(), fullName, root.getAge(), year, root.getGpa());
            printRecords(root.getRight(), output); 
        }  
    }
    
    public void insert(int ID, String firstName, String lastName, String email, int age, int phone) {
        root = insert(root, ID, firstName, lastName, email, age, phone);
    }
    
    private students insert(students root, int ID, String firstName, String lastName, String email, int age, int phone) {
        
        if (root == null) {
            root = new students(ID, firstName, lastName, email, age, phone);
        }
        else {
            if (ID < root.getID())
                root.setLeft(insert(root.getLeft(), ID, firstName, lastName, email, age, phone));
            else
                root.setRight(insert(root.getRight(), ID, firstName, lastName, email, age, phone));
        }
        return root;
    }
    
    private students minNode(students root) {
        
        if (root != null) {
        } 
        else {
            return null;
        }
        if (root.getLeft() != null) {
            return minNode(root.getLeft());
        } 
        else {
            return root;
        }
    }
    
    public students parent(students student) {
        return parent(root, student);
    }
    
    private students parent(students root, students student) {
        
        if (root != null && root != student) {
        } 
        else {
            return null;
        }
        
        if (root.getLeft() != student && root.getRight() != student) {
        } 
        else {
            return root;
        }
        
        if (student.getID() >= root.getID()){
            if (student.getID() <= root.getID()) {
                return null;
            } 
            else {
                return parent(root.getRight(), student);
            }
        }
        else {
            return parent(root.getLeft(), student);
        }
        
    }
    
    private boolean isLeaf(students student) {
        return (student.getLeft() == null && student.getRight() == null);
    }
    
    private boolean hasOnlyLeftChild(students student) {
        return (student.getLeft() != null && student.getRight() == null);
    }
    
    private boolean hasOnlyRightChild(students student) {
        return (student.getLeft() == null && student.getRight() != null);
    }
    
    public void delete(String name) {
        root = delete(root, name);
    }
    
    private students delete(students root, String name) {
        students node2delete;
        
        students newnode2delete, parent;
        node2delete = findNodeName(name);
        if (node2delete != null) {
        } 
        else {
            return root;
        }
        parent = parent(root, node2delete);
        if (isLeaf(node2delete)) {
            if (parent != null) {
            } 
            else {
                return null;
            }
            if (name.compareTo(parent.getFirstName() + " " + parent.getLastName()) >= 0) {
                parent.setRight(null);
            } 
            else {
                parent.setLeft(null);
            }
            return root;  
        }
        
        if (hasOnlyLeftChild(node2delete)) {
            if (parent == null) {
                return node2delete.getLeft();
            }
            if (name.compareTo(parent.getFirstName()+" "+parent.getLastName()) >= 0) {
                parent.setRight(parent.getRight().getLeft());
            } 
            else {
                parent.setLeft(parent.getLeft().getLeft());
            }
            return root;  
        }
        if (hasOnlyRightChild(node2delete)) {      
            if (parent != null) {
            } else {
                return node2delete.getRight();
            }
            if (name.compareTo(parent.getFirstName()+" "+parent.getLastName()) < 0) {
                parent.setLeft(parent.getLeft().getRight());
            } else
            {
                parent.setRight(parent.getRight().getRight());
            }
            
            return root;  
        }
        
        
        newnode2delete = minNode(node2delete.getRight()); 
        String Fname, LName, email;
        int ID = newnode2delete.getID();
        Fname = newnode2delete.getFirstName();
        LName = newnode2delete.getLastName();
        email = newnode2delete.getEmail();
        
        int age, phone, level;
        age = newnode2delete.getAge();
        phone = newnode2delete.getPhone();
        level = newnode2delete.getLevel();
        
        courses courses = newnode2delete.getCourses();
        double gpa;
        gpa = newnode2delete.getGpa(); 
        root = delete(root, (Fname + " " + LName));

        node2delete.setID(ID);
        node2delete.setFirstName(Fname);
        node2delete.setLastName(LName);
        node2delete.setEmail(email);
        node2delete.setAge(age);
        node2delete.setPhone(phone);
        node2delete.setLevel(level);
        node2delete.setCourses(courses);
        node2delete.setGpa(gpa);

        return root;   
    }  
    
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////    

    @Override
    public String toString() {
        return "KAUdbmsBST {" + "root=" + root + '}';
    }    
}