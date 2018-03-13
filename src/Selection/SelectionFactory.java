package Selection;

public class SelectionFactory {
	public static SelectionAlgorithm getSelectionAlgorithm(String algorithm, double truncation) {
		if (algorithm.equals("Roulette"))
			return new Roulette();
		else if (algorithm.equals("Tournament"))
			return new Tournament();
		else if (algorithm.equals("Universal estochastic"))
			return new UniversalStochastic();
		else if (algorithm.endsWith("Truncation"))
			return new Truncation(truncation);
		else
			return new Roulette();
	}
}
