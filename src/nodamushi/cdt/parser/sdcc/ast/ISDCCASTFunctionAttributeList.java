package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.ASTNodeProperty;
import org.eclipse.cdt.core.dom.ast.IASTAttribute;
import org.eclipse.cdt.core.dom.ast.IASTAttributeSpecifier;

/**
 * List of {@link ISDCCASTFunctionAttribute}
 * @author nodamushi
 * @since 1.1.0
 */
public interface ISDCCASTFunctionAttributeList extends IASTAttributeSpecifier{
  public static final ASTNodeProperty ATTRIBUTE =
      new ASTNodeProperty("ISDCCASTFunctionAttribute.ATTRIBUTE"); //$NON-NLS-1$
  public static final ISDCCASTFunctionAttribute[] EMPTY_ATTRIBUTE_ARRAY = {};
  /**
   * Returns the attributes in the list.
   */
  public ISDCCASTFunctionAttribute[] getAttributes();

  /**
   * Adds an attribute to the list.
   */
  public void addAttribute( ISDCCASTFunctionAttribute attribute);

  @Deprecated @Override void addAttribute(IASTAttribute attribute)throws ClassCastException;

  @Override public ISDCCASTFunctionAttributeList copy();

  @Override public ISDCCASTFunctionAttributeList copy(CopyStyle style);
}
