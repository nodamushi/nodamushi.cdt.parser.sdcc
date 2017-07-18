package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.IASTAttribute;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTToken;

/**
 * __interrupt,__naked,ect
 * @author nodamushi
 * @since 1.1.0
 */
public interface ISDCCASTFunctionAttribute extends IASTAttribute{

  /**
   * Returns the name of the attribute.
   */
  public char[] getName();

  /**
   * Returns value of this attribute, or {@code null} if the attribute doesn't have value.
   */
  public IASTNode getValue();

  @Deprecated @Override IASTToken getArgumentClause();
  @Deprecated @Override void setArgumentClause(IASTToken argumentClause);
  /**
   * Sets the value of this attribute.
   */
  public void setValue(IASTNode value);

  @Override
  public ISDCCASTFunctionAttribute copy();

  @Override
  public ISDCCASTFunctionAttribute copy(CopyStyle style);
}
