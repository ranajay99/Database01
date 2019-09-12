//package assignment1;
import java.io.*;
import java.util.Iterator;

public class Assignment1 
{

	static Linkedlist<Hostel> allHostels=new Linkedlist<Hostel>();
	static Linkedlist<Department> allDepartments=new Linkedlist<Department>();
	static Linkedlist<Course> allCourses=new Linkedlist<Course>();
	static Linkedlist<Student> allStudents=new Linkedlist<Student>();
	static File f1,f2,f3;
	
	public static void main(String args[])throws IOException
	{
		f1=new File(args[0]);//student record file
		f2=new File(args[1]);//course file
		f3=new File(args[2]);//student query file
		
		getData();
		answerQueries();
		
	}
	private static void getData() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(f1)); 
		String ss;
		while((ss=br.readLine())!=null)
		{
			String h[]=new String[4];
			//ss gives the whole String entryno_name_dept_hostel
			for(int i=0;i<4;i++)
			{
				ss=ss.trim();
				ss=ss+" ";
				int x=ss.indexOf(' ');
				String s1=ss.substring(0,x);
				s1=s1.trim();
				h[i]=s1;
				ss=ss.substring(x+1);
			}
			Student s=new Student(h[1],h[0],h[3],h[2]);
			allStudents.add(s);
			
		}
		br.close();
		
		BufferedReader brr=new BufferedReader(new FileReader(f2));
		while((ss=brr.readLine())!=null)
		{
			//ss gives the whole String entryno_course_grade_coursename
			String h[]=new String[4];
			for(int i=0;i<3;i++)
			{
				ss=ss.trim();
				ss=ss+" ";
				int x=ss.indexOf(' ');
				String s1=ss.substring(0,x);
				s1=s1.trim();
				h[i]=s1;
				ss=ss.substring(x+1);
			}
			ss=ss.trim();
			h[3]=ss;
			
			Iterator<Position_<Student>> it = allStudents.positions();
			Position_<Student> p=it.next();
			while(p!=null)
			{
				if(p.value().entryNo().equals(h[0]))
				{
					p.value().addcourse(h[3],h[1],h[2]);
					break;
				}
				if(p.after()==null)
					break;
				p=it.next();
				
			}
			
		}
		brr.close();
		
		////////////////////////////////////////adding to the lists
		//deriving from a particular student
		Iterator<Position_<Student>> it = allStudents.positions();
		Position_<Student> p=it.next();

		while(p!=null)
		{
			Student s=p.value();
			
			if(allHostels.count()==0)
				allHostels.add(new Hostel(s.hostel()));
			else
			{
				Iterator<Position_<Hostel>> i1=allHostels.positions();
				Position_<Hostel> h=i1.next();
				while(h!=null)
				{
					if(h.value().name().equals(s.hostel()))
						break;
						
					if(h.after()==null)
					{
						Hostel hh=new Hostel(s.hostel());
						allHostels.add(hh);
						break;
					}
					
					h=i1.next();
				}
			}
			
			if(allDepartments.count()==0)
				allDepartments.add(new Department(s.department()));
			else
			{
				Iterator<Position_<Department>> i2=allDepartments.positions();
				Position_<Department> d=i2.next();
				while(d!=null)
				{
					if(d.value().name().equals(s.department()))
						break;
					
					if(d.after()==null)
					{
						Department dd=new Department(s.department());
						allDepartments.add(dd);
						break;
					}
					
					d=i2.next();
				}
			}
			if(s.courses.count()!=0)
			{
				if(allCourses.count()==0)
				{
					
					Iterator<CourseGrade_> ff=s.courseList();
					CourseGrade_ sg=ff.next();
					
					while(sg!=null)
					{
						allCourses.add(new Course(sg.coursetitle(),sg.coursenum()));
						if(ff.hasNext()==false)
							break;
						sg=ff.next();
					}
				}
				else
				{
					Iterator<CourseGrade_> ff=s.courseList();
					CourseGrade_ sg=ff.next();
					while(sg!=null)
					{			
						Iterator<Position_<Course>> i3=allCourses.positions();
						Position_<Course> c=i3.next();
						while(c!=null)
						{
							if(sg.coursetitle().equals(c.value().name()))
								break;						
							if(c.after()==null)
							{
								allCourses.add(new Course(sg.coursetitle(),sg.coursenum()));
								break;
							}
							c=i3.next();
						}
						if(ff.hasNext()==false)
							break;
						sg=ff.next();
					}
				}
			}
			if(p.after()==null)
				break;
			p=it.next();
		}
		
		/////////////////////////////////////////adding students to the lists
		
		Iterator<Position_<Student>> it2 = allStudents.positions();
		Position_<Student> pp=it2.next();
		
		while(pp!=null)
		{
			Student s=pp.value();
			
			//add students to these lists
			Iterator<Position_<Hostel>> i1=allHostels.positions();
			Position_<Hostel> h=i1.next();
			while(h!=null)
			{
				h.value().addstudent(s);
					
				if(h.after()==null)
					break;
				
				h=i1.next();
			}

			Iterator<Position_<Department>> i2=allDepartments.positions();
			Position_<Department> d=i2.next();
			while(d!=null)
			{
				d.value().addstudent(s);
				
				if(d.after()==null)
					break;
				
				d=i2.next();
			}

			Iterator<Position_<Course>> i3=allCourses.positions();
			Position_<Course> c=i3.next();
			while(c!=null)
			{
				if(s.courses.count()==0)
					break;
				c.value().addstudent(s);
					
				if(c.after()==null)
					break;
				c=i3.next();
			}
			
			if(pp.after()==null)
				break;
			pp=it2.next();
		}
		/*		
		//////////////////////////displaying
		
		Iterator<Position_<Student>> is = allStudents.positions();
		Position_<Student> ps=is.next();
		while(ps!=null)
		{
			Student s=ps.value();
			System.out.println(s.name+" "+s.entry+" "+s.dept+" "+s.hostel);
			s.dispcourse();
			System.out.println();
			if(ps.after()==null)
				break;
			ps=is.next();
							
		}
		
		Iterator<Position_<Hostel>> i1=allHostels.positions();
		Position_<Hostel> h=i1.next();
		while(h!=null)
		{
			System.out.print(h.value().name()+" ");
			h.value().disp();
			
			if(h.after()==null)
			{
				break;
			}
			
			h=i1.next();
		}
		Iterator<Position_<Department>> i2=allDepartments.positions();
		Position_<Department> d=i2.next();
		while(d!=null)
		{
			System.out.print(d.value().name()+" ");
			d.value().disp();
			
			if(d.after()==null)
			{
				break;
			}
			
			d=i2.next();
		}
		Iterator<Position_<Course>> i3=allCourses.positions();
		Position_<Course> c=i3.next();
		while(c!=null)
		{
			System.out.print(c.value().name());
			c.value().disp();
				
			if(c.after()==null)
			{
				break;
			}
			c=i3.next();
		}
		
		/////////////////////////////////////////
		*/
	}
	
	private static void answerQueries() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(f3)); 
		String ss;
		Linkedlist<String> ls=new Linkedlist<String>();
		
		while((ss=br.readLine())!=null)
		{
			ls.add(ss);
			//ss gives the whole String querytype_entryno_somequeryrelateddata
		}
		br.close();

		while(ls.head!=null)
		{
			ss=ls.remove();
			
			int x=ss.indexOf(' ');
			String s1=ss.substring(0,x);
			ss=ss.substring(x+1);
			ss=ss.trim();
			
			if(s1.equals("SHARE"))
			{
				x=ss.indexOf(' ');
				s1=ss.substring(0,x);
				ss=ss.substring(x+1);
				ss=ss.trim();
				

				Iterator<Position_<Student>> it = allStudents.positions();
				Position_<Student> p=it.next();
				
				Student s=null;
				while(p!=null)
				{
					s=p.value();
					if(s.entryNo().equals(s1))
						break;
					
					if(p.after()==null)
						break;
					p=it.next();
				}
				
				if(s.hostel().equals(ss))
				{
					Iterator<Position_<Hostel>> i1=allHostels.positions();
					Position_<Hostel> h=i1.next();
					while(h!=null)
					{
						if(h.value().name().equals(ss))
							break;
							
						if(h.after()==null)
						{
							break;
						}
						
						h=i1.next();
					}
					//disp hostel students
					h.value().dispstudents(s);
				}
				else if(s.department().equals(ss))
				{
					Iterator<Position_<Department>> i2=allDepartments.positions();
					Position_<Department> d=i2.next();
					while(d!=null)
					{
						if(d.value().name().equals(ss))
							break;
						if(d.after()==null)
							break;
						
						d=i2.next();
					}
					//disp department students
					d.value().dispstudents(s);
				}
				else//course
				{
					Iterator<Position_<Course>> i3=allCourses.positions();
					Position_<Course> c=i3.next();
					while(c!=null)
					{
						if(c.value().number().equals(ss))
							break;
							
						if(c.after()==null)
							break;
						
						c=i3.next();
					}
					//disp course students
					c.value().dispstudents(s);
				}
				
				
				System.out.println();				
				
			}
			else if(s1.equals("COURSETITLE"))
			{
				Iterator<Position_<Course>> it = allCourses.positions();
				Position_<Course> p=it.next();
				Course c=null;
				while(p!=null)
				{
					c=p.value();
					if(c.number().equals(ss))
						break;
					
					if(p.after()==null)
						break;
					p=it.next();
				}
				System.out.println(c.name());
			}
			else if(s1.equals("INFO"))
			{

				Iterator<Position_<Student>> it = allStudents.positions();
				Position_<Student> p=it.next();
				Student s=null;
				while(p!=null)
				{
					s=p.value();
					if(s.entryNo().equals(ss) || s.name().equals(ss))
						break;
					if(p.after()==null)
						break;
					p=it.next();
				}
				System.out.print(s.entryNo()+" "+s.name()+" "+s.department()+" "+s.hostel()+" "+s.cgpa()+" ");
				//sort lexicographically
				s.dispcourse();
				
				System.out.println();
			}
			
		}
	}
	

}
