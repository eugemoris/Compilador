.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.STACK 200h
.DATA
mensajeError DB "ERROR",0
mensajeDivError DB "ERROR: DIVISION POR CERO",0
mensajeOverflowSuma DB "ERROR: OVERFLOW EN SUMA",0
mensajeOverflowMultiplicacion DB "ERROR: OVERFLOW EN MULTIPLICACION",0
_a DW ?
_a$ DD ?
mensaje0 DB "Exito", 0
_3 DW 3
_2 DW 2
_5 DW 5
_@float0 DD 1.5
_@aux1 DD ?
_@aux3 DW ?
_@aux4 DW ?
_@aux5 DW ?
_@aux7 DW ?
_@auxcomp DW ?
_@auxcero DD 0.0
_@maxValor DQ 3.40282347E38
_@minValor DQ -3.40282347E38
.CODE
divError:
invoke MessageBox, NULL, addr mensajeDivError, addr mensajeError, MB_OK
invoke ExitProcess, 0
errorOverflowSuma:
invoke MessageBox, NULL, addr mensajeOverflowSuma, addr mensajeError, MB_OK
invoke ExitProcess, 0
errorOverflowMultiplicacion:
invoke MessageBox, NULL, addr mensajeOverflowMultiplicacion, addr mensajeError, MB_OK
invoke ExitProcess, 0
START:
MOV EAX, _@float0
MOV _a$, EAX
MOV AX, _2
ADD AX, _3
JC errorOverflowSuma
MOV _@aux3, AX
MOV AX, _@aux3
MOV _a, AX
MOV AX, _a
MOV BX, _5
CMP AX, BX
JNE Label2
invoke MessageBox, NULL, addr mensaje0, addr mensaje0, MB_OK
Label2:
invoke ExitProcess,0
END START
