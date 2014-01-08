package it.unical.uniexam;

/**
 * 
 * @author luigi
 *
 */
public class MokException {

	Boolean DEBUG=true;
	Exception error;

	public MokException(Exception e) {
		error=e;
		if(DEBUG)
			printError();
	}

	private void printError() {
		error.printStackTrace();
	}

	
	
	
}