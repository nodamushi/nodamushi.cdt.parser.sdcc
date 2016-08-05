package nodamushi.cdt.parser.sdcc;

import java.util.Map;

import org.eclipse.cdt.core.dom.ILinkage;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.lrparser.BaseExtensibleLanguage;
import org.eclipse.cdt.core.dom.lrparser.IParser;
import org.eclipse.cdt.core.dom.lrparser.ScannerExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.IBuiltinBindingsProvider;
import org.eclipse.cdt.core.dom.parser.IScannerExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.c.ANSICParserExtensionConfiguration;
import org.eclipse.cdt.core.index.IIndex;
import org.eclipse.cdt.core.model.ICLanguageKeywords;
import org.eclipse.cdt.core.parser.IScanner;
import org.eclipse.cdt.core.parser.ParserLanguage;

import nodamushi.internal.cdt.parser.sdcc.DOMToSDCCTokenMap;
import nodamushi.internal.cdt.parser.sdcc.SDCCLanguageKeywords;
import nodamushi.internal.cdt.parser.sdcc.SDCCParser;

/**
 * SDCCの方言にある程度対応したC99ベースの言語
 * @author nodamushi
 *
 */
public class SDCCLanguage extends BaseExtensibleLanguage{
  /**プラグインID*/
  public static final String PLUGIN_ID = "nodamushi.cdt.parser.sdcc";//$NON-NLS-1$
  /**Language ID(プラグインIDは含まない)*/
  public static final String LANGUAGE_ID = "nodamushi_sdcc"; //$NON-NLS-1$
  /**PLUGIN_ID.LANGUAGE_ID */
  public static final String ID = PLUGIN_ID+"."+LANGUAGE_ID; //$NON-NLS-1$


  private static final DOMToSDCCTokenMap TOKEN_MAP = new DOMToSDCCTokenMap();
  @Override
  protected IParser<IASTTranslationUnit> getParser(IScanner scanner, IIndex index, Map<String,String> properties) {
    return new SDCCParser(scanner, TOKEN_MAP, getBuiltinBindingsProvider(), index, properties);
  }

  @Override
  public String getId() {
    return ID;
  }

  @Override
  public int getLinkageID() {
    return ILinkage.C_LINKAGE_ID;
  }



  private static final ICLanguageKeywords sdccKeywords =
      new SDCCLanguageKeywords(ScannerExtensionConfiguration.createC());

  @Override @SuppressWarnings("rawtypes")
  public Object getAdapter(Class adapter) {
    if(ICLanguageKeywords.class.equals(adapter))
      return sdccKeywords;

    return super.getAdapter(adapter);
  }

  @Override
  protected ParserLanguage getParserLanguage() {
    return ParserLanguage.C;
  }

  @Override
  protected IScannerExtensionConfiguration getScannerExtensionConfiguration() {
    return ScannerExtensionConfiguration.createC();
  }

  protected IBuiltinBindingsProvider getBuiltinBindingsProvider() {
    return new ANSICParserExtensionConfiguration().getBuiltinBindingsProvider();
  }

}
