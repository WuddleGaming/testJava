import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Comparator;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }
}
class RomanArabicConverter {

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while (number > 0 && i < romanNumerals.size()) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
}

public class Main {
    public static String calc(String input)
    {
        List listArab = new ArrayList();
        List listRim = new ArrayList();
        List listSign = new ArrayList();
        listArab.add('1');
        listArab.add('2');
        listArab.add('3');
        listArab.add('4');
        listArab.add('5');
        listArab.add('6');
        listArab.add('7');
        listArab.add('8');
        listArab.add('9');
        listRim.add('I');
        listRim.add('V');
        listRim.add('X');
        listSign.add('+');
        listSign.add('-');
        listSign.add('*');
        listSign.add('/');
        String ans="";
        String aa="",bb="";
        boolean rim = false, arab = false;
        int another = 0, sign = 0;
        char oper=' ';
        int a=0, b=0, c=0, i=0;
        for (i = 0; i < input.length() && input.charAt(i)!= '+' && input.charAt(i)!= '-' && input.charAt(i)!= '*' && input.charAt(i)!= '/'; i++) {
                if (input.charAt(i) != ' ') aa += input.charAt(i);
                if (listArab.contains(input.charAt(i)))
                    arab = true;
                else
                    if (listRim.contains(input.charAt(i)))
                        rim = true;
                    else if (listSign.contains(input.charAt(i)))
                        sign ++;
                    else if (input.charAt(i) != ' ')
                        another ++;
        }
        if (i < input.length())
        oper = input.charAt(i);
        if (!listSign.contains(oper))
            return "throws Exception";
        else sign++;
        ++i;
        for (i = i; i < input.length(); ++i) {
            if (input.charAt(i) != ' ') bb += input.charAt(i);
            if (listArab.contains(input.charAt(i)))
                arab = true;
            else
            if (listRim.contains(input.charAt(i)))
                rim = true;
            else if (listSign.contains(input.charAt(i)))
                sign ++;
            else if (input.charAt(i) != ' ')
                another ++;
        }
        if (rim && arab)
            return "throws Exception";
        if (another > 0)
            return "throws Exception";
        if (sign > 1)
            return "throws Exception";
        if (arab)
        try {
            a = Integer.parseInt(aa);
            b = Integer.parseInt(bb);
            if (oper == '+')
            {
                c = a+b;
                ans = Integer.toString(c);
            }
            else
            if (oper == '-')
            {
                c = a-b;
                if (c<0)
                    return "throws Exception";
                ans = Integer.toString(c);
            }
            else
            if (oper == '*')
            {
                c = a*b;
                ans = Integer.toString(c);
            }
            else
            if (oper == '/')
            {
                c = a/b;
                ans = Integer.toString(c);
            }
            else
            {
                ans = "throws Exception";
                return ans;
            }
        }
        catch (Exception e)
        {
            ans = "throws Exception";
            return ans;
        }
        else if (rim)
            try
            {
                a = RomanArabicConverter.romanToArabic(aa);
                b = RomanArabicConverter.romanToArabic(bb);
                if (oper == '+')
                {
                    c = a+b;
                    ans = RomanArabicConverter.arabicToRoman(c);
                }
                else
                if (oper == '-')
                {
                    c = a-b;
                    ans = RomanArabicConverter.arabicToRoman(c);
                }
                else
                if (oper == '*')
                {
                    c = a*b;
                    ans = RomanArabicConverter.arabicToRoman(c);
                }
                else
                if (oper == '/')
                {
                    c = a/b;
                    ans = RomanArabicConverter.arabicToRoman(c);
                }
                else
                {
                    ans = "throws Exception";
                    return ans;
                }
            }
            catch (Exception e)
            {
                ans = "throws Exception";
                return ans;
            }
        return ans;
    }
    public static void main(String[] args) {
        String s, ans = "";
        Scanner in = new Scanner(System.in);
        s = in.nextLine();
        ans = calc (s);
        System.out.println(ans);
    }
}