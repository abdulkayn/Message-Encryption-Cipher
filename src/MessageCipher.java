
import java.io.*;

/**
 * This class represents a cipher that can encode and decode information using
 * the Western Cipher algorithm.
 * 
 * @author Abdul Khan
 *
 */

public class MessageCipher {
	CircularArrayQueue<Character> encodingQueue;
	CircularArrayQueue<Character> decodingQueue;

	/**
	 * The constructor class initializes two queues, encodingQueue and decodingQueue,
	 * both with a capacity of 10
	 */
	public MessageCipher() {
		encodingQueue = new CircularArrayQueue<Character>(10);
		decodingQueue = new CircularArrayQueue<Character>(10);
	}

	/**
	 * The constructor class initializes two queues, encodingQueue and decodingQueue,
	 * both with the given capacity
	 * 
	 * @param initialCapacity the capacity that will be inputed while initializing
	 *                        the two queues
	 */
	public MessageCipher(int initialCapacity) {
		encodingQueue = new CircularArrayQueue<Character>(initialCapacity);
		decodingQueue = new CircularArrayQueue<Character>(initialCapacity);
	}

	/**
	 * The private help method getIndex finds the index of a given letter by
	 * traversing through an array with the letters of the alphabet as its elements
	 * 
	 * @param letter the letter to find the index of
	 * @return the index of the letter in the alphabet array
	 */
	private int getIndex(Character letter) {
		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		int index = -1;

		for (int i = 0; i < alphabet.length; i++) {
			if (letter == alphabet[i]) {
				return i;
			}
		}

		return index;

	}

	/**
	 * The encode method takes a string as input, splits the string into individual
	 * characters, and applies the Western Cipher algorithm, rejoins the individual
	 * characters into a string and returns it
	 * 
	 * @param input the message to be encoded
	 * @return the encoded message
	 */
	public String encode(String input) {
		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		/**
		 * A String array is created to split the individual characters from the
		 * inputed message and enqueue them to the encodingQueue using a for loop
		 */
		String[] charArray = input.split("");

		String encodedString = "";

		for (int i = 0; i < charArray.length; i++) {
			encodingQueue.enqueue(charArray[i].charAt(0));
		}

		/**
		 * A while loop is used to encode each individual character in the encodingQueue
		 * (iterate until all characters from encodingQueue have been dequeued)
		 */
		Character dequeuedItem = '*';
		int index = 0;
		while (!encodingQueue.isEmpty()) {
			/**
			 * if the previous character was converted to a number, and the current
			 * character is to be converted to a number, use different numeric values for
			 * conversion. Else, if the current character is a letter, shift according to
			 * Western Cipher algorithm
			 */
			if (Character.isDigit(dequeuedItem)) {
				Character prevDequeuedItem = dequeuedItem;
				int numValue = Character.getNumericValue(dequeuedItem);
				dequeuedItem = encodingQueue.dequeue();

				if (dequeuedItem == 'A') {
					encodedString += "3";
					dequeuedItem = '3';
				} else if (dequeuedItem == 'E') {
					encodedString += "4";
					dequeuedItem = '4';
				} else if (dequeuedItem == 'I') {
					encodedString += "5";
					dequeuedItem = '5';
				} else if (dequeuedItem == 'O') {
					encodedString += "6";
					dequeuedItem = '6';
				} else if (dequeuedItem == 'U') {
					encodedString += "1";
					dequeuedItem = '1';
				} else if (dequeuedItem == 'Y') {
					encodedString += "2";
					dequeuedItem = '2';
				} else if (dequeuedItem == ' ') {
					encodedString += " ";
					dequeuedItem = prevDequeuedItem;
				} else {
					Character temp = alphabet[(((getIndex(dequeuedItem) + 5) + (2 * index)) - (2 * numValue))
							% alphabet.length];
					encodedString += Character.toString(temp);
				}
			}
			/**
			 * if the previous character is a letter or it is the first letter of the
			 * message, execute code in else if branch
			 */
			else if (Character.isLetter(dequeuedItem) || dequeuedItem == '*') {
				Character prevDequeuedItem = dequeuedItem;
				dequeuedItem = encodingQueue.dequeue();
				/**
				 * If the current character is to be converted to a number, converted character
				 * using the Western Cipher algorithm rules
				 */
				if (dequeuedItem == 'A') {
					encodedString += "1";
					dequeuedItem = '1';
				} else if (dequeuedItem == 'E') {
					encodedString += "2";
					dequeuedItem = '2';
				} else if (dequeuedItem == 'I') {
					encodedString += "3";
					dequeuedItem = '3';
				} else if (dequeuedItem == 'O') {
					encodedString += "4";
					dequeuedItem = '4';
				} else if (dequeuedItem == 'U') {
					encodedString += "5";
					dequeuedItem = '5';
				} else if (dequeuedItem == 'Y') {
					encodedString += "6";
					dequeuedItem = '6';
				} else if (dequeuedItem == ' ') {
					encodedString += " ";
					dequeuedItem = prevDequeuedItem;
				}
				/**
				 * if the character is not to be converted to a number, shift the letter using
				 * the Western Cipher algorithm rules
				 */
				else {
					Character temp = alphabet[((getIndex(dequeuedItem) + 5) + (2 * index)) % alphabet.length];
					encodedString += Character.toString(temp);
				}

			}
			index++;
		}
		return encodedString;
	}

