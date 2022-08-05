package _220804_corona.virus;

import java.io.Serializable;

public class Virus extends Object implements Serializable,Comparable<Virus>{
	private String name;
	private int level;

	public Virus() {
	}

	public Virus(String name, int level) {
		setName(name);
		setLevel(level);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append("\t").append(getLevel());
		return sb.toString();
	}

	
	//다시 해보기
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Virus other = (Virus) obj;
		if (level != other.level)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Virus o) {
		// TODO Auto-generated method stub
		return this.level - o.level;
	}
}
