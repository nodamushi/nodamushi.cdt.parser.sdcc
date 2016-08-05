package nodamushi.cdt.parser.sdcc.ast;


import org.eclipse.cdt.core.dom.ast.IASTStatement;

/**
 * __asm,__endasmで囲まれた範囲
 * @author nodamushi
 *
 */
public interface ISDCCASTAsmStatement extends IASTStatement{
  @Override
  public ISDCCASTAsmStatement copy();
  @Override
  public ISDCCASTAsmStatement copy(CopyStyle style);

}
