//package assignment1;
import java.util.Iterator;

public class Node<T> implements Position_<T>//,Iterator<T>
{
	//Node holds type T data
	T data;
	Node<T> next;
	Node(T data)
	{
		this.data=data;
		next=null;
	}
	public T value()
	{
		return this.data;
	}
	public Position_<T> after()
	{
		return this.next;
	}
}

class Linkedlist<T> implements LinkedList_<T>
{
	//head points to first element of the Linkedlist
	Node<T> head;
	Node<T> tail;
	int c=0;
	
	Linkedlist()
	{
		head=null;
		tail=null;
	}
	public Position_<T> add(T element)
	{
		Node<T> node=new Node<T>(element);
		
		if(head==null)
		{
			c++;
			head=node;
			tail=node;
			return head;
		}

		tail.next=node;
		tail=tail.next;
		c++;
		return tail;
		
	}
	public T remove()
	{
		Node<T> start=head;
		if(head==tail)
		{
			T temp=head.value();
			head=null;
			tail=null;
			c--;
			return temp;
		}
		while(start.next!=tail)
			start=start.next;
		T temp=tail.value();
		tail=null;
		tail=start;
		c--;
		return temp;
	}
	public Iterator<Position_<T>> positions()
	{
		IT<T> n=new IT<T>(head);
		return n;
	}
	public int count()
	{
		return c;
	}
}