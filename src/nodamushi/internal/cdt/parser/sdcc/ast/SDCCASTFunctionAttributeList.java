package nodamushi.internal.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTAttribute;
import org.eclipse.cdt.core.parser.util.ArrayUtil;
import org.eclipse.cdt.internal.core.dom.parser.ASTNode;

import nodamushi.cdt.parser.sdcc.ast.ISDCCASTFunctionAttribute;
import nodamushi.cdt.parser.sdcc.ast.ISDCCASTFunctionAttributeList;

@SuppressWarnings("restriction")
public class SDCCASTFunctionAttributeList extends ASTNode implements ISDCCASTFunctionAttributeList{

  protected ISDCCASTFunctionAttribute[] attributes = EMPTY_ATTRIBUTE_ARRAY;

  @Override
  public ISDCCASTFunctionAttribute[] getAttributes(){
    attributes = ArrayUtil.trim(attributes);
    return attributes;
  }

  @Override
  public void addAttribute(ISDCCASTFunctionAttribute attribute){
    assertNotFrozen();
    if(attribute!=null){
      attribute.setParent(this);
      attribute.setPropertyInParent(ATTRIBUTE);
      attributes = ArrayUtil.append(attributes, attribute);
    }
  }

  @Override
  public ISDCCASTFunctionAttributeList copy(){
    return copy(CopyStyle.withoutLocations);
  }

  @Override
  public ISDCCASTFunctionAttributeList copy(CopyStyle style){
    return copy(new SDCCASTFunctionAttributeList(),style);
  }


  protected <T extends SDCCASTFunctionAttributeList> T copy(T copy ,CopyStyle style){
    copy.attributes = ArrayUtil.trim(attributes,true);
    for (int i = 0; i < copy.attributes.length; i++) {
      ISDCCASTFunctionAttribute attributeCopy = copy.attributes[i].copy(style);
      attributeCopy.setParent(copy);
      copy.attributes[i] = attributeCopy;
    }
    return super.copy(copy, style);
  }




  @Override
  public boolean accept(ASTVisitor visitor){
    if (visitor.shouldVisitAttributes) {
      switch (visitor.visit(this)) {
      case ASTVisitor.PROCESS_ABORT:
        return false;
      case ASTVisitor.PROCESS_SKIP:
        return true;
      }
    }

    for (IASTAttribute attribute : getAttributes()) {
      if (!attribute.accept(visitor))
        return false;
    }

    if (visitor.shouldVisitAttributes && visitor.leave(this) == ASTVisitor.PROCESS_ABORT)
      return false;

    return true;
  }

  @Override @Deprecated
  public void addAttribute(IASTAttribute attribute)throws ClassCastException{
    addAttribute(ISDCCASTFunctionAttribute.class.cast(attribute));
  }
}
