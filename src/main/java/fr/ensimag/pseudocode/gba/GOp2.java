package fr.ensimag.pseudocode.gba;

import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.DVal;

/**
 * Operand that is a shifted register or 8 bit immediate.
 * 
 * @author gl27
 * @date 01/01/2021
 */
public class GOp2 extends DVal {
	private ShiftType shift;
	private int nShift;
	private DVal regOrImm8;
	
	public GOp2(DVal regOrImm8, ShiftType shift, int nShift) {
		this.regOrImm8 = regOrImm8;
		Validate.isTrue(regOrImm8.isRegister() || shift==ShiftType.NONE);
		this.shift = shift;
		this.nShift = nShift;
	}
	
	public GOp2(DVal regOrImm8) {
		this.regOrImm8 = regOrImm8;
		this.shift = ShiftType.NONE;
		this.nShift = 0;
	}
	
	public static GOp2 convertOp2(int n) {
		// returns an instance of GOp2 representing n or null if it is not possible
		if(n<256)
			return new GOp2(new GImmediate8BitShiftedInteger(n), ShiftType.NONE, 0);
		
		String binN = "";
		int num = n;
		while(num!=0) {
			binN = num%2 + binN;
			num /= 2;
		}
		
		String trimed = binN.substring(0, binN.lastIndexOf('1')+1);
		int shifts = (binN.length()-binN.lastIndexOf('1')-1);
		if(trimed.length()>8 || shifts%2==1/* || shifts>8*/)
			return null;
		else
			return new GOp2(new GImmediate8BitShiftedInteger(n), ShiftType.NONE, 0);
	}
	
	public boolean isRegister() {
		return regOrImm8.isRegister();
	}

	@Override
	public String toString() {
		if(shift==ShiftType.NONE)
			return regOrImm8.toString();
		else
			return regOrImm8.toString()+", "+shift.name()+" #"+nShift;
	}
}
