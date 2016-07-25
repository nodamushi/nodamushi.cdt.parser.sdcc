package nodamushi.internal.cdt.parser.sdcc.ast;


import org.eclipse.cdt.internal.core.dom.parser.ASTAttributeOwner;

import nodamushi.cdt.parser.sdcc.ast.ISDCCASTAsmStatement;

@SuppressWarnings("restriction")
public class SDCCASTAsmStatement extends ASTAttributeOwner
implements ISDCCASTAsmStatement{


  @Override
  public SDCCASTAsmStatement copy(){
    return copy(CopyStyle.withoutLocations);
  }

  @Override
  public SDCCASTAsmStatement copy(CopyStyle style){
    return copy(new SDCCASTAsmStatement(),style);
  }

}
