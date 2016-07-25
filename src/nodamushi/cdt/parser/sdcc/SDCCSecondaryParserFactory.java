package nodamushi.cdt.parser.sdcc;

import java.util.Map;

import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.lrparser.ISecondaryParser;
import org.eclipse.cdt.core.dom.lrparser.action.ISecondaryParserFactory;
import org.eclipse.cdt.core.dom.lrparser.action.ITokenStream;

import nodamushi.internal.cdt.parser.sdcc.SDCCExpressionParser;
import nodamushi.internal.cdt.parser.sdcc.SDCCNoCastExpressionParser;
import nodamushi.internal.cdt.parser.sdcc.SDCCSizeofExpressionParser;

public class SDCCSecondaryParserFactory implements ISecondaryParserFactory{

  private static final SDCCSecondaryParserFactory DEFAULT = new SDCCSecondaryParserFactory();

  public static SDCCSecondaryParserFactory getDefault(){
    return DEFAULT;
  }

  @Override
  public ISecondaryParser<IASTExpression> getExpressionParser(
      ITokenStream stream ,Map<String, String> properties){
    return new SDCCExpressionParser(stream, properties);
  }

  @Override
  public ISecondaryParser<IASTExpression> getNoCastExpressionParser(
      ITokenStream stream ,Map<String, String> properties){
    return new SDCCNoCastExpressionParser(stream, properties);
  }

  @Override
  public ISecondaryParser<IASTExpression> getSizeofExpressionParser(
      ITokenStream stream ,Map<String, String> properties){
    return new SDCCSizeofExpressionParser(stream, properties);
  }

}
