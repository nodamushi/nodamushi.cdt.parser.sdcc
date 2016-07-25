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

public interface SDCCExpressionParsersym {
    public final static int
      TK_auto = 38,
      TK_break = 103,
      TK_case = 104,
      TK_char = 43,
      TK_const = 10,
      TK_continue = 105,
      TK_default = 106,
      TK_do = 107,
      TK_double = 44,
      TK_else = 108,
      TK_enum = 58,
      TK_extern = 39,
      TK_float = 45,
      TK_for = 109,
      TK_goto = 110,
      TK_if = 111,
      TK_inline = 40,
      TK_int = 46,
      TK_long = 47,
      TK_register = 41,
      TK_restrict = 11,
      TK_return = 112,
      TK_short = 48,
      TK_signed = 49,
      TK_sizeof = 29,
      TK_static = 30,
      TK_struct = 59,
      TK_switch = 113,
      TK_typedef = 42,
      TK_union = 60,
      TK_unsigned = 50,
      TK_void = 51,
      TK_volatile = 12,
      TK_while = 114,
      TK__Bool = 52,
      TK__Complex = 53,
      TK__Imaginary = 54,
      TK_integer = 6,
      TK_floating = 31,
      TK_charconst = 32,
      TK_stringlit = 33,
      TK_identifier = 1,
      TK_Completion = 4,
      TK_EndOfCompletion = 5,
      TK_Invalid = 115,
      TK_LeftBracket = 7,
      TK_LeftParen = 2,
      TK_LeftBrace = 26,
      TK_Dot = 68,
      TK_Arrow = 89,
      TK_PlusPlus = 27,
      TK_MinusMinus = 28,
      TK_And = 13,
      TK_Star = 3,
      TK_Plus = 8,
      TK_Minus = 9,
      TK_Tilde = 34,
      TK_Bang = 35,
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
      TK_Comma = 36,
      TK_RightBracket = 65,
      TK_RightParen = 37,
      TK_RightBrace = 55,
      TK_SemiColon = 66,
      TK___data = 14,
      TK___near = 15,
      TK___xdata = 16,
      TK___far = 17,
      TK___idata = 18,
      TK___pdata = 19,
      TK___code = 20,
      TK___bit = 56,
      TK___sfr = 21,
      TK___sfr16 = 22,
      TK___sfr32 = 23,
      TK___sbit = 57,
      TK___at = 24,
      TK___banked = 25,
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
                 "Star",
                 "Completion",
                 "EndOfCompletion",
                 "integer",
                 "LeftBracket",
                 "Plus",
                 "Minus",
                 "const",
                 "restrict",
                 "volatile",
                 "And",
                 "__data",
                 "__near",
                 "__xdata",
                 "__far",
                 "__idata",
                 "__pdata",
                 "__code",
                 "__sfr",
                 "__sfr16",
                 "__sfr32",
                 "__at",
                 "__banked",
                 "LeftBrace",
                 "PlusPlus",
                 "MinusMinus",
                 "sizeof",
                 "static",
                 "floating",
                 "charconst",
                 "stringlit",
                 "Tilde",
                 "Bang",
                 "Comma",
                 "RightParen",
                 "auto",
                 "extern",
                 "inline",
                 "register",
                 "typedef",
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