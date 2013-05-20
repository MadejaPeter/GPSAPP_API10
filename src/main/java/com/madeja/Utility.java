package com.madeja;
 
public class Utility {

	boolean tracking;
	Location loc1;
	Location loc2;

	public Utility() {
		tracking = false;
		loc1 = new Location(430000);
		
		loc2 = new Location(210000);
	}

	public String returnAuthor() {
		return "madeja";
	}

	public void enableTracing() {
		tracking = true;
	}
	
	public boolean isTrackingEnabled(){
		return tracking;
	}
	
	public Location getCurrentPosition(){
		return loc1;
	}
	
	public Location getSearchPosition(){
		return loc2;
	}
	
	public Integer distance(Location loc1, Location loc2){
		return loc1.distanceTo(loc2);
	}
	
	public class Location{
		int loc;
		public Location(int pos1){
			loc = pos1;
		}
		
		public Integer getValue(){
			return loc;
		}
		
		public int distanceTo(Location pos1){
			return (int) (Math.hypot(pos1.getValue()/1E6, this.getValue()/1E6)) + 1;
		}
	}

}
