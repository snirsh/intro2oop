package utilities;

import java.util.regex.Pattern;

/**
 * Created by yossi252 on 6/13/16.
 */
public final class Patterns {

    //private static String varDeclarationStr = "^\\s*(int|double|String|char|boolean)\\s+" +
           // "\\w+\\s*=\\s*([\"'])?\\w+.?\\w*;$\2?";
    private static String varDeclarationStr = "^\\s*(int|double|String|char|boolean)\\s+(\\w+)\\s*[=]\\s*([\"].*[\"]" +
            "|['].?[']|\\d+|true|false|\\d+\\.\\d*);\\s*$";
    private static String methodDecString ="^void\\s+\\w+\\s*( " +
            "\\(\\)|\\(((int|String|boolean|double|char)\\s+\\w+\\s*" +
            "(,\\s*(int|String|boolean|double|char)\\s+\\w+\\s*)*\\)))\\{";
    //private static String methodDecString = "^void\\s+\\w+[(]((int|String|boolean|double|char)" +
            //"\\s+\\w+(,\\s+(int|String|boolean|double|char)\\s+\\w+)*)*[)][{]$";
    private static String existingVarDecStr = "^\\s*(\\w+)\\s*[=]\\s*(\\w+|\\d+|\\w)[;]$";
    private static String ifWhileRegex = "^\\s*(while|if)\\s*[(]\\s*(!?true|!?false|\\w+\\s*(==|!=)" +
            "\\s*\\w+|!?\\w+)\\s*[)]\\s*[{]\\s*$";

    public static final Pattern booleanPattern = Pattern.compile("\\s*(\\w+)\\s*");
    public static final Pattern intPattern = Pattern.compile("\\s*(\\w+)\\s*[=|!][=]\\s*\\d+\\s*");
    public static final Pattern doublePattern = Pattern.compile("\\s*(\\w+)\\s*[=|!][=]\\s*(\\d+|\\d+\\.\\d*)\\s*");
    public static final Pattern strPattern = Pattern.compile("\\s*(\\w+)\\s*[=|!][=]\\s*[\"](.+[\"]|[\"]|\\s+[\"])");
    public static final Pattern chrPattern = Pattern.compile("\\s*(\\w+)\\s*[=|!][=]\\s*[\'](.[\']|[\']|\\s[\'])[\']");
    public static final Pattern varComparisonPattern = Pattern.compile("\\s*(\\w+)\\s*[=|!][=]\\s*(\\w+)\\s*");
    public static final Pattern ifPattern = Pattern.compile(ifWhileRegex);

    public static final Pattern varDeclaration = Pattern.compile(varDeclarationStr);
    public static final Pattern methodDeclaration = Pattern.compile(methodDecString);
    public static final Pattern existingVarDeclaration = Pattern.compile(existingVarDecStr);




}
