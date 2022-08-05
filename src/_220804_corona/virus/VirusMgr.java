package _220804_corona.virus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface VirusMgr {
	void add(Virus v) throws DuplicatedException;
	List<Virus> search();
	Virus search(String name) throws NotFoundException;
	Virus search(Virus v) throws NotFoundException;
	void save();
	void load();
}