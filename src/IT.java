//package assignment1;
import java.util.Iterator;

public class IT<T> implements Iterator<Position_<T>>
{
	//Position_/Node type object
	Position_<T> ob;
	IT(Node<T> ob)
	{
		this.ob=ob;
	}
	public boolean hasNext()
	{
		if(ob.after()==null)
			 return false;
		return true;
	}
	public Position_<T> next()
	{
			Position_<T> temp=ob;
			if(hasNext())
				ob=ob.after();
			else
				ob=null;
			return temp;
	}
}
