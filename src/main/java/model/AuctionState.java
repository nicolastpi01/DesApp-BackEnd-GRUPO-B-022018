package model;

abstract class AuctionState {
	
	public Boolean isNew() {
		return false;
	}
	
	public Boolean isInProgress() {
		return false;
	}
	
	public Boolean isFinished() {
		return false;
	}
	
}
