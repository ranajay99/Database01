//package assignment1;

public class CourseG implements CourseGrade_ 
{
	String title,code,g;
	CourseG(String title,String code,String g)
	{
		this.title=title;
		this.code=code;
		this.g=g;
		
	}
	public String coursetitle()
	{
		return title;
	}
	public String coursenum()
	{
		return code;
	}
	public GradeInfo_ grade()
	{
		Grade gg=new Grade(g);
		return gg;
	}

} 