package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.c.ICASTSimpleDeclSpecifier;
/**
 *  __xdataなどや__atを保存できるようにしたもの。
 * @author nodamushi
 *
 */
public interface ISDCCASTSimpleDeclSpecifier extends ICASTSimpleDeclSpecifier,ISDCCASTDeclSpecifier{
  @Override
  public ISDCCASTSimpleDeclSpecifier copy();
}
