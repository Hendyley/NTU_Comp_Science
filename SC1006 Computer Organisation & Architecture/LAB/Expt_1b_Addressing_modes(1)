		;---------------------------------------------------------
		;Expt_1b	- ARM addressing modes, load & store instructions
		;---------------------------------------------------------
		;Allocate	values in data memory
DataArea	EQU		0x00000100	;data area starts at 0x0100
Num1		DCD		0x1111AAAA	;allocate 32-bit values
Num2		DCD		0x2222BBBB	;in memory starting at
Num3		DCD		0x3333CCCC	;address 0x00000100
Num4		DCD		0x4444DDDD	;
		;Start	of program
Start	MOV		R0,#0xAB		;immediate data
		MOV		R1,R0		;register direct
		;load	register instructions
		MOV		R2,#DataArea	;initialize address pointer R2
		LDR		R3,[R2]		;register indirect
		LDR		R4,[R2,#4]	;base plus offset
		LDR		R5,[R2],#4	;auto-index (post-index)
		LDR		R6,[R2,#4]!	;auto-index (pre-index)
		;store	register instructions
		MOV		R2,#DataArea	;initialize R2 to start of data area
		STR		R0,[R2]		;store R0 to address 0x0100
							;store R0 to address 0x010C
							;store R0 to 0x0104 & increment R2 by 4
							;get the value of zero into R0
							;store R0 to 0x0104 & increment R2 by 4 again
		END
