package nodamushi.internal.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.internal.core.dom.parser.c.CNodeFactory;

import nodamushi.cdt.parser.sdcc.ast.ISDCCASTAsmStatement;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTCompositeTypeSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTElaboratedTypeSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTEnumerationSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTSimpleDeclSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTTypedefNameSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCNodeFactory;

@SuppressWarnings("restriction")
public class SDCCASTNodeFactory extends CNodeFactory implements ISDCCNodeFactory{


  @Override
  public ISDCCASTAsmStatement createAsmStatement(){
    return new SDCCASTAsmStatement();
  }

  @Override
  public ISDCCASTSimpleDeclSpecifier newSimpleDeclSpecifier(){
    return new SDCCASTSimpleDeclSpecifier();
  }

  @Override
  public ISDCCASTCompositeTypeSpecifier newCompositeTypeSpecifier(int key ,
      IASTName name){
    return new SDCCASTCompositeTypeSpecifier(key, name);
  }

  @Override
  public ISDCCASTElaboratedTypeSpecifier newElaboratedTypeSpecifier(int kind ,
      IASTName name){
    return new SDCCASTElaboratedTypeSpecifier(kind, name);
  }

  @Override
  public ISDCCASTEnumerationSpecifier newEnumerationSpecifier(IASTName name){
    return new SDCCASTEnumerationSpecifier(name);
  }

  @Override
  public ISDCCASTTypedefNameSpecifier newTypedefNameSpecifier(IASTName name){
    return new SDCCASTTypedefNameSpecifier(name);
  }
}
