package application;

public class Pointer {
	private double xp;
	private double yp;

	public void setXP(double xp) {
		this.xp=xp;
	}
	public void setYP(double yp) {
		this.yp=yp;
	}

	public double getXP(){
		return xp;
	}
	public double getYP(){
		return yp;
	}
/*
boolean containsObjectOfType(double xr, double yr){
	 for (int i=0; i<o.length();i++){
	  if (shelf.get(i).getClass().equals(o.getClass())){
	   return true;
	  }
	 }
	return false;
	}
	*/
}