package interview.hoju;

public class H_Base implements Comparable<H_Base>{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public H_Base(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "이름 : "+name+"\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof H_Base)&&((H_Base) obj).getName().equals(name);
	}

	@Override
	public int compareTo(H_Base o) {
		if(name.equals(o.getName())){
			return 0;
		}
		return Integer.parseInt(name.substring(2))-Integer.parseInt(o.getName().substring(2));
	}
	
	
	
	

}
