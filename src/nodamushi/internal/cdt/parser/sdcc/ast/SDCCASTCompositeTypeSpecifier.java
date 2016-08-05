package nodamushi.internal.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTLiteralExpression;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTCompositeTypeSpecifier;

import nodamushi.cdt.parser.sdcc.ast.ISDCCASTCompositeTypeSpecifier;

@SuppressWarnings("restriction")
public class SDCCASTCompositeTypeSpecifier extends CASTCompositeTypeSpecifier
implements ISDCCASTCompositeTypeSpecifier{
  public SDCCASTCompositeTypeSpecifier() {
  }

  public SDCCASTCompositeTypeSpecifier(int key, IASTName name) {
    super(key, name);
  }

  private int addrspace=as_no_space;
  private boolean  banked =false;
  private IASTLiteralExpression addr = null;


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
  public IASTLiteralExpression getAddress(){
    return addr;
  }

  @Override
  public void setAddress(IASTLiteralExpression addressToken){
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
  public SDCCASTCompositeTypeSpecifier copy(){
    return copy(CopyStyle.withoutLocations);
  }

  @Override
  public SDCCASTCompositeTypeSpecifier copy(CopyStyle style){
    SDCCASTCompositeTypeSpecifier copy = new SDCCASTCompositeTypeSpecifier();
    copy.addr = addr == null? null:addr.copy(style);
    copy.addrspace = addrspace;
    copy.banked = banked;
    return copy(copy,style);
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
    IASTDeclaration [] decls = getMembers();
    for( int i = 0; i < decls.length; i++ )
      if( !decls[i].accept( action ) ) return false;

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
