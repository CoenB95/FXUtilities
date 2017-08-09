package com.cbapps.javafx.utilities.types;

/**Utility class for converting types.*/
public class Types {
	
	/**Converts Java's signed bytes to unsigned bytes (integers).
	 * @param value the input to be converted.
	 * @return the converted result as a integer.
	 */
	public static int toUnsignedBytes(byte value) {
		if(value < 0) return 256 + value;
		else return value;
	}
	
	/**Converts unsigned bytes (wrapped as integers) to Java's
	 * signed bytes (= simply cast).
	 * @param inputBuffer the input to be converted.
	 * @return the converted result in a byte array.
	 */
	public static byte[] toSignedBytes(int... inputBuffer) {
		byte[] outputBuffer = new byte[inputBuffer.length];
		for(int i = 0; i < inputBuffer.length; i++){
            outputBuffer[i] = (byte)inputBuffer[i];
        }
		return outputBuffer;
	}
}
