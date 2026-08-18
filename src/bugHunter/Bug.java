package bugHunter;

public class Bug {
	private final String name;
	private String diagnosis;
	public Bug(String name, String diagnosis) {
	    this.name = name;
	    this.diagnosis = diagnosis;
	}
	public String getName() {
	    return name;
	}

	public String getDiagnosis() {
	    return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
	    this.diagnosis = diagnosis;
	}
	@Override
	public String toString() {
	    return name + ": " + diagnosis;
	}
}