	public String decode(String input) {

		char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		String[] charArray = input.split("");

		String decodedString = "";

		for (int i = 0; i < charArray.length; i++) {
			decodingQueue.enqueue(charArray[i].charAt(0));
		}

		Character dequeuedItem = '*';

		int index = 0;

		/**
		 * A while loop is used to decode each individual character in the decodingQueue
		 * (iterate until all characters from decodingQueue have been dequeued)
		 */
		while (!decodingQueue.isEmpty()) {
			/**
			 * if the character is the first character in the message, execute code
			 * according to Western Cipher algorithm rules
			 */
			if (dequeuedItem == '*') {
				dequeuedItem = decodingQueue.dequeue();

				if (Character.isDigit(dequeuedItem)) {
					if (dequeuedItem == '1') {
						decodedString += "A";
					} else if (dequeuedItem == '2') {
						decodedString += "E";
					} else if (dequeuedItem == '3') {
						decodedString += "I";
					} else if (dequeuedItem == '4') {
						decodedString += "O";
					} else if (dequeuedItem == '5') {
						decodedString += "U";
					} else if (dequeuedItem == '6') {
						decodedString += "Y";
					}

				} else if (Character.isLetter(dequeuedItem)) {

					int newIndex = (getIndex(dequeuedItem) - 5) % alphabet.length;
					if (newIndex < 0) {
						newIndex += alphabet.length;
					}
					Character temp = alphabet[newIndex];
					decodedString += Character.toString(temp);
				}
			}
			/**
			 * if previous character is a number, execute code
			 */
			else if (Character.isDigit(dequeuedItem)) {
				Character prevDequeuedItem = dequeuedItem;
				int numValue = Character.getNumericValue(dequeuedItem);
				dequeuedItem = decodingQueue.dequeue();

				if (Character.isLetter(dequeuedItem)) {
					int newIndex = (((getIndex(dequeuedItem) - 5) - (2 * index)) + (2 * numValue)) % alphabet.length;
					if (newIndex < 0) {
						newIndex += alphabet.length;
					}
					Character temp = alphabet[newIndex];
					decodedString += Character.toString(temp);
				} else if (Character.isDigit(dequeuedItem)) {
					if (dequeuedItem == '3') {
						decodedString += "A";
					} else if (dequeuedItem == '4') {
						decodedString += "E";
					} else if (dequeuedItem == '5') {
						decodedString += "I";
					} else if (dequeuedItem == '6') {
						decodedString += "O";
					} else if (dequeuedItem == '1') {
						decodedString += "U";
					} else if (dequeuedItem == '2') {
						decodedString += "Y";
					} else if (dequeuedItem == ' ') {
						decodedString += " ";
						dequeuedItem = prevDequeuedItem;
					}

				}
			}
			/**
			 * if the previous character is a letter, execute code
			 */
			else if (Character.isLetter(dequeuedItem)) {
				Character prevDequeuedItem = dequeuedItem;
				int numValue = Character.getNumericValue(dequeuedItem);
				dequeuedItem = decodingQueue.dequeue();

				if (Character.isDigit(dequeuedItem)) {
					if (dequeuedItem == '1') {
						decodedString += "A";
					} else if (dequeuedItem == '2') {
						decodedString += "E";
					} else if (dequeuedItem == '3') {
						decodedString += "I";
					} else if (dequeuedItem == '4') {
						decodedString += "O";
					} else if (dequeuedItem == '5') {
						decodedString += "U";
					} else if (dequeuedItem == '6') {
						decodedString += "Y";
					}
				} else if (Character.isLetter(dequeuedItem)) {
					int newIndex = ((getIndex(dequeuedItem) - 5) - (2 * index)) % alphabet.length;
					if (newIndex < 0) {
						newIndex += alphabet.length;
					}
					Character temp = alphabet[newIndex];
					decodedString += Character.toString(temp);
				} else if (dequeuedItem == ' ') {
					decodedString += " ";
					dequeuedItem = prevDequeuedItem;
				}
			}

			index++;
		}

		return decodedString;
	}

	/**
	 * The main method prompts the user about whether it would like to encode or
	 * decode a string. The program then encodes/decodes appropriately and prompts
	 * the user to enter another string, until
	 * 
	 * @param args command line arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		MessageCipher encrypter = new MessageCipher();
		boolean quit = false;
		System.out.println("The following program encodes or decodes a String that is inputted by the user. "
				+ "\nThe only supported characters are upper case letters and positive integer numbers.\n");
		try {
			while (!quit) {
				System.out.println("Enter \"e\" for encoding or \"d\" for decoding.\n");

				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				String encryptingOption = input.readLine();
				System.out.println(encryptingOption);

				if (encryptingOption.equals("e")) {
					System.out.println("Encoding option chosen.\n");
				} else if (encryptingOption.equals("d")) {
					System.out.println("Decoding option chosen.\n");
				} else {
					System.out.println("\nInvalid option.");
					continue;
				}
				System.out.println("Enter in the string:");

				BufferedReader input2 = new BufferedReader(new InputStreamReader(System.in));
				String message = input.readLine();

				if (encryptingOption.equals("e")) {
					System.out.println("The encoded message is " + encrypter.encode(message));
				} else if (encryptingOption.equals("d")) {
					System.out.println("The decoded message is " + encrypter.decode(message));
				}

				System.out.println("\nWould you like to enter another string?");
				System.out.println("\"y\" for yes or \"n\" for no");

				BufferedReader input3 = new BufferedReader(new InputStreamReader(System.in));
				String option = input.readLine();

				if (option.equals("y")) {
					continue;
				} else if (option.equals("n")) {
					System.out.println("\nProgram ended.");
					quit = true;
				} else {
					System.out.println("Invalid input.");
					quit = true;
				}
				input.close();
				input2.close();
				input3.close();
			}

		} catch (IOException e) {
			System.out.println("Invalid input.");

		}

	}
}
