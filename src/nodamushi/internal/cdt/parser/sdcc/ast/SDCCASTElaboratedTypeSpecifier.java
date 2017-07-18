package nodamushi.internal.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTLiteralExpression;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTElaboratedTypeSpecifier;

import nodamushi.cdt.parser.sdcc.ast.ISDCCASTElaboratedTypeSpecifier;

@SuppressWarnings("restriction")
public class SDCCASTElaboratedTypeSpecifier extends CASTElaboratedTypeSpecifier
implements ISDCCASTElaboratedTypeSpecifier{

  public SDCCASTElaboratedTypeSpecifier() {
  }

  public SDCCASTElaboratedTypeSpecifier(int kind, IASTName name) {
    super(kind, name);
  }


  private int addrspace=as_no_space;
  private boolean banked =false;
  private IASTExpression addr = null;


  @Override
  public int getAddressSpace(){
    return addrspace;
  }

  @Override
  public void setAddressSpace(int addressSpace){
    assertNotFrozen();
    this.addrspace = addressSpace;
  }

  @Override
  public IASTExpression getAddressExpression(){
    return addr;
  }

  @Override @Deprecated public IASTLiteralExpression getAddress() throws ClassCastException{
    return IASTLiteralExpression.class.cast(getAddressExpression());
  }

  @Override
  public void setAddress(IASTExpression addressToken){
    assertNotFrozen();
    this.addr = addressToken;
  }

  @Override
  public boolean getBanked(){
    return banked;
  }

  @Override
  public void setBanked(boolean banked){
    assertNotFrozen();
    this.banked = banked;
  }

  @Override
  public SDCCASTElaboratedTypeSpecifier copy(){
    return copy(CopyStyle.withoutLocations);
  }

  @Override
  public SDCCASTElaboratedTypeSpecifier copy(CopyStyle style){
    IASTName name = getName();
    SDCCASTElaboratedTypeSpecifier copy =
        new SDCCASTElaboratedTypeSpecifier(getKind(),name == null?
            null:name.copy(style));

    return copy(copy,style);
  }

  protected <T extends SDCCASTElaboratedTypeSpecifier> T copy(T copy ,CopyStyle style){
    copy.addr = addr == null? null:addr.copy(style);
    copy.addrspace = addrspace;
    copy.banked = banked;
    return super.copy(copy, style);
  }


  @Override
  public boolean accept( ASTVisitor action ){
    if( action.shouldVisitDeclSpecifiers ){
      switch( action.visit( this ) ){
        case ASTVisitor.PROCESS_ABORT : return false;
        case ASTVisitor.PROCESS_SKIP  : return true;
        default : break;
      }
    }
    if( getName() != null ) if( !getName().accept( action ) ) return false;
    if( addr != null) if( !addr.accept( action ) ) return false;

    if( action.shouldVisitDeclSpecifiers ){
      switch( action.leave( this ) ){
        case ASTVisitor.PROCESS_ABORT : return false;
        case ASTVisitor.PROCESS_SKIP  : return true;
        default : break;
      }
    }
    return true;
  }



}
