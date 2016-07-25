package nodamushi.cdt.parser.sdcc;

import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.c.ICNodeFactory;

import nodamushi.cdt.parser.sdcc.ast.ISDCCASTAsmStatement;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTCompositeTypeSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTElaboratedTypeSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTEnumerationSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTSimpleDeclSpecifier;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTTypedefNameSpecifier;

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
