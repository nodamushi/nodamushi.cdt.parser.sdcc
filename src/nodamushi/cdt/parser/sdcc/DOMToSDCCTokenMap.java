package nodamushi.cdt.parser.sdcc;

import org.eclipse.cdt.core.dom.lrparser.IDOMTokenMap;
import org.eclipse.cdt.core.parser.IToken;

import static org.eclipse.cdt.core.parser.IToken.*;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.*;

public class DOMToSDCCTokenMap implements IDOMTokenMap{


  @Override
  public int getEOFTokenKind(){
    return TK_EOF_TOKEN;
  }

  @Override
  public int getEOCTokenKind(){
    return TK_EndOfCompletion;
  }

  @Override
  public int mapKind(IToken token){
    switch(token.getType()) {
      case tIDENTIFIER :
        return SDCCKeyword.getTokenKind(token.getCharImage()).orElse(TK_identifier);
      case tINTEGER      : return TK_integer;
      case tCOLON        : return TK_Colon;
      case tSEMI         : return TK_SemiColon;
      case tCOMMA        : return TK_Comma;
      case tQUESTION     : return TK_Question;
      case tLPAREN       : return TK_LeftParen;
      case tRPAREN       : return TK_RightParen;
      case tLBRACKET     : return TK_LeftBracket;
      case tRBRACKET     : return TK_RightBracket;
      case tLBRACE       : return TK_LeftBrace;
      case tRBRACE       : return TK_RightBrace;
      case tPLUSASSIGN   : return TK_PlusAssign;
      case tINCR         : return TK_PlusPlus;
      case tPLUS         : return TK_Plus;
      case tMINUSASSIGN  : return TK_MinusAssign;
      case tDECR         : return TK_MinusMinus;
      case tARROW        : return TK_Arrow;
      case tMINUS        : return TK_Minus;
      case tSTARASSIGN   : return TK_StarAssign;
      case tSTAR         : return TK_Star;
      case tMODASSIGN    : return TK_PercentAssign;
      case tMOD          : return TK_Percent;
      case tXORASSIGN    : return TK_CaretAssign;
      case tXOR          : return TK_Caret;
      case tAMPERASSIGN  : return TK_AndAssign;
      case tAND          : return TK_AndAnd;
      case tAMPER        : return TK_And;
      case tBITORASSIGN  : return TK_OrAssign;
      case tOR           : return TK_OrOr;
      case tBITOR        : return TK_Or;
      case tBITCOMPLEMENT: return TK_Tilde;
      case tNOTEQUAL     : return TK_NE;
      case tNOT          : return TK_Bang;
      case tEQUAL        : return TK_EQ;
      case tASSIGN       : return TK_Assign;
      case tUNKNOWN_CHAR : return TK_Invalid;
      case tSHIFTL       : return TK_LeftShift;
      case tLTEQUAL      : return TK_LE;
      case tLT           : return TK_LT;
      case tSHIFTRASSIGN : return TK_RightShiftAssign;
      case tSHIFTR       : return TK_RightShift;
      case tGTEQUAL      : return TK_GE;
      case tGT           : return TK_GT;
      case tSHIFTLASSIGN : return TK_LeftShiftAssign;
      case tELLIPSIS     : return TK_DotDotDot;
      case tDOT          : return TK_Dot;
      case tDIVASSIGN    : return TK_SlashAssign;
      case tDIV          : return TK_Slash;
      case t_auto        : return TK_auto;
      case t_break       : return TK_break;
      case t_case        : return TK_case;
      case t_char        : return TK_char;
      case t_const       : return TK_const;
      case t_continue    : return TK_continue;
      case t_default     : return TK_default;
      case t_do          : return TK_do;
      case t_double      : return TK_double;
      case t_else        : return TK_else;
      case t_enum        : return TK_enum;
      case t_extern      : return TK_extern;
      case t_float       : return TK_float;
      case t_for         : return TK_for;
      case t_goto        : return TK_goto;
      case t_if          : return TK_if;
      case t_inline      : return TK_inline;
      case t_int         : return TK_int;
      case t_long        : return TK_long;
      case t_register    : return TK_register;
      case t_return      : return TK_return;
      case t_short       : return TK_short;
      case t_sizeof      : return TK_sizeof;
      case t_static      : return TK_static;
      case t_signed      : return TK_signed;
      case t_struct      : return TK_struct;
      case t_switch      : return TK_switch;
      case t_typedef     : return TK_typedef;
      case t_union       : return TK_union;
      case t_unsigned    : return TK_unsigned;
      case t_void        : return TK_void;
      case t_volatile    : return TK_volatile;
      case t_while       : return TK_while;
      case tFLOATINGPT   : return TK_floating;
      case tSTRING       : return TK_stringlit;
      case tLSTRING      : return TK_stringlit;
      case tUTF16STRING  : return TK_stringlit;
      case tUTF32STRING  : return TK_stringlit;
      case tCHAR         : return TK_charconst;
      case tLCHAR        : return TK_charconst;
      case tUTF16CHAR    : return TK_charconst;
      case tUTF32CHAR    : return TK_charconst;
      case t__Bool       : return TK__Bool;
      case t__Complex    : return TK__Complex;
      case t__Imaginary  : return TK__Imaginary;
      case t_restrict    : return TK_restrict;
      case tCOMPLETION   : return TK_Completion;
      case tEOC          : return TK_EndOfCompletion;
      case tEND_OF_INPUT : return TK_EOF_TOKEN;

      default:
        assert false : "token not recognized by the SDCC parser: " + token.getType();
      return TK_Invalid;
    }
  }


