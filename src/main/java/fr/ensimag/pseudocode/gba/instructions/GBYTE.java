package fr.ensimag.pseudocode.gba.instructions;

import org.apache.commons.lang.Validate;

import fr.ensimag.pseudocode.gba.GUnaryDirective;

public class GBYTE extends GUnaryDirective {
	private final static String CHARACTERS = "\0!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ ";;

	public GBYTE(String parameter) {
		super(parameter);
	}
	
	public static GBYTE createString(String txt) {
        String retStr = "";
    	for(char ch : txt.toCharArray()) {
        	final int index = CHARACTERS.indexOf(ch);
        	Validate.isTrue(index!=-1, "'"+ch+"' is not a valid character for GBA. The valid characters are : \n"+CHARACTERS);
        	retStr += index+", ";
        }
    	retStr += "0";
        return new GBYTE(retStr);
    }

}
