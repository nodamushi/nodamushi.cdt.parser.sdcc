package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTEnumerationSpecifier;

public interface ISDCCASTEnumerationSpecifier extends
ISDCCASTDeclSpecifier,ICASTEnumerationSpecifier{
  @Override
  public ISDCCASTEnumerationSpecifier copy();

}
