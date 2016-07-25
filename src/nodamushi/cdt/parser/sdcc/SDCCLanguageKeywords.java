package nodamushi.cdt.parser.sdcc;

import org.eclipse.cdt.core.dom.parser.CLanguageKeywords;
import org.eclipse.cdt.core.dom.parser.IScannerExtensionConfiguration;
import org.eclipse.cdt.core.parser.ParserLanguage;

public class SDCCLanguageKeywords extends CLanguageKeywords{

  public SDCCLanguageKeywords(IScannerExtensionConfiguration config){
    super(ParserLanguage.C, config);
  }

  private static final String[] keywords = SDCCKeyword.getAllKeywords();
  @Override
  public String[] getKeywords(){
    return keywords;
  }

  private String[] types;

  static volatile int __bit_index;
  static volatile int __sbit_index;

  @Override
  public String[] getBuiltinTypes(){
    if(this.types == null){
      String[] btypes = super.getBuiltinTypes();
      String[] arr = new String[btypes.length+2];
      System.arraycopy(btypes, 0, arr, 0, btypes.length);
      arr[btypes.length] = SDCCKeyword.__bit.name();
      arr[btypes.length+1] = SDCCKeyword.__sbit.name();
      __bit_index = btypes.length;
      __sbit_index = btypes.length+1;
      this.types = arr;
    }
    return types;
  }


}
