package nodamushi.cdt.parser.sdcc.ast;

import org.eclipse.cdt.core.dom.ast.IASTDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTLiteralExpression;
/**
 * __xdataなどや__atを保存できるようにしたもの。
 * @author nodamushi
 *
 */
public interface ISDCCASTDeclSpecifier extends IASTDeclSpecifier{
  /**アドレス空間が設定されていないデフォルト値*/
  public static final int as_no_space = 0;
  /**__data*/
  public static final int as_data = 1;
  /**__near*/
  public static final int as_near = 2;
  /**__xdata*/
  public static final int as_xdata = 3;
  /**__far*/
  public static final int as_far = 4;
  /**__idata*/
  public static final int as_idata = 5;
  /**__pdata*/
  public static final int as_pdata = 6;
  /**__code*/
  public static final int as_code = 7;
  /**__bit*/
  public static final int as_bit = 8;
  /**__sfr*/
  public static final int as_sfr = 9;
  /**__sfr16*/
  public static final int as_sfr16 = 10;
  /**__sfr32*/
  public static final int as_sfr32 = 11;
  /**__sbit*/
  public static final int as_sbit = 12;
  /**
   * アドレス空間を指定する__xdataなどの指示のタイプを返す。<br>
   * {@link ISDCCASTDeclSpecifier#as_xdata}などのas_から始まる定数を参照。
   * @return
   */
  public int getAddressSpace();
  public void setAddressSpace(int addressSpace);
  /**
   * __at(整数)の整数部分を表す {@link IASTExpression}を返す。<br>
   * @return {@link IASTExpression}
   */
  public IASTExpression getAddressExpression();
  /**
   * {@link #getAddressExpression()}を利用すること
   * @return {@link #getAddressExpression()}を{@link IASTLiteralExpression}にキャストした結果
   * @deprecated 1.1.0
   */
  @Deprecated public IASTLiteralExpression getAddress()throws ClassCastException;
  public void setAddress(IASTExpression addressToken);
  /**
   * __bankedが付加されているかどうか
   * @return
   */
  public boolean getBanked();
  public void setBanked(boolean banked);

  @Override
  public ISDCCASTDeclSpecifier copy();
}
