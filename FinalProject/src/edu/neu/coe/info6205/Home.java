package edu.neu.coe.info6205;

public class Home {
    
	private int id;
	private int x;
    private int y;
    private int isOneWayTo;
    
    
    public Home(int id, int x, int y){
        this.x = x;
        this.y = y;
        this.id = id;
        this.isOneWayTo = isOneWayTo;
    }
    
    public int getX() {
		return x;
	}
   
	public int getY() {
		return y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsOneWayTo() {
		return isOneWayTo;
	}

	public void setIsOneWayTo(int isOneWayTo) {
		this.isOneWayTo = isOneWayTo;
	}

	// Gets the distance to given city
    public double distanceTo(Home home){
        int xDistance = Math.abs(getX() - home.getX());
        int yDistance = Math.abs(getY() - home.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
    
    @Override
    public String toString(){
        return "(" + getX()+", "+getY() + ")";
    }
}