package MobileIron;

public class TypeInstance {

	final static String upper = "UPPRCS";
	final static String replace = "REPLCE";

	public  IType getTypeInstance(String type) {
		switch (type) {
		case upper:
			return new UpperCase();
		case replace:
			return new Replace();
		}
		return null;
	}
}
