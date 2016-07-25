package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTTypedefNameSpecifier;

public interface ISDCCASTTypedefNameSpecifier extends ICASTTypedefNameSpecifier,ISDCCASTDeclSpecifier{
  @Override
  public ISDCCASTTypedefNameSpecifier copy();
}
