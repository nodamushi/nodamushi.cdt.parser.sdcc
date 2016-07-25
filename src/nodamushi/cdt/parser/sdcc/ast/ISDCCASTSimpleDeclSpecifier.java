package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTSimpleDeclSpecifier;

public interface ISDCCASTSimpleDeclSpecifier extends ICASTSimpleDeclSpecifier,ISDCCASTDeclSpecifier{
  @Override
  public ISDCCASTSimpleDeclSpecifier copy();
}
