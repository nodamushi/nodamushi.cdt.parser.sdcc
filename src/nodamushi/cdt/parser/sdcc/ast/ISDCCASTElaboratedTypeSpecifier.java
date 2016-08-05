package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTElaboratedTypeSpecifier;
/**
 *  __xdataなどや__atを保存できるようにしたもの。
 * @author nodamushi
 *
 */
public interface ISDCCASTElaboratedTypeSpecifier
    extends ISDCCASTDeclSpecifier,ICASTElaboratedTypeSpecifier{
  @Override
  public ISDCCASTElaboratedTypeSpecifier copy();
}
