# ***************************************************
#				Prints signed integer
# *************************************************** 
printInt:
	@ r1=int
	push {r3 - r5, r14}
	mov r0, r1
	mov r5, #0
	ldr r4, =tmpPrintIntEnd
	cmp r0, #0
	bge loopInt
	neg r0, r0
	mov r5, #0x0D

loopInt:
	mov r1, #10
	swi 0x60000
	add r1, r1, #0x10
	strb r1, [r4], #-1
	cmp r0, #0
	bne loopInt
endLoopInt:
	
	cmp r5, #0
	strneb r5, [r4], #-1
	add r1, r4, #1
	bl printStr
	pop {r3 - r5, r14}
	mov pc, r14


# ***************************************************
#						Newline
# *************************************************** 
newline:
	ldr r0, =cursory
	ldrb r1, [r0]
	add r1, r1, #1
	strb r1, [r0], #1
	mov r1, #0
	strb r1, [r0]
	mov pc, r14


# ***************************************************
#					Prints string
# *************************************************** 
printStr:
	@ r1=string
	push {r2 - r8, r14}
	mov r8, r1

loopStr:
	ldrb r1, [r8], #1
	cmp r1, #0
	beq endLoop
	bl printChar
	bl incAddressCursor
	b loopStr

endLoop:
	pop {r2 - r8, r14}
	mov pc, r14


# ***************************************************
#					Prints char
# *************************************************** 
printChar:
	@ r1=char
	
	ldr r0, =font
	mov r1, r1, LSL #3
	add r1, r0, r1
	push {r1, r14}
	bl getAddressCursor
	pop {r1, r14}
	mov r5, #8
	ldr r6, =#0xFFFF
	mov r7, #0

loopy:
	ldrb r2, [r1]
	mov r3, #1
	mov r4, #8
loopx:
	tst r2, r3
	streqh r7, [r0], #2
	strneh r6, [r0], #2
	mov r3, r3, LSL #1
	subs r4, r4, #1
	bne loopx

	subs r5, r5, #1
	add r0, r0, #232*2
	add r1, r1, #1
	bne loopy
	mov pc, r14


# ***************************************************
#					Clears screen
# *************************************************** 
clrlcdfull:
	@ ultra fast clear screen
	push {r2-r10}
	mov r0, #0x6000000
	mov r1, #0x960

	mov r2, #0x00
	mov r3, r2
	mov r4, r2
	mov r5, r2
	mov r6, r2
	mov r7, r2
	mov r8, r2
	mov r9, r2
	mov r10, r2

loop1:
	stmia r0!, { r2,r3,r4,r5,r6,r7,r8,r9,r10 }
	subs r1, r1, #1
	bne loop1 

	pop {r2-r10}
	mov pc, r14


