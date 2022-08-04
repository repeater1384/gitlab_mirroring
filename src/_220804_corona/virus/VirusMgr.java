package _220804_corona.virus;

public interface VirusMgr {
	void add(Virus v) throws DuplicatedException;
	Virus[] search();
	Virus search(String name) throws NotFoundException;
}