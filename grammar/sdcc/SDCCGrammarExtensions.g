
$Define
	$build_action_class /. SDCCParserAction ./
	$node_factory_create_expression /. new SDCCASTNodeFactory() ./
	$parser_factory_create_expression /. SDCCSecondaryParserFactory.getDefault() ./
    $Build /. action. ./
    $EndBuild /. ./
$End


$Globals
/.
import org.eclipse.cdt.core.dom.lrparser.lpgextensions.FixedBacktrackingParser;
import nodamushi.cdt.parser.sdcc.*;
import nodamushi.cdt.parser.sdcc.ast.*;
import nodamushi.internal.cdt.parser.sdcc.ast.SDCCASTNodeFactory;
./
$End


$Terminals  -- Additional keywords defined by UPC

  __data
  __near
  __xdata
  __far
  __idata
  __pdata
  __code
  __bit
  __sfr
  __sfr16
  __sfr32
  __sbit

  __at

  __banked


  __interrupt
  __using
  __reentrant
  __critical
  __naked
  __wparam
  __shadowregs
  __preserves__regs


  __asm
  __endasm
$End



$Rules  -- SDCC grammar extensions to C99
      
      
-----------------------------------------------------------------------------------
-- Declarations
-----------------------------------------------------------------------------------

type_qualifier
    ::= address_space_name_qualifier
      | define_address
      | sdcc_type_qualifier
        /. $Build consumeAddressSpaceName(); $EndBuild ./


address_space_name_qualifier
    ::= '__data'
      | '__near'
      | '__xdata'
      | '__far'
      | '__idata'
      | '__pdata'
      | '__code'
      | '__sfr'
      | '__sfr16'
      | '__sfr32'

simple_type_specifier_token
    ::= '__sbit'
      | '__bit'

define_address
    ::= '__at' '(' absolute_address ')'
         /. $Build  consumeDefineAddress();  $EndBuild ./
      | '__at' absolute_address
         /. $Build  consumeDefineAddress();  $EndBuild ./

absolute_address
    ::= 'integer'
         /. $Build  consumeAbsoluteAddress();  $EndBuild ./

sdcc_type_qualifier
   ::= '__banked'
         /. $Build  consumeToken();  $EndBuild ./



-----------------------------------------------------------------------------------
-- Function
-----------------------------------------------------------------------------------

-- copy from C99Grammar.g

original_function_direct_declarator
    ::= basic_direct_declarator '(' <openscope-ast> parameter_type_list ')'
          /. $Build  consumeDirectDeclaratorFunctionDeclarator(true, true);  $EndBuild ./
      | basic_direct_declarator '(' ')'
          /. $Build  consumeDirectDeclaratorFunctionDeclarator(true, false);  $EndBuild ./


function_direct_declarator
  ::= original_function_direct_declarator <openscope-ast> sdcc_function_attributes
    /. $Build  consumeSDCCFunctionAttributes();  $EndBuild ./


sdcc_function_attributes
 ::= sdcc_function_attribute
    | sdcc_function_attributes sdcc_function_attribute
 
sdcc_function_attribute
  ::= address_function_attribute
    | '__critical'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__critical,false);  $EndBuild ./
    | '__reentrant'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__reentrant,false);  $EndBuild ./
    | '__naked'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__naked,false);  $EndBuild ./
    | '__shadowregs'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__shadowregs,false);  $EndBuild ./
    | '__wparam'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__wparam,false);  $EndBuild ./
    | preserves_regs_attribute


address_function_attribute
  ::= '__interrupt' '(' integer_token  ')'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__interrupt,true);  $EndBuild ./
    | '__interrupt'  integer_token 
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__interrupt,true);  $EndBuild ./
    | '__using' '('  integer_token  ')'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__using,true);  $EndBuild ./
    | '__using'  integer_token 
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__using,true);  $EndBuild ./
    | '__interrupt'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__interrupt,false);  $EndBuild ./
    | '__using'
     /. $Build  consumeSDCCFunctionAttribute(SDCCKeyword.__using,false);  $EndBuild ./


