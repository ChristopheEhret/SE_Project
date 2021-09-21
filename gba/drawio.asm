# ***************************************************
#					Displays Sprite
# *************************************************** 
dispSprite:
	@ r0=spriteaddr
	@ r1=X pos
	@ r2=Y pos
	@ r3=X size @ /!\ Must be a multiple of 2
	@ r4=Y size
	push {r5}

	mov r5, #240*2
	mov r1, r1, LSL #1
	mla r1, r2, r5, r1
	add r1, r1, #0x6000000
	
	mov r3, r3, LSL #1
	mov r5, r3

spr_loopy:
	mov r3, r5

spr_loopx:
	ldr r2, [r0], #4
	str r2, [r1], #4
	subs r3, r3, #4
	bne spr_loopx

	add r1, r1, #240*2
	sub r1, r1, r5
	subs r4, r4, #1
	bne spr_loopy

	pop {r5}
	mov pc, r14
