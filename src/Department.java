//package assignment1;

import java.util.Iterator;

public class Department implements Entity_
{
	String dept;
	Linkedlist<Student> students=new Linkedlist<Student>();
	Student_ arr[];
	int totalc=0;
	
	Department(String dept)
	{
		this.dept=dept;
	}
	public String name()
	{
		return dept;
	}
	public void addstudent(Student s)
	{
		if(s.department().equals(dept))
		{
			students.add(s);
			totalc++;
		}
	}
	public Iterator<Student_> studentList()
	{
		Iterator<Student_> sd=new Iter2(students.head);
		return sd;		
	}
	/*
	public void disp()
	{
		System.out.println();
		Iterator<Position_<Student>> is = students.positions();
		Position_<Student> ps=is.next();
		while(ps!=null)
		{
			Student s=ps.value();
			System.out.println(s.name);
			if(ps.after()==null)
				break;
			ps=is.next();
							
		}
		System.out.println();
	}
	*/
	public void dispstudents(Student s)
	{
		Iterator<Student_> ccc=this.studentList();
		Student_ cn=ccc.next();
		arr=new Student_[totalc-1];
		int index=0;
		
		while(cn!=null)
		{
			if(cn.equals(s))
			{
				if(ccc.hasNext()==false)
					break;
				cn=ccc.next();
				continue;
			}
			insert(index,cn);
			index++;
			
			if(ccc.hasNext()==false)
				break;
			cn=ccc.next();
		}
		for(int i=0;i<totalc-1;i++)
		{
			System.out.print(arr[i].entryNo());
			if(i==totalc-2)
				break;
			System.out.print(" ");
		}
	}
	public void insert(int index,Student_ cn)
	{
		arr[index]=cn;
		for(int i=index;i>0;i--)
		{
			if(cn.entryNo().compareTo(arr[i-1].entryNo())<0)
			{
				arr[i]=arr[i-1];
				arr[i-1]=cn;
			}
		}
	}



}
