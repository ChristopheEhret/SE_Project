# Use this Makefile to compile .deca files into .gba files.
# Compiling dir/file.deca :
#	make compile TARGET=dir/file
# Emulating dir/file.gba :
#	make emul TARGET=dir.file

DECAC=decac
ASM=arm-none-eabi-gcc
LNK=arm-none-eabi-objcopy
EMU=vba

ifndef TARGET
$(error TARGET is not set)
endif


compile:
	$(DECAC) -d -d --gba $(TARGET).deca
	$(ASM) -mthumb-interwork -specs=gba.specs $(TARGET).s
	$(LNK) -O binary a.out $(TARGET).gba
	rm a.out $(TARGET).s

run:
	$(DECAC) -d -d --gba $(TARGET).deca
	$(ASM) -mthumb-interwork -specs=gba.specs $(TARGET).s
	$(LNK) -O binary a.out $(TARGET).gba
	rm a.out $(TARGET).s
	$(EMU) $(TARGET).gba

emul:
	$(EMU) $(TARGET).gba
