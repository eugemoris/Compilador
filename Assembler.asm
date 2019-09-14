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
_a DD ?
_@float0 DD -1.4
_@aux1 DD ?
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
MOV _a, EAX
invoke ExitProcess,0
END START
