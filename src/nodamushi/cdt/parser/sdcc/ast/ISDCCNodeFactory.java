package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.c.ICNodeFactory;

public interface ISDCCNodeFactory extends ICNodeFactory{


  public ISDCCASTAsmStatement createAsmStatement();

  @Override
  public ISDCCASTSimpleDeclSpecifier newSimpleDeclSpecifier();

  @Override
  public ISDCCASTCompositeTypeSpecifier newCompositeTypeSpecifier(int key,
      IASTName name);

  @Override
  public ISDCCASTElaboratedTypeSpecifier newElaboratedTypeSpecifier(int kind,
      IASTName name);

  @Override
  public ISDCCASTEnumerationSpecifier newEnumerationSpecifier(IASTName name);

  @Override
  public ISDCCASTTypedefNameSpecifier newTypedefNameSpecifier(IASTName name);

}
