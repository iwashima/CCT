package Lab6;

public class Lab6 {

	/*** CAR ***/
	public static void main(String[] args) {

		new Lab6();
		
	}
	
	/*** Method that starts the engine of the car ***/
	public void startCar() {
		
		System.out.println("Starting the engine .... ");
		
	}
	
	/*** Method that sets the temperature of the AC 
	 * 
	 * @param temp temperature that the AC should be set at
	 */
	public void setAC( int temp ) {
		
		System.out.println("Setting AC Temperatura at ... " + temp);
		
	}
	
	/*** Method that turns on the Radio on a station
	 * 
	 *  @param station Radio station to set the radio
	 */
	public void turnOnRadio( double station ) { 
		
		System.out.println("Turn on Radio ... Station ... " + station );
		
	}
	
	/*** Method that turn of the indicator light 
	 * 
	 * @param side left/right/both side that the indicator should be turned on
	 *
	 */
	public void indicator(String side) { 
		
		if ( side.equals("left") || side.equals("right") || side.equals("both") ) {
			
			System.out.println("Turn on indicator on " + side + " side(s)");
			
		}
	}
	
	/*** Method that checks the level of the fuel 
	 * 
	 * @return level level of the fuel (litter) that is remain in the car
	 * 
	 */
	public double levelOfFuel() { 
		
		System.out.println("Level of fuel !!!");
		
		return 5.5;
	}
	
	/*** Method to turn off the engine ***/
	public void stopCar() { 
		
		System.out.println("Stop Engine .... ");
		
	}
}
