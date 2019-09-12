//package assignment1;
import java.util.Iterator;

public class Student implements Student_
{
	String name,entry,hostel,dept;
	Linkedlist<CourseG> courses=new Linkedlist<CourseG>();
	int completedc=0;
	int totalc=0;
	int sum=0;
	int currentg=0;
	CourseGrade_ arr[];
	
	Student(String name,String entry,String hostel,String dept)
	{
		this.name=name;
		this.entry=entry;
		this.hostel=hostel;
		this.dept=dept;
	}
	public String hostel()
	{
		return hostel;
	}
	public String name()
	{
		return name;
	}
	public String entryNo()
	{
		return entry;
	}
	public String department()
	{
		return dept;
	}
	public void addcourse(String name,String code,String grade)
	{
		CourseG cc=new CourseG(name,code,grade);
		courses.add(cc);
		
		Grade ggg=(Grade)(cc.grade()); 
		currentg=ggg.gradep();
		//consider E,F?
		if(currentg>0)
			completedc++;
		else
			if(!ggg.gradel().equals("I"))
				completedc++;
		totalc++;
		sum=sum+currentg*3;
	}
	/*
	public boolean checkcourse(String coursenum)
	{
		Iterator<Position_<CourseG>> it=courses.positions();
		Position_<CourseG> f=it.next(); 
		while(f!=null)
		{
			if(f.value().coursenum().equals(coursenum))
				return true;
			if(f.after()==null)
				break;
			f=it.next();
		}
		return false;
	}
	*/
	public String completedCredits()
	{
		return completedc*3+"";
	}
	public String cgpa()
	{
		if(completedc==0) return "0.00";
		double cg=(double)(sum)/(double)(completedc*3);//each course has 3 credits
		cg+=0.005;
		String cgg=cg+"00";
		cgg=cgg.substring(0,4);
		if(cg>=10) cgg=cgg+"0";
		return cgg;
	}
	public Iterator<CourseGrade_> courseList()
	{
		Iterator<CourseGrade_> ccg=new Iter(courses.head);
		return ccg;
	}
	public void dispcourse()
	{
		if(courses.count()==0)
			return;
		Iterator<CourseGrade_> ccc=this.courseList();
		CourseGrade_ cn=ccc.next();
		arr=new CourseGrade_[totalc];
		int index=0;
		
		while(cn!=null)
		{
			insert(index,cn);
			index++;
			
			if(ccc.hasNext()==false)
				break;
			cn=ccc.next();
		}
		for(int i=0;i<totalc;i++)
		{
			String g=((Grade)(arr[i].grade())).gradel();
			System.out.print(arr[i].coursenum()+" "+g);
			if(i==totalc-1)
				break;
			System.out.print(" ");
		}
	}
	public void insert(int index,CourseGrade_ cn)
	{
		arr[index]=cn;
		for(int i=index;i>0;i--)
		{
			if(cn.coursenum().compareTo(arr[i-1].coursenum())<0)
			{
				arr[i]=arr[i-1];
				arr[i-1]=cn;
			}
		}
	}
	
	
}
