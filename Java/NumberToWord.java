import java.text.DecimalFormat;

public class NumberToWord{

  private static final String[] tensNames = {
    "",
    " ten",
    " twenty",
    " thirty",
    " forty",
    " fifty",
    " sixty",
    " seventy",
    " eighty",
    " ninety"
  };

  private static final String[] numNames = {
    "",
    " one",
    " two",
    " three",
    " four",
    " five",
    " six",
    " seven",
    " eight",
    " nine",
    " ten",
    " eleven",
    " twelve",
    " thirteen",
    " fourteen",
    " fifteen",
    " sixteen",
    " seventeen",
    " eighteen",
    " nineteen"
  };

  private NumberToWord() {}

  private static String convertLessThanOneThousand(int number) {
    String soFar;

    if (number % 100 < 20){
      soFar = numNames[number % 100];
      number /= 100;
    }
    else {
      soFar = numNames[number % 10];
      number /= 10;

      soFar = tensNames[number % 10] + soFar;
      number /= 10;
    }
    if (number == 0) return soFar;
    return numNames[number] + " hundred" + soFar;
  }


  public static String convert(double number) {
    // 0 to 999 999 999 999
    if (number == 0) { return "zero"; }

    String snumber = Double.toString(number);

    // pad with "0"
    String mask = "000000000000.00";
    DecimalFormat df = new DecimalFormat(mask);
    snumber = df.format(number);
    // XXXXX,nn,nn,nnn
    int Crores = Integer.parseInt(snumber.substring(0,5));
   
    // nnnnn,XX,nn,nnn
    int Lakhs  = Integer.parseInt(snumber.substring(5,7));
    
    // nnnnn,nn,XX,nnn
    int Thousands = Integer.parseInt(snumber.substring(7,9));
    
    // nnnnn,nn,nn,XYZ 
    int hundreds = Integer.parseInt(snumber.substring(9,12));
    
    
    
    int paise = Integer.parseInt(snumber.substring(13,15));
    
    String strCrores;
    switch (Crores) {
    case 0:
    	strCrores = "";
      break;
    case 1 :
    	strCrores = convertLessThanOneThousand(Crores)
      + " crore ";
      break;
    default :
    	strCrores = convertLessThanOneThousand(Crores)
      + " crore ";
    }
    String result =  strCrores;

    String strLakhs;
    switch (Lakhs) {
    case 0:
    	strLakhs = "";
      break;
    case 1 :
    	strLakhs = convertLessThanOneThousand(Lakhs)
         + " lakh ";
      break;
    default :
    	strLakhs = convertLessThanOneThousand(Lakhs)
         + " lakh ";
    }
    result =  result + strLakhs;

    String strThousands;
    switch (Thousands) {
    case 0:
    	strThousands = "";
      break;
    case 1 :
    	strThousands = "one thousand ";
      break;
    default :
    	strThousands = convertLessThanOneThousand(Thousands)
         + " thousand ";
    }
    result =  result + strThousands;
    
    
    String strHundreds;
    strHundreds = convertLessThanOneThousand(hundreds);
    result =  result + strHundreds;
    
    
    String strPaise="";
    switch (paise) {
    case 0:
    	strPaise = " and zero paise only";
      break;
    default :
    	strPaise = " and "+convertLessThanOneThousand(paise)+" paise only";
   }  
    result =  result + strPaise;
    // remove extra spaces!
    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
  }
  
  public static void main(String[] args) {
	  //Example:- 9999999999D
    //Put D at the end to avoid out of range error
    System.out.println(NumberToWord.convert(9999999999D));
    //Output:-
	  //nine hundred ninety nine crore ninety nine lakh ninety nine thousand nine hundred ninety nine and zero paise only
  }
}
