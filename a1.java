import java.util.Scanner;
class Student{
String Name = "sreeram";
int Rollno = 6664;
public void Student_Display(){
System.out.println(Name);
System.out.println(Rollno);
}
}

class Exam{
Scanner sc = new Scanner(System.in);

public void Exam_Display(){
	System.out.print("Enter First Subject name");
	String s1 = sc.next();
	System.out.print("Enter Second Subject name");
	String s2 = sc.next();
	System.out.println(s1);
	System.out.println(s2);
}


public class a1{
public static void main(String[] args){
Student S = new Student();
Exam E = new Exam();
S.Student_Display();
E.Exam_Display();
}
}
}