integer_token
    ::= 'integer'
         /. $Build  consumeIntegerToken();  $EndBuild ./


preserves_regs_attribute
    ::= '__preserves__regs' '(' ')'
     /. $Build  consumeSDCCPreservesRegsAttribute(false);  $EndBuild ./
      | '__preserves__regs' '(' <openscope-ast> preserves_regs_args ')'
     /. $Build  consumeSDCCPreservesRegsAttribute(true);  $EndBuild ./

preserves_regs_args 
    ::= preserves_regs_arg
    | preserves_regs_args ',' preserves_regs_arg

preserves_regs_arg
    ::= 'identifier'
     /. $Build  consumeSDCCPreservesRegsArg();  $EndBuild ./

-----------------------------------------------------------------------------------
-- Statements
-----------------------------------------------------------------------------------

statement
     ::= critical_statement
       | oldasm_satement




critical_statement
   ::= '__critical' compound_statement
     /. $Build  consumeCriticalAttribute();  $EndBuild ./





oldasm_satement
   ::= '__asm' <openscope-ast> oldasm_contents '__endasm' ';'
     /. $Build  consumeSDCCOldAsmStatement(true);  $EndBuild ./
     | '__asm' '__endasm' ';'
     /. $Build  consumeSDCCOldAsmStatement(false);  $EndBuild ./


oldasm_contents
   ::= oldasm_content
     | oldasm_contents oldasm_content

oldasm_content
   ::= oldasm_item
     /. $Build  consumeASTToken();  $EndBuild ./


-- every terminals but __endasm
oldasm_item  
  ::= 'auto'
    | 'break'
    | 'case'
    | 'char'
    | 'const'
    | 'continue'
    | 'default'
    | 'do'
    | 'double'
    | 'else'
    | 'enum'
    | 'extern'
    | 'float'
    | 'for'
    | 'goto'
    | 'if'
    | 'inline'
    | 'int'
    | 'long'
    | 'register'
    | 'restrict'
    | 'return'
    | 'short'
    | 'signed'
    | 'sizeof'
    | 'static'
    | 'struct'
    | 'switch'
    | 'typedef'
    | 'union'
    | 'unsigned'
    | 'void'
    | 'volatile'
    | 'while'
    | '_Bool'
    | '_Complex'
    | '_Imaginary'
    | 'integer'
    | 'floating'
    | 'charconst'
    | 'stringlit'
    | 'identifier'
    | 'Completion'
    | 'EndOfCompletion'
    | 'Invalid'
    | '['
    | '('
    | '{'
    | '.'
    | '->'
    | '++'
    | '--'
    | '&'
    | '*'
    | '+'
    | '-'
    | '~'
    | '!'
    | '/'
    | '%'
    | '>>'
    | '<<'
    | '<'
    | '>'
    | '<='
    | '>='
    | '=='
    | '!='
    | '^'
    | '|'
    | '&&'
    | '||'
    | '?'
    | ':'
    | '...'
    | '='
    | '*='
    | '/='
    | '%='
    | '+='
    | '-='
    | '>>='
    | '<<='
    | '&='
    | '^='
    | '|='
    | ','
    | 'RightBracket'
    | 'RightParen'
    | 'RightBrace'
    | 'SemiColon'
    | '__data'
    | '__near'
    | '__xdata'
    | '__far'
    | '__idata'
    | '__pdata'
    | '__code'
    | '__bit'
    | '__sfr'
    | '__sfr16'
    | '__sfr32'
    | '__sbit'
    | '__banked'
    | '__at'
    | '__asm'
    | '__critical'
    | '__interrupt'
    | '__using'
    | '__reentrant'
    | '__naked'
    | '__wparam'
    | '__shadowregs'
    | '__preserves__regs'
   


$End


