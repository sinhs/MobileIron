package MobileIron;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TLVParser {

	static StringBuilder result = new StringBuilder();

	private static StringBuilder TlvParseOutput(String type, String value) {
		TypeInstance typeInstance = new TypeInstance();
		IType typeInput = typeInstance.getTypeInstance(type);
		if (typeInput != null) {
			result.append(type);
			result.append("-");
			result.append(typeInput.processTLV(value));
			return result;
		} else {
			result.append("Type not valid");
			return result;
		}
	}

	private static void TLVParserImpl(String input) {
		int i = 0, length = 0;
		StringBuilder result = new StringBuilder();
		input = input.replaceAll("-", "");
		while (i < input.length()) {
			if (i + 6 > input.length()) {
				i = i + 6;
				break;
			}
			String type = input.substring(i, i + 6);
			i += 6;
			if (i + 4 > input.length()) {
				i = i + 4;
				break;
			}

			String len = input.substring(i, i + 4);
			i += 4;
			try {
				length = Integer.parseInt(len);
			} catch (NumberFormatException ex) {
			}

			if (i + length > input.length()) {
				i = i + length;
				break;
			}

			String value = input.substring(i, i + length);
			i += length;
			result = TlvParseOutput(type, value);

			if (i == input.length()) {
				System.out.print(result);
			} else {
				System.out.println(result);
			}
			result.setLength(0);
		}
	}

	public static void main(String[] args) {

		String inputString = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			inputString = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		TLVParserImpl(inputString);
	}

}
