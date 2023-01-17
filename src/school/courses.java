package school;

public class courses {
    
    private course head;

    public courses() {
    }

    public courses(course head) {
        this.head = head;
    }
    
    public course getHead() {
        return head;
    }

    public void setHead(course head) {
        this.head = head;
    }
    
    public void insert(String id, int grade) {
        
        if (head != null && head.getId().compareTo(id) <= 0) {
            course helpPtr = head;
            while (helpPtr.getNext() != null) {
                
                if (helpPtr.getNext().getId().compareTo(id) <= 0) {
                } 
                else {
                    break;
                }
                helpPtr = helpPtr.getNext();
            }
            helpPtr.setNext(new course(id, grade, helpPtr.getNext()));
        }
        else {
            head = new course(id, grade, head);
        }
    }
    
    
     public int numberOfNodes() {
         
        int counter = 0;
        course helpPtr = head;
        while (helpPtr != null) {
            ++ counter;
            helpPtr = helpPtr.getNext();
        }
        return counter;
    }
      
     
    public course search(String courseID) {
        
        course helpPtr = head;
        while (helpPtr != null) {
            if (!courseID.equals (helpPtr.getId())) {
            } 
            else {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return helpPtr;
        
    }


    @Override
    public String toString() {
        return "KAUcourses{" + "head=" + head + '}';
    }
   
}
