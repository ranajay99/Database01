//package assignment1;

public class Grade implements GradeInfo_
{
	String g;
	LetterGrade l;
	
	Grade(String g)
	{
		this.g=g;
		l=LetterGrade.valueOf(g);
	}
	public int gradep()
	{
		return GradeInfo_.gradepoint(l);
	}
	public String gradel()
	{
		return g;
	}
	
}
