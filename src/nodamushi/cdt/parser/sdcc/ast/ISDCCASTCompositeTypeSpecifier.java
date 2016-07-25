package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTCompositeTypeSpecifier;

public interface ISDCCASTCompositeTypeSpecifier
    extends ICASTCompositeTypeSpecifier,ISDCCASTDeclSpecifier{
  @Override
  public ISDCCASTCompositeTypeSpecifier copy();
  public ISDCCASTCompositeTypeSpecifier copy(CopyStyle styl);
}
