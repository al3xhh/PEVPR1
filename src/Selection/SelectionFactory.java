package Selection;

public class SelectionFactory {
	public static SelectionAlgorithm getSelectionAlgorithm(String algorithm, int participantes) {
		if (algorithm.equals("Roulette"))
			return new Roulette();
		else if (algorithm.equals("Tournament"))
			return new Tournament();
		else
			return new Roulette();
	}
}
