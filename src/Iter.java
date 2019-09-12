//package assignment1;
import java.util.Iterator;

public class Iter implements Iterator<CourseGrade_> 
{
	Position_<CourseG> node;
	int c=0;
	Iter(Node<CourseG> node)
	{
		this.node=node;
	}
	public boolean hasNext()
	{
		if(node.after()==null)
			return false;
		return true;
	}
	public CourseGrade_ next()
	{
		if(c==0)
		{
			c++;
			return (CourseGrade_) node.value();
		}
		node=node.after();
		return (CourseGrade_) node.value();
		//node.value is CourseG type
		
	}
}
