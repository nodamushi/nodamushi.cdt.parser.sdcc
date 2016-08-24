/*******************************************************************************
* Copyright (c) 2006, 2010 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     IBM Corporation - initial API and implementation
*********************************************************************************/

// This file was generated by LPG

package nodamushi.internal.cdt.parser.sdcc;

public interface SDCCNoCastExpressionParsersym {
    public final static int
      TK_auto = 29,
      TK_break = 103,
      TK_case = 104,
      TK_char = 40,
      TK_const = 8,
      TK_continue = 105,
      TK_default = 106,
      TK_do = 107,
      TK_double = 41,
      TK_else = 108,
      TK_enum = 58,
      TK_extern = 30,
      TK_float = 42,
      TK_for = 109,
      TK_goto = 110,
      TK_if = 111,
      TK_inline = 31,
      TK_int = 43,
      TK_long = 44,
      TK_register = 32,
      TK_restrict = 9,
      TK_return = 112,
      TK_short = 45,
      TK_signed = 46,
      TK_sizeof = 33,
      TK_static = 22,
      TK_struct = 59,
      TK_switch = 113,
      TK_typedef = 34,
      TK_union = 60,
      TK_unsigned = 47,
      TK_void = 48,
      TK_volatile = 10,
      TK_while = 114,
      TK__Bool = 49,
      TK__Complex = 50,
      TK__Imaginary = 51,
      TK_integer = 6,
      TK_floating = 35,
      TK_charconst = 36,
      TK_stringlit = 37,
      TK_identifier = 1,
      TK_Completion = 5,
      TK_EndOfCompletion = 3,
      TK_Invalid = 115,
      TK_LeftBracket = 7,
      TK_LeftParen = 2,
      TK_LeftBrace = 23,
      TK_Dot = 68,
      TK_Arrow = 89,
      TK_PlusPlus = 25,
      TK_MinusMinus = 26,
      TK_And = 24,
      TK_Star = 4,
      TK_Plus = 20,
      TK_Minus = 21,
      TK_Tilde = 38,
      TK_Bang = 39,
      TK_Slash = 69,
      TK_Percent = 70,
      TK_RightShift = 61,
      TK_LeftShift = 62,
      TK_LT = 71,
      TK_GT = 72,
      TK_LE = 73,
      TK_GE = 74,
      TK_EQ = 76,
      TK_NE = 77,
      TK_Caret = 78,
      TK_Or = 79,
      TK_AndAnd = 80,
      TK_OrOr = 90,
      TK_Question = 91,
      TK_Colon = 63,
      TK_DotDotDot = 64,
      TK_Assign = 75,
      TK_StarAssign = 92,
      TK_SlashAssign = 93,
      TK_PercentAssign = 94,
      TK_PlusAssign = 95,
      TK_MinusAssign = 96,
      TK_RightShiftAssign = 97,
      TK_LeftShiftAssign = 98,
      TK_AndAssign = 99,
      TK_CaretAssign = 100,
      TK_OrAssign = 101,
      TK_Comma = 27,
      TK_RightBracket = 65,
      TK_RightParen = 28,
      TK_RightBrace = 52,
      TK_SemiColon = 66,
      TK___data = 11,
      TK___near = 12,
      TK___xdata = 13,
      TK___far = 14,
      TK___idata = 15,
      TK___pdata = 16,
      TK___code = 17,
      TK___bit = 53,
      TK___sfr = 54,
      TK___sfr16 = 55,
      TK___sfr32 = 56,
      TK___sbit = 57,
      TK___at = 18,
      TK___banked = 19,
      TK___interrupt = 81,
      TK___using = 82,
      TK___reentrant = 83,
      TK___critical = 84,
      TK___naked = 85,
      TK___wparam = 86,
      TK___shadowregs = 87,
      TK___preserves__regs = 88,
      TK___asm = 116,
      TK___endasm = 117,
      TK_ERROR_TOKEN = 67,
      TK_EOF_TOKEN = 102;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "identifier",
                 "LeftParen",
                 "EndOfCompletion",
                 "Star",
                 "Completion",
                 "integer",
                 "LeftBracket",
                 "const",
                 "restrict",
                 "volatile",
                 "__data",
                 "__near",
                 "__xdata",
                 "__far",
                 "__idata",
                 "__pdata",
                 "__code",
                 "__at",
                 "__banked",
                 "Plus",
                 "Minus",
                 "static",
                 "LeftBrace",
                 "And",
                 "PlusPlus",
                 "MinusMinus",
                 "Comma",
                 "RightParen",
                 "auto",
                 "extern",
                 "inline",
                 "register",
                 "sizeof",
                 "typedef",
                 "floating",
                 "charconst",
                 "stringlit",
                 "Tilde",
                 "Bang",
                 "char",
                 "double",
                 "float",
                 "int",
                 "long",
                 "short",
                 "signed",
                 "unsigned",
                 "void",
                 "_Bool",
                 "_Complex",
                 "_Imaginary",
                 "RightBrace",
                 "__bit",
                 "__sfr",
                 "__sfr16",
                 "__sfr32",
                 "__sbit",
                 "enum",
                 "struct",
                 "union",
                 "RightShift",
                 "LeftShift",
                 "Colon",
                 "DotDotDot",
                 "RightBracket",
                 "SemiColon",
                 "ERROR_TOKEN",
                 "Dot",
                 "Slash",
                 "Percent",
                 "LT",
                 "GT",
                 "LE",
                 "GE",
                 "Assign",
                 "EQ",
                 "NE",
                 "Caret",
                 "Or",
                 "AndAnd",
                 "__interrupt",
                 "__using",
                 "__reentrant",
                 "__critical",
                 "__naked",
                 "__wparam",
                 "__shadowregs",
                 "__preserves__regs",
                 "Arrow",
                 "OrOr",
                 "Question",
                 "StarAssign",
                 "SlashAssign",
                 "PercentAssign",
                 "PlusAssign",
                 "MinusAssign",
                 "RightShiftAssign",
                 "LeftShiftAssign",
                 "AndAssign",
                 "CaretAssign",
                 "OrAssign",
                 "EOF_TOKEN",
                 "break",
                 "case",
                 "continue",
                 "default",
                 "do",
                 "else",
                 "for",
                 "goto",
                 "if",
                 "return",
                 "switch",
                 "while",
                 "Invalid",
                 "__asm",
                 "__endasm"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