# ***************************************************
#		Gives the current address of the cursor
# *************************************************** 
getAddressCursor:
	ldr r1, =cursory
	ldrb r0, [r1]
	ldrb r2, [r1, #1]
	mov r1, #240*2*8

	mov r2, r2, LSL #4
	mla r1, r0, r1, r2
	add r0, r1, #0x6000000
	mov pc, r14


# ***************************************************
#			Increments the cursor location
# *************************************************** 
incAddressCursor:
	ldr r1, =cursory
	ldrb r0, [r1, #1]
	add r0, r0, #1
	cmp r0, #240/8
	blt noNewLine
	ldrb r0, [r1]
	add r0, r0, #1
	strb r0, [r1]
	mov r0, #0
noNewLine:
	strb r0, [r1, #1]
	mov pc, r14


# ***************************************************
#				Set the cursor location
# *************************************************** 
setCursor:
	@ r0=y
	@ r1=x
	push {r2}
	ldr r2, =cursory
	strb r0, [r2]
	strb r1, [r2, #1]
	pop {r2}
	mov pc, r14


# ***************************************************
#			Gets the pressed key or 0
# *************************************************** 
# 0=Released, 1=Pressed
#	Bit	  Expl.
#   0     Button A
#   1     Button B
#   2     Select
#   3     Start
#   4     Right
#   5     Left
#   6     Up
#   7     Down
#   8     Button R
#   9     Button L
#   10-31 Not used
getKey:
	ldr r0, =0x4000130
	ldrh r0, [r0]
	ldr r1, =0b0000001111111111
	eor r1, r0, r1
	mov pc, r14


.ltorg

.data 
cursory:
	.byte 0
cursorx:
	.byte 0

tmpPrintInt:
	.byte 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
tmpPrintIntEnd:
	.byte 0, 0

.text


font:
.word 0x00000000, 0x00000000, 0x18181818, 0x00180018, 0x00003636, 0x00000000, 0x367F3636, 0x0036367F
.word 0x3C067C18, 0x00183E60, 0x1B356600, 0x0033566C, 0x6E16361C, 0x00DE733B, 0x000C1818, 0x00000000 
.word 0x0C0C1830, 0x0030180C, 0x3030180C, 0x000C1830, 0xFF3C6600, 0x0000663C, 0x7E181800, 0x00001818 
.word 0x00000000, 0x0C181800, 0x7E000000, 0x00000000, 0x00000000, 0x00181800, 0x183060C0, 0x0003060C 
.word 0x7E76663C, 0x003C666E, 0x181E1C18, 0x00181818, 0x3060663C, 0x007E0C18, 0x3860663C, 0x003C6660 
.word 0x33363C38, 0x0030307F, 0x603E067E, 0x003C6660, 0x3E060C38, 0x003C6666, 0x3060607E, 0x00181818
.word 0x3C66663C, 0x003C6666, 0x7C66663C, 0x001C3060, 0x00181800, 0x00181800, 0x00181800, 0x0C181800 
.word 0x06186000, 0x00006018, 0x007E0000, 0x0000007E, 0x60180600, 0x00000618, 0x3060663C, 0x00180018 

.word 0x5A5A663C, 0x003C067A, 0x7E66663C, 0x00666666, 0x3E66663E, 0x003E6666, 0x06060C78, 0x00780C06
.word 0x6666361E, 0x001E3666, 0x1E06067E, 0x007E0606, 0x1E06067E, 0x00060606, 0x7606663C, 0x007C6666
.word 0x7E666666, 0x00666666, 0x1818183C, 0x003C1818, 0x60606060, 0x003C6660, 0x0F1B3363, 0x0063331B
.word 0x06060606, 0x007E0606, 0x6B7F7763, 0x00636363, 0x7B6F6763, 0x00636373, 0x6666663C, 0x003C6666
.word 0x3E66663E, 0x00060606, 0x3333331E, 0x007E3B33, 0x3E66663E, 0x00666636, 0x3C0E663C, 0x003C6670
.word 0x1818187E, 0x00181818, 0x66666666, 0x003C6666, 0x66666666, 0x00183C3C, 0x6B636363, 0x0063777F
.word 0x183C66C3, 0x00C3663C, 0x183C66C3, 0x00181818, 0x0C18307F, 0x007F0306, 0x0C0C0C3C, 0x003C0C0C
.word 0x180C0603, 0x00C06030, 0x3030303C, 0x003C3030, 0x00663C18, 0x00000000, 0x00000000, 0x003F0000

.word 0x00301818, 0x00000000, 0x603C0000, 0x007C667C, 0x663E0606, 0x003E6666, 0x063C0000, 0x003C0606
.word 0x667C6060, 0x007C6666, 0x663C0000, 0x003C067E, 0x0C3E0C38, 0x000C0C0C, 0x667C0000, 0x3C607C66
.word 0x663E0606, 0x00666666, 0x18180018, 0x00301818, 0x30300030, 0x1E303030, 0x36660606, 0x0066361E
.word 0x18181818, 0x00301818, 0x7F370000, 0x0063636B, 0x663E0000, 0x00666666, 0x663C0000, 0x003C6666
.word 0x663E0000, 0x06063E66, 0x667C0000, 0x60607C66, 0x663E0000, 0x00060606, 0x063C0000, 0x003E603C
.word 0x0C3E0C0C, 0x00380C0C, 0x66660000, 0x007C6666, 0x66660000, 0x00183C66, 0x63630000, 0x00367F6B
.word 0x36630000, 0x0063361C, 0x66660000, 0x0C183C66, 0x307E0000, 0x007E0C18, 0x0C181830, 0x00301818
.word 0x18181818, 0x00181818, 0x3018180C, 0x000C1818, 0x003B6E00, 0x00000000, 0x00000000, 0x00000000
