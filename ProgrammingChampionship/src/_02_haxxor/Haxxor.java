package _02_haxxor;

import java.util.Scanner;

public class Haxxor {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Number of ports: ");
//		int numPorts = in.nextInt();
		System.out.print("Upper bound: ");
//		int upperBound = in.nextInt();

//		in.nextLine(); // empty buffer

		int correctAddress = Integer.parseInt(args[0]);
		int numPorts = 1;
		int upperBound = 2000;
		
		
		

		// TODO
		int addressesToCheck = 77;

//		for (int correctAddress = 1; correctAddress < addressesToCheck; correctAddress++) {

			for (int port = 0; port < numPorts; port++) {
				
				int guess = upperBound / 2;
				String answer = "";
				
				while (!answer.equals("EINVAL")) {

					System.out.println("correct: " + correctAddress);
					
					System.out.printf("READ %d%n", guess);

					// TODO
					// answer = in.nextLine();

					answer = guess == correctAddress ? "EINVAL"
							: guess < correctAddress ? "true" : "false";

					System.out.println(answer);

					if (answer.equals("EINVAL"))
						break;

						
					if (answer.equals("true")) {
						guess += guess / 3 + 1;
					} else { // assuming anything other than "true" is false
						guess -= (guess / 2) + 1;
					}
				}

				System.out.printf("OUTPUT %d%n", guess);
			}
//		}
	}
}
