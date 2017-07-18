package nodamushi.internal.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTToken;
import org.eclipse.cdt.internal.core.dom.parser.ASTNode;

import nodamushi.cdt.parser.sdcc.ast.ISDCCASTFunctionAttribute;

@SuppressWarnings("restriction")
public class SDCCASTFunctionAttribute extends ASTNode implements ISDCCASTFunctionAttribute{

  private char[] name;
  private IASTNode value;

  public SDCCASTFunctionAttribute(char[] name){
    this.name = name==null?new char[0]:name;
  }

  @Override
  public char[] getName(){
    return name;
  }

  @Override
  public IASTNode getValue(){
    return value;
  }

  @Override
  public void setValue(IASTNode value){
    assertNotFrozen();
    this.value = value;
  }

  @Override
  public ISDCCASTFunctionAttribute copy(){
    return copy(CopyStyle.withoutLocations);
  }

  @Override
  public ISDCCASTFunctionAttribute copy(CopyStyle style){
    SDCCASTFunctionAttribute copy = new SDCCASTFunctionAttribute(name);
    return copy(copy,style);
  }


  protected <T extends SDCCASTFunctionAttribute> T copy(T copy ,CopyStyle style){
    copy.value = value==null?null:value.copy(style);
    return super.copy(copy, style);
  }

  @Override @Deprecated
  public IASTToken getArgumentClause(){
    IASTNode n = getValue();
    return n instanceof IASTToken? (IASTToken)n:null;
  }

  @Override @Deprecated
  public void setArgumentClause(IASTToken argumentClause){
    setValue(argumentClause);
  }

}
