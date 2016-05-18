package com.cbapps.javafx.utilities.types;

/**Utility class for converting types.*/
public class Types {

	/**Converts Java's signed bytes to unsigned bytes (integers).
	 * @param inputBuffer the input to be converted.
	 * @return the converted result as a integer array.
	 */
	public static int[] toUnsignedBytes(byte... inputBuffer) {
        int[] outputBuffer = new int[inputBuffer.length];
        for(int i = 0; i < inputBuffer.length; i++){
            if(inputBuffer[i] < 0){
                outputBuffer[i] = 256 + inputBuffer[i];
            }
            else {
                outputBuffer[i] = inputBuffer[i];
            }
        }
        return outputBuffer;
	}
	
	/**Converts unsigned bytes (wrapped as integers) to Java's
	 * signed bytes.
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
