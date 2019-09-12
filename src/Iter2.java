//package assignment1;
import java.util.Iterator;

public class Iter2 implements Iterator<Student_> 
{
	Position_<Student> node;
	int c=0;
	Iter2(Node<Student> node)
	{
		this.node=node;
	}
	public boolean hasNext()
	{
		if(node.after()==null)
			return false;
		return true;
	}
	public Student_ next()
	{
		if(c==0)
		{
			c++;
			return (Student_)node.value();
		}
		node=node.after();
		return (Student_)node.value();
			//node.value is CourseG type
		
	}
}
