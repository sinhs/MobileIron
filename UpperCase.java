package MobileIron;


public class UpperCase implements IType {
	
	@Override
	public String processTLV(String value) {
		// TODO Auto-generated method stub
		return value.toUpperCase();
	}
}
