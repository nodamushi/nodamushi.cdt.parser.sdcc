package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTTypedefNameSpecifier;
/**
 *  __xdataなどや__atを保存できるようにしたもの。
 * @author nodamushi
 *
 */
public interface ISDCCASTTypedefNameSpecifier extends ICASTTypedefNameSpecifier,ISDCCASTDeclSpecifier{
  @Override
  public ISDCCASTTypedefNameSpecifier copy();
}
