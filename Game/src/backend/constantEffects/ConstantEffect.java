package backend.constantEffects;

public interface ConstantEffect {
	
	/**
	 * Applys the effect. call this at each update of logic.
	 * @return if the effect has ended
	 */
	public boolean apply();

}
