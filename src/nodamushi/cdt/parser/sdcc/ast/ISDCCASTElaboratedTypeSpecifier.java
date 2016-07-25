package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTElaboratedTypeSpecifier;

public interface ISDCCASTElaboratedTypeSpecifier
    extends ISDCCASTDeclSpecifier,ICASTElaboratedTypeSpecifier{
  @Override
  public ISDCCASTElaboratedTypeSpecifier copy();
}
