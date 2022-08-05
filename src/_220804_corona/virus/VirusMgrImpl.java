package _220804_corona.virus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class VirusMgrImpl implements VirusMgr {
	private List<Virus> virus = null;
	public static VirusMgr instance;
	
	private VirusMgrImpl() {
		load();
		if (virus == null)
			virus = new ArrayList<>();
	}
	
	public static VirusMgr get_instance() {
		if(instance == null) {
			return instance = new VirusMgrImpl();
		}
		return instance;
	}

	@Override
	public void add(Virus v) throws DuplicatedException {
		try {
			search(v.getName());
			throw new DuplicatedException(v.getName() + " : 이미 존재합니다.");
		} catch (NotFoundException e) {
			virus.add(v);
		}
	}

	@Override
	public Virus search(String name) throws NotFoundException {
		for (int i = 0; i < virus.size(); i++) {
			if (virus.get(i).getName().equals(name))
				return virus.get(i);
		}
		throw new NotFoundException(name + ": 미등록 바이러스입니다.");
	}

	@Override
	public List<Virus> search() {
		// TODO Auto-generated method stub
		return virus;
	}

	@Override
	public Virus search(Virus v) throws NotFoundException {
		Virus result = null;
		for (int i = 0; i < virus.size(); i++) {

			if (virus.get(i).equals(v)) {
				result = v;
				return v;
			}
		}

		throw new NotFoundException(v.getName() + ": 미등록 바이러스입니다.");
	}

	/**
	 * virus.dat 파일에 데이터를 저장
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Override
	public void save() {
		// TODO Auto-generated method stub
		File f = new File("virus.dat");
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(virus);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** virus.data 파일에 있는 데이터를 읽어 옴 */
	@Override
	public void load() {
		// TODO Auto-generated method stub
		File f = new File("virus.dat");
		if (!f.exists())
			return;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			virus = (List<Virus>) ois.readObject();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
