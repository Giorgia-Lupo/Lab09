package it.polito.tdp.borders.model;

public class Country {
	
	private int CCode;
	private String StateAbb;
	private String StateNme;

	public Country(int cCode, String stateAbb, String stateNme) {
		super();
		CCode = cCode;
		StateAbb = stateAbb;
		StateNme = stateNme;
	}

	public int getCCode() {
		return CCode;
	}

	public void setCCode(int cCode) {
		CCode = cCode;
	}

	public String getStateAbb() {
		return StateAbb;
	}

	public void setStateAbb(String stateAbb) {
		StateAbb = stateAbb;
	}

	public String getStateNme() {
		return StateNme;
	}

	public void setStateNme(String stateNme) {
		StateNme = stateNme;
	}

	@Override
	public String toString() {
		return "Country [CCode=" + CCode + ", StateAbb=" + StateAbb + ", StateNme=" + StateNme + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CCode;
		result = prime * result + ((StateAbb == null) ? 0 : StateAbb.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (CCode != other.CCode)
			return false;
		if (StateAbb == null) {
			if (other.StateAbb != null)
				return false;
		} else if (!StateAbb.equals(other.StateAbb))
			return false;
		return true;
	}
	
	

}
