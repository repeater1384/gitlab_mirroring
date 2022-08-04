package _220804_corona.virus;

public class NotFoundException extends Exception{
	public NotFoundException(String msg) {
		super(msg);
	}
	public NotFoundException() {
		this("NotFound예외");
	}
}
