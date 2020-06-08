package design_patterns.behavioral;

 abstract class Exam {  
      
    abstract void start();  
    abstract void end();  
    abstract void result();
   
    public final void process(){    
       start();  
 
       end();  
       
       result();
    }  
}
 class MathExam extends Exam{
	void start(){
		System.out.println("Math Exam started");
	}
	void end() {
		System.out.println("Math Exam started");
	}
void result() {
	System.out.println("Math Exam results have been declared");
}
}
public class PatternTemplate {

	public static void main(String[] args) {
		MathExam m=new MathExam();
		m.start();
		m.end();
		m.result();

	}

}
