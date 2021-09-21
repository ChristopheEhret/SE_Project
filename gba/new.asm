# ***************************************************
#						NEW
# *************************************************** 

@ what is NEWed is not aimed at being FREEd
@ 	=> the code is then trivial

new:
	@ INPUT : r0=size
	@ OUTPUT : r0=address
	push {r2, r14}
	ldr r1, =nextAvailableAddr
	ldr r2, =0x0203fff
	cmp r1, r2
	bvs noOverflow
	mov r0, #0
	b endNew
	
noOverflow:
	ldr r2, [r1]
	add r0, r0, r2
	str r0, [r1]
	mov r0, r2
endNew:
	pop {r2, r14}
	mov pc, r14

.ltorg

.data
@02000000
@0203ffff
nextAvailableAddr:
	.word 0x02000000

.text
