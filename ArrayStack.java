import java.util.EmptyStackException;
import java.util.Scanner;

public class ArrayStack<E> {
	private E s[];
	private int top;
	
	public ArrayStack() {
		s = (E[]) new Object[1];
		top = -1;
	}
	public int size() {
		return top+1;
	}
	public boolean isEmpty() {
		return (top == -1);
	}
	
	//peek
	public E peek() {
		if (isEmpty()) throw new EmptyStackException();
		return s[top];
	}
	//push
	public void push(E newItem) {
		if (size() == s.length)
			resize(2*s.length);
		s[++top] = newItem;
	}
	//pop
	public E pop() {
		if(isEmpty()) throw new EmptyStackException();
		E item = s[top];
		s[top--] = null;
		if (size()>0 && size()==s.length/4)
			resize(s.length/2);
		return item;
	}
	//resize
	private void resize(int newSize) {
		Object[] t = new Object[newSize];
		for (int i=0; i<size(); i++)
			t[i] = s[i];
		s = (E[]) t;
	}
	//�Է¹ޱ�
	public static void main(String[] args) {
		ArrayStack<String> stack = new ArrayStack<String>();
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String[] input = a.split("");
		
		for(String str : input) {
			//���� ��ȣ push�� �ֱ�
			if(str.equals("(") || str.equals("{") || str.equals("["))
				stack.push(str);
			//")" ��ȣ�̰�, ()�� ������ pop
			else if(str.equals(")") && !stack.isEmpty() && stack.peek().equals("("))
				stack.pop();
			//"}" ��ȣ�̰�, {}�� ������ pop
			else if(str.equals("}") && !stack.isEmpty() && stack.peek().equals("{"))
				stack.pop();
			//"]" ��ȣ�̰�, []�� ������ pop
			else if(str.equals("]") && !stack.isEmpty() && stack.peek().equals("["))
				stack.pop();
			//¦�� ���� ���� ���� ��ȣ push�� �ֱ�
			else if(str.equals(")") || str.equals("}") || str.equals("]"))
				stack.push(str);
		}	
		
		if(stack.isEmpty())
			System.out.println("Yes");
		else
			System.out.println("No");
	}
}
