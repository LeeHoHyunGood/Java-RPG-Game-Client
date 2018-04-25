package mrhi.adventure.model.game;

public interface Observee {
	public void addObserver(Observer obs);
	public void removeObserver(Observer obs);
	public void notifyObservers();
}
