package _220804_corona.virus;

import java.util.Arrays;

public class VirusMgrImpl implements VirusMgr {
	private Virus[] virus;
	private int index;

	public VirusMgrImpl() {
		virus = new Virus[100];
	}

	@Override
	public void add(Virus v) {
		try {
			search(v.getName());
		} catch (NotFoundException e) {
			virus[index++] = v;
		}
	}

	@Override
	public Virus[] search() {
		return Arrays.copyOf(virus, index);
	}

	@Override
	public Virus search(String name) throws NotFoundException {
		for (int i = 0; i < index; i++) {
			if (virus[i].getName().equals(name))
				return virus[i];
		}
		throw new NotFoundException(name + ": 미등록 바이러스입니다.");
	}
}
