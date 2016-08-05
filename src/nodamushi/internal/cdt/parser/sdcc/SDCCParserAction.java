package nodamushi.internal.cdt.parser.sdcc;

import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.*;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.*;

import org.eclipse.cdt.core.dom.ast.IASTAttribute;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTLiteralExpression;
import org.eclipse.cdt.core.dom.ast.IASTToken;
import org.eclipse.cdt.core.dom.ast.IASTTokenList;
import org.eclipse.cdt.core.dom.ast.c.ICASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.gnu.IGCCASTAttributeList;
import org.eclipse.cdt.core.dom.lrparser.action.ISecondaryParserFactory;
import org.eclipse.cdt.core.dom.lrparser.action.ITokenStream;
import org.eclipse.cdt.core.dom.lrparser.action.ScopedStack;
import org.eclipse.cdt.core.dom.lrparser.action.c99.C99BuildASTParserAction;

import lpg.lpgjavaruntime.IToken;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTAsmStatement;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCNodeFactory;

public class SDCCParserAction extends C99BuildASTParserAction{



  private ISDCCNodeFactory nodeFactory;
  public SDCCParserAction(ITokenStream parser, ScopedStack<Object> astStack,
      ISDCCNodeFactory nodeFactory, ISecondaryParserFactory parserFactory){
    super(parser, astStack, nodeFactory, parserFactory);
    this.nodeFactory = nodeFactory;
  }


  @Override
  protected boolean isCompletionToken(IToken token){
    return token.getKind() == SDCCParsersym.TK_Completion;
  }
  @Override
  protected boolean isIdentifierToken(IToken token){
    return token.getKind() == SDCCParsersym.TK_identifier;
  }



  public void consumeSDCCFunctionAttributes(){
    IGCCASTAttributeList list = nodeFactory.newGCCAttributeList();
    for(Object o:astStack.closeScope()){
      list.addAttribute((IASTAttribute)o);
    }

    Object pop = astStack.pop();
    IASTDeclarator decl = (IASTDeclarator)pop;

    decl.addAttributeSpecifier(list);
    setOffsetAndLength(decl);
    astStack.push(decl);
  }

  public void consumeSDCCFunctionAttribute(SDCCKeyword attrType,boolean hasData){
    char[] image = attrType.getImage();
    IASTToken value=null;
    if(hasData){
      value = (IASTToken) astStack.pop();
    }
    IASTAttribute attr = nodeFactory.newAttribute(image, value);
    setOffsetAndLength(attr);
    astStack.push(attr);
  }



  public void consumeSDCCPreservesRegsAttribute(boolean hasArgs){
    IASTTokenList args = null;
    if(hasArgs){
      args = nodeFactory.newTokenList();
      for(Object o:astStack.closeScope()){
        IASTToken t = (IASTToken)o;
        args.addToken(t);
      }
    }
    IASTAttribute attr = nodeFactory.newAttribute(SDCCKeyword.__preserves__regs.getImage(), args);
    setOffsetAndLength(attr);
    astStack.push(attr);
  }


  public void consumeSDCCPreservesRegsArg(){
    IASTToken token = nodeFactory.newToken(org.eclipse.cdt.core.parser.IToken.tIDENTIFIER, stream.getRightIToken().toString().toCharArray());
    setOffsetAndLength(token);
    astStack.push(token);
  }


  public void consumeCriticalAttribute(){
    IASTCompoundStatement state = (IASTCompoundStatement) astStack.pop();
    IASTAttribute attr = nodeFactory.newAttribute(SDCCKeyword.__critical.getImage(), null);
    IGCCASTAttributeList list = nodeFactory.newGCCAttributeList();
    list.addAttribute(attr);
    state.addAttributeSpecifier(list);
    setOffsetAndLength(state);
    astStack.push(state);
  }

  public void consumeSDCCOldAsmStatement(boolean hasState){
    if(hasState){
      astStack.closeScope();
    }
    ISDCCASTAsmStatement asm = nodeFactory.createAsmStatement();
    setOffsetAndLength(asm);
    astStack.push(asm);
  }

  public void consumeASTToken(){
    // アセンブラ部分のトークンを保持してもあまり意味ないので何もしないことにした
  }

  public void consumeAbsoluteAddress(){
    consumeExpressionLiteral(IASTLiteralExpression.lk_integer_constant);
  }

  //IASTLiteralExpressionを区別するためのクラス。
  private static class AddrContainer{
    IASTLiteralExpression t;
  }

  /*
  define_address
    ::= '__at' '(' absolute_address ')'
         /. $Build  consumeDefineAddress();  $EndBuild ./
      | '__at' absolute_address
         /. $Build  consumeDefineAddress();  $EndBuild ./
   */
  public void consumeDefineAddress(){
    IASTLiteralExpression addr =(IASTLiteralExpression)astStack.pop();
    AddrContainer c = new AddrContainer();
    c.t = addr;
    setOffsetAndLength(addr);
    astStack.push(c);
  }

  public void consumeAddressSpaceName(){
    consumeToken();
  }

  @Override
  public void setSpecifier(ICASTDeclSpecifier node ,Object specifier){
    ISDCCASTDeclSpecifier node2 = (ISDCCASTDeclSpecifier) node;

    if(specifier instanceof IToken){
      IToken token = (IToken)specifier;
      switch(token.getKind()){
        case TK___banked:
          node2.setBanked(true);break;
        case TK___data:
          node2.setAddressSpace(as_data);break;
        case TK___near:
          node2.setAddressSpace(as_near);break;
        case TK___xdata:
          node2.setAddressSpace(as_xdata);break;
        case TK___far:
          node2.setAddressSpace(as_far);break;
        case TK___idata:
          node2.setAddressSpace(as_idata);break;
        case TK___pdata:
          node2.setAddressSpace(as_pdata);break;
        case TK___code:
          node2.setAddressSpace(as_code);break;
        case TK___bit:
          node2.setAddressSpace(as_bit);
          // TODO setBuiltinType
          break;
        case TK___sfr:
          node2.setAddressSpace(as_sfr);break;
        case TK___sfr16:
          node2.setAddressSpace(as_sfr16);break;
        case TK___sfr32:
          node2.setAddressSpace(as_sfr32);break;
        case TK___sbit:
          node2.setAddressSpace(as_sbit);
          // TODO setBuiltinType
          break;
        default:
          super.setSpecifier(node, specifier);
      }

    }else if(specifier instanceof AddrContainer){
      AddrContainer ac= (AddrContainer) specifier;
      node2.setAddress(ac.t);
    }
  }

}
