package school;

public class course {
    
    private String id;
    private int grade;
    private int num;
    private course next;

    public course() {
        id = null;
        grade = 0;
        num = 0;
        next = null;
    }
 
    public course(String id, int grade, course next) {
        this.id = id;
        this.grade = grade;
        this.next = next;
    }

    // getters and setter for all the data fields 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public course getNext() {
        return next;
    }

    public void setNext(course next) {
        this.next = next;
    }
    

    @Override
    public String toString() {
        return "KAUcourse{" + "id=" + id + ", grade=" + grade + ", num=" + num + ", next=" + next + '}';
    }
  
}