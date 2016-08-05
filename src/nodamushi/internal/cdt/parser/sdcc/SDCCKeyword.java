package nodamushi.internal.cdt.parser.sdcc;

import static nodamushi.internal.cdt.parser.sdcc.SDCCParsersym.*;

import org.eclipse.cdt.core.dom.lrparser.c99.C99Language;
import org.eclipse.cdt.core.model.ICLanguageKeywords;
import org.eclipse.cdt.core.parser.util.CharArrayIntMap;
/**
 *
 * SDCC additional keywords
 *
 * @author nodamushi
 *
 */
public enum SDCCKeyword{

  __data(TK___data),
  __near(TK___near),
  __xdata(TK___xdata),
  __far(TK___far),
  __idata(TK___idata),
  __pdata(TK___pdata),
  __code(TK___code),
  __bit(TK___bit),
  __sfr(TK___sfr),
  __sfr16(TK___sfr16),
  __sfr32(TK___sfr32),
  __sbit(TK___sbit),
  __banked(TK___banked),
  __at(TK___at),
  __interrupt(TK___interrupt),
  __using(TK___using),
  __critical(TK___critical),
  __reentrant(TK___reentrant),
  __asm(TK___asm),
  __endasm(TK___endasm),
  __naked(TK___naked),
  __wparam(TK___wparam),
  __shadowregs(TK___shadowregs),
  __preserves__regs(TK___preserves__regs),


  // additional Keywords
  __asm__(-1)

  ;


  static final int EMPTY_NUMBER = Integer.MIN_VALUE;
  private static final CharArrayIntMap tokenMap;
  private static final String[] sdccKeywords,allKeywords;


  static{
    SDCCKeyword[] values = values();
    final int size = values.length;
    tokenMap = new CharArrayIntMap(size, EMPTY_NUMBER);
    sdccKeywords = new String[size];
    for(int i=0;i<size;i++){
      final SDCCKeyword v = values[i];
      final String name = v.name();
      sdccKeywords[i] = name;
      if(v.tokenKind != -1){
        tokenMap.put(name.toCharArray(), v.tokenKind);
      }
    }
    ICLanguageKeywords c99Keywords = (ICLanguageKeywords) C99Language.getDefault().getAdapter(ICLanguageKeywords.class);
    String[] c99ks = c99Keywords.getKeywords();
    allKeywords = new String[size + c99ks.length];
    System.arraycopy(c99ks, 0, allKeywords, 0, c99ks.length);
    System.arraycopy(sdccKeywords, 0, allKeywords, c99ks.length, size);
  }

  public static int getTokenKind(char[] image){
    if(image == null){
      return EMPTY_NUMBER;
    }else{
      int v = tokenMap.get(image);
      return v;
    }
  }

  public static String[] getSDCCOnlyKeywords(){
    return sdccKeywords;
  }

  public static String[] getAllKeywords(){
    return allKeywords;
  }



  private final int tokenKind;
  private final char[] image;
  private SDCCKeyword(int tokenKind){
    this.tokenKind = tokenKind;
    image = name().toCharArray();
  }

  public int getTokenKind(){
    return tokenKind;
  }


  //package private
  char[] getImage(){
    return image;
  }


}