  static int reverseKind(int k){
    switch(k) {
      case TK_integer : return tINTEGER;
      case TK_Colon : return tCOLON;
      case TK_SemiColon : return tSEMI;
      case TK_Comma : return tCOMMA;
      case TK_Question : return tQUESTION;
      case TK_LeftParen : return tLPAREN;
      case TK_RightParen : return tRPAREN;
      case TK_LeftBracket : return tLBRACKET;
      case TK_RightBracket : return tRBRACKET;
      case TK_LeftBrace : return tLBRACE;
      case TK_RightBrace : return tRBRACE;
      case TK_PlusAssign : return tPLUSASSIGN;
      case TK_PlusPlus : return tINCR;
      case TK_Plus : return tPLUS;
      case TK_MinusAssign : return tMINUSASSIGN;
      case TK_MinusMinus : return tDECR;
      case TK_Arrow : return tARROW;
      case TK_Minus : return tMINUS;
      case TK_StarAssign : return tSTARASSIGN;
      case TK_Star : return tSTAR;
      case TK_PercentAssign : return tMODASSIGN;
      case TK_Percent : return tMOD;
      case TK_CaretAssign : return tXORASSIGN;
      case TK_Caret : return tXOR;
      case TK_AndAssign : return tAMPERASSIGN;
      case TK_AndAnd : return tAND;
      case TK_And : return tAMPER;
      case TK_OrAssign : return tBITORASSIGN;
      case TK_OrOr : return tOR;
      case TK_Or : return tBITOR;
      case tBITCOMPLEMENT: return TK_Tilde;
      case TK_NE : return tNOTEQUAL;
      case TK_Bang : return tNOT;
      case TK_EQ : return tEQUAL;
      case TK_Assign : return tASSIGN;
      case TK_Invalid : return tUNKNOWN_CHAR;
      case TK_LeftShift : return tSHIFTL;
      case TK_LE : return tLTEQUAL;
      case TK_LT : return tLT;
      case TK_RightShiftAssign : return tSHIFTRASSIGN;
      case TK_RightShift : return tSHIFTR;
      case TK_GE : return tGTEQUAL;
      case TK_GT : return tGT;
      case TK_LeftShiftAssign : return tSHIFTLASSIGN;
      case TK_DotDotDot : return tELLIPSIS;
      case TK_Dot : return tDOT;
      case TK_SlashAssign : return tDIVASSIGN;
      case TK_Slash : return tDIV;
      case TK_auto : return t_auto;
      case TK_break : return t_break;
      case TK_case : return t_case;
      case TK_char : return t_char;
      case TK_const : return t_const;
      case TK_continue : return t_continue;
      case TK_default : return t_default;
      case TK_do : return t_do;
      case TK_double : return t_double;
      case TK_else : return t_else;
      case TK_enum : return t_enum;
      case TK_extern : return t_extern;
      case TK_float : return t_float;
      case TK_for : return t_for;
      case TK_goto : return t_goto;
      case TK_if : return t_if;
      case TK_inline : return t_inline;
      case TK_int : return t_int;
      case TK_long : return t_long;
      case TK_register : return t_register;
      case TK_return : return t_return;
      case TK_short : return t_short;
      case TK_sizeof : return t_sizeof;
      case TK_static : return t_static;
      case TK_signed : return t_signed;
      case TK_struct : return t_struct;
      case TK_switch : return t_switch;
      case TK_typedef : return t_typedef;
      case TK_union : return t_union;
      case TK_unsigned : return t_unsigned;
      case TK_void : return t_void;
      case TK_volatile : return t_volatile;
      case TK_while : return t_while;
      case TK_floating : return tFLOATINGPT;
      case TK_stringlit : return tSTRING;
      case TK_charconst : return tCHAR;
      case TK__Bool : return t__Bool;
      case TK__Complex : return t__Complex;
      case TK__Imaginary : return t__Imaginary;
      case TK_restrict : return t_restrict;
      case TK_Completion : return tCOMPLETION;
      case TK_EndOfCompletion : return tEOC;
      case TK_EOF_TOKEN : return tEND_OF_INPUT;

      default:
        return tIDENTIFIER;
    }
  }
}
