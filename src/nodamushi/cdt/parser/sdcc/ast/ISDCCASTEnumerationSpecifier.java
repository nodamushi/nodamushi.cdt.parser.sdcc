package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTEnumerationSpecifier;
/**
 *  __xdataなどや__atを保存できるようにしたもの。
 * @author nodamushi
 *
 */
public interface ISDCCASTEnumerationSpecifier extends
ISDCCASTDeclSpecifier,ICASTEnumerationSpecifier{
  @Override
  public ISDCCASTEnumerationSpecifier copy();

}
