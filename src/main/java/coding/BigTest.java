package coding;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.math.BigInteger;
import java.util.LinkedList;

public class BigTest {

    static final long LONG_MASK = 0xffffffffL;

    public static String multiply(String num1, String num2) {
        if(num1 == "0" || num2 == "0") return String.valueOf(0);
        int len1 = num1.length(),len2 = num2.length();
        int[] a = new int[len1];
        int[] b = new int[len2];
        for(int i = 0;i < len1;i++) a[i] = Integer.parseInt(num1.substring(i,i+1));
        for(int i = 0;i < len2;i++) b[i] = Integer.parseInt(num2.substring(i,i+1));
        int[] res = new int[len1+len2];
        res = implMultiplyToLen(a,len1,b,len2,null);
        res = trustedStripLeadingZeroInts(res);

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < res.length;i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    private static  int[] implMultiplyToLen(int[] x, int xlen, int[] y, int ylen, int[] z) {
        int xstart = xlen - 1;
        int ystart = ylen - 1;

        if (z == null || z.length < (xlen+ ylen))
            z = new int[xlen+ylen];

        long carry = 0;
        for (int j=ystart, k=ystart+1+xstart; j >= 0; j--, k--) {
            long product = (y[j] & LONG_MASK) *
                    (x[xstart] & LONG_MASK) + carry;
            z[k] = (int)product;
            carry = product >>> 32;
        }
        z[xstart] = (int)carry;

        for (int i = xstart-1; i >= 0; i--) {
            carry = 0;
            for (int j=ystart, k=ystart+1+i; j >= 0; j--, k--) {
                long product = (y[j] & LONG_MASK) *
                        (x[i] & LONG_MASK) +
                        (z[k] & LONG_MASK) + carry;
                z[k] = (int)product;
                carry = product >>> 32;
            }
            z[i] = (int)carry;
        }
        return z;
    }

    private static int[] trustedStripLeadingZeroInts(int val[]) {
        int vlen = val.length;
        int keep;

        // Find first nonzero byte
        for (keep = 0; keep < vlen && val[keep] == 0; keep++)
            ;
        return keep == 0 ? val : java.util.Arrays.copyOfRange(val, keep, vlen);
    }

    public static String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList();
        int len = path.length();

        int i = 0;
        while(i < len - 1) {
            int j = i + 1;
            while(j < len && path.charAt(j) != '/') j++;
            String str = path.substring(i,j);
            if("/..".equals(str)) {
                if(stack.size() > 0) stack.removeLast();
            }else if(!"/".equals(str) && !"/.".equals(str)) {
                stack.add(str);
            }
            i = j;
        }
        if(stack.size() == 0) stack.add("/");
        StringBuilder sb = new StringBuilder();
        while(stack.size() > 0) {
            sb.append(stack.removeFirst());
        }
        return sb.toString();
    }

    public static String simplifyPathV2(String path) {
       path =  path.replaceAll("/+","/");

        return path;
    }


    public static void main(String[] args) {
       System.out.println(simplifyPathV2("/a//b////c/d//././/.."));
    }
}
