package myproject.javafxproject.model.clinic;
/**
An enum class describing the specialty of the providers and the charge that they demand
 * @author Renil Khristi, Christian Roa
 */
public enum Specialty {
    FAMILY(250), PEDIATRICIAN(300), ALLERGIST(350);
    private final int charge;

    /*
    Gets the county 
    @param charge the amount the provider charges
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /*
    Gets the charge
    @return the charge
     */
    public int getCharge() {
    	return charge;
    }
    
}
