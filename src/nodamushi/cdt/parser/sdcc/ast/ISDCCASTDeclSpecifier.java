package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTToken;

public interface ISDCCASTDeclSpecifier extends IASTDeclSpecifier{
  //TODO

  public static final int as_no_space = 0;
  public static final int as_data = 1;
  public static final int as_near = 2;
  public static final int as_xdata = 3;
  public static final int as_far = 4;
  public static final int as_idata = 5;
  public static final int as_pdata = 6;
  public static final int as_code = 7;
  public static final int as_bit = 8;
  public static final int as_sfr = 9;
  public static final int as_sfr16 = 10;
  public static final int as_sfr32 = 11;
  public static final int as_sbit = 12;


  public static final int ba_no_banked = 0;
  public static final int ba_banked = 1;

  public int getAddressSpace();
  public void setAddressSpace(int addressSpace);


  public IASTToken getAddress();
  public void setAddress(IASTToken addressToken);

  public int getBanked();
  public void setBanked(int banked);


  @Override
  public ISDCCASTDeclSpecifier copy();
}
