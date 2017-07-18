package nodamushi.internal.cdt.parser.sdcc;

import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_bit;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_code;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_data;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_far;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_idata;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_near;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_pdata;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_sbit;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_sfr;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_sfr16;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_sfr32;
import static nodamushi.cdt.parser.sdcc.ast.ISDCCASTDeclSpecifier.as_xdata;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___banked;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___bit;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___code;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___data;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___far;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___idata;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___near;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___pdata;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___sbit;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___sfr;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___sfr16;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___sfr32;
import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.TK___xdata;

import org.eclipse.cdt.core.dom.ast.IASTAttribute;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier;
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
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTFunctionAttribute;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTFunctionAttributeList;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTSimpleDeclSpecifier;
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
    ISDCCASTFunctionAttributeList list = nodeFactory.newFunctionAttributeList();

    for(Object o:astStack.closeScope()){
      if(o instanceof ISDCCASTFunctionAttribute){
        list.addAttribute((ISDCCASTFunctionAttribute)o);
      }
    }

    Object pop = astStack.pop();
    IASTDeclarator decl = (IASTDeclarator)pop;

    decl.addAttributeSpecifier(list);
    setOffsetAndLength(decl);
    astStack.push(decl);
  }

  public void consumeSDCCFunctionAttribute(SDCCKeyword attrType,boolean hasData){
    char[] image = attrType.getImage();
    ISDCCASTFunctionAttribute attr = nodeFactory.newFunctionAttribute(image);

    if(hasData){
      attr.setValue((IASTNode)astStack.pop());
    }

    setOffsetAndLength(attr);
    astStack.push(attr);
  }




  public void consumeSDCCPreservesRegsAttribute(boolean hasArgs){
    ISDCCASTFunctionAttribute attr = nodeFactory.newFunctionAttribute(SDCCKeyword.__preserves__regs.getImage());

    if(hasArgs){
      IASTTokenList args = nodeFactory.newTokenList();
      for(Object o:astStack.closeScope()){
        IASTToken t = (IASTToken)o;
        args.addToken(t);
      }
      attr.setValue(args);
    }
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

  //IASTLiteralExpressionを区別するためのクラス。
  private static class AddrContainer{
    IASTExpression t;
  }

  public void consumeIntegerToken(){
    IASTToken token = nodeFactory.newToken(org.eclipse.cdt.core.parser.IToken.tINTEGER, stream.getRightIToken().toString().toCharArray());
    setOffsetAndLength(token);
    astStack.push(token);
  }

  /*
  define_address
    ::= '__at' '(' absolute_address ')'
         /. $Build  consumeDefineAddress();  $EndBuild ./
      | '__at' absolute_address
         /. $Build  consumeDefineAddress();  $EndBuild ./
   */
  public void consumeDefineAddress(){
    IASTExpression addr =(IASTExpression)astStack.pop();
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
          if(node instanceof ISDCCASTSimpleDeclSpecifier){
            ISDCCASTSimpleDeclSpecifier n = (ISDCCASTSimpleDeclSpecifier)node;
            n.setUnsigned(true);
          }
          node2.setAddressSpace(as_bit);
          break;
        case TK___sfr:
          if(node instanceof ISDCCASTSimpleDeclSpecifier){
            ISDCCASTSimpleDeclSpecifier n = (ISDCCASTSimpleDeclSpecifier)node;
            n.setType(IASTSimpleDeclSpecifier.t_char);
            n.setUnsigned(true);
          }
          node2.setAddressSpace(as_sfr);break;
        case TK___sfr16:
          if(node instanceof ISDCCASTSimpleDeclSpecifier){
            ISDCCASTSimpleDeclSpecifier n = (ISDCCASTSimpleDeclSpecifier)node;
            n.setShort(true);
            n.setUnsigned(true);
          }
          node2.setAddressSpace(as_sfr16);break;
        case TK___sfr32:
          if(node instanceof ISDCCASTSimpleDeclSpecifier){
            ISDCCASTSimpleDeclSpecifier n = (ISDCCASTSimpleDeclSpecifier)node;
            n.setType(IASTSimpleDeclSpecifier.t_int);
            n.setUnsigned(true);
          }
          node2.setAddressSpace(as_sfr32);break;
        case TK___sbit:
          if(node instanceof ISDCCASTSimpleDeclSpecifier){
            ISDCCASTSimpleDeclSpecifier n = (ISDCCASTSimpleDeclSpecifier)node;
            n.setUnsigned(true);
          }
          node2.setAddressSpace(as_sbit);
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
