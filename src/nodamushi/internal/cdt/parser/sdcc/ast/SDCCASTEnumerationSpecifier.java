package nodamushi.internal.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTToken;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTEnumerationSpecifier;

import nodamushi.cdt.parser.sdcc.ast.ISDCCASTEnumerationSpecifier;

@SuppressWarnings("restriction")
public class SDCCASTEnumerationSpecifier extends CASTEnumerationSpecifier
implements ISDCCASTEnumerationSpecifier{


  public SDCCASTEnumerationSpecifier(){
  }

  public SDCCASTEnumerationSpecifier(IASTName name){
    super(name);
  }



  private int addrspace=as_no_space,
      banked =ba_no_banked;
  private IASTToken addr = null;


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
  public IASTToken getAddress(){
    return addr;
  }

  @Override
  public void setAddress(IASTToken addressToken){
    assertNotFrozen();
    this.addr = addressToken;
  }

  @Override
  public int getBanked(){
    return banked;
  }

  @Override
  public void setBanked(int banked){
    assertNotFrozen();
    this.banked = banked;
  }

  @Override
  public SDCCASTEnumerationSpecifier copy(){
    return copy(CopyStyle.withoutLocations);
  }

  @Override
  public SDCCASTEnumerationSpecifier copy(CopyStyle style){
    SDCCASTEnumerationSpecifier copy = new SDCCASTEnumerationSpecifier();
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

    IASTEnumerator[] etors = getEnumerators();
    for ( int i = 0; i < etors.length; i++ ) {
      if( !etors[i].accept( action ) ) return false;
    }
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
