package fr.ensimag.deca.codegen;

import java.util.concurrent.ThreadLocalRandom;

import fr.ensimag.pseudocode.gba.GImmediate32BitInteger;
import fr.ensimag.pseudocode.gba.GOp2;
import fr.ensimag.pseudocode.gba.ShiftType;

/** 
 * The ouput is aim at being tested with the command line :
 * arm-none-eabi-gcc -mthumb-interwork -specs=gba.specs test.s
 * 
 */

public class Op2Test {

	public static void main(String[] args) {
		while(true) {
			int r = ThreadLocalRandom.current().nextInt(0, 10000000);
			if(convertOp2(r)!=null)
				System.out.println("\tMOV r0, #"+r);
		}
	}
	
	public static GOp2 convertOp2(int n) {
		// returns an instance of GOp2 representing n or null if it is not possible
		if(n<256)
			return new GOp2(new GImmediate32BitInteger(n), ShiftType.NONE, 0);
		
		String binN = "";
		while(n!=0) {
			binN = n%2 + binN;
			n /= 2;
		}
		
		String trimed = binN.substring(0, binN.lastIndexOf('1')+1);
		int shifts = (binN.length()-binN.lastIndexOf('1')-1);
		if(trimed.length()>8 || shifts%2==1)
			return null;
		else
			return new GOp2(new GImmediate32BitInteger(Integer.valueOf(trimed)), ShiftType.ROR, shifts);
	}

}
