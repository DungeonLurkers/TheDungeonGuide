package tk.avabin.tdg;


import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by avabi on 13.03.2017.
 */
public class Util {
    public static String prettyStringFromObject(Object o) {
        final int linePadding = 4;
        final String fieldColName = "Field name";
        final String valueColName = "Field Value";
        StringBuilder sb = new StringBuilder();
        int maxLineLength = 0;
        int maxFieldNameLength = fieldColName.length();
        int maxFieldValueLength = valueColName.length();

        sb.append(System.lineSeparator());
        try {
            for (PropertyDescriptor pd :
                    Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors()) {

                if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                    String name = pd.getName();
                    Object v = pd.getReadMethod().invoke(o);
                    if (name.length() > maxFieldNameLength) maxFieldNameLength = name.length();
                    if (String.valueOf(v).length() > maxFieldValueLength)
                        maxFieldValueLength = String.valueOf(v).length();
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        maxFieldNameLength += linePadding;
        maxFieldValueLength += linePadding;
        maxLineLength = maxFieldNameLength + maxFieldValueLength;
        printCellBorder(sb, maxLineLength, true);
        printCell(sb, fieldColName, maxFieldNameLength, false);
        printCell(sb, valueColName, maxFieldValueLength, true);
        printCellBorder(sb, maxFieldNameLength, false);
        printCellBorder(sb, maxFieldValueLength, true);
        try {
            for (PropertyDescriptor pd :
                    Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors()) {

                if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                    String name = pd.getName();
                    Object v = pd.getReadMethod().invoke(o);
                    printCell(sb, name, maxFieldNameLength, false);
                    printCell(sb, String.valueOf(v), maxFieldValueLength, true);
                    printCellBorder(sb, maxFieldNameLength, false);
                    printCellBorder(sb, maxFieldValueLength, true);
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private static void printCell(StringBuilder sb, String content, int size, boolean printEndOfRow) {
        sb.append("|");
        size -= 1;
        if (printEndOfRow) size -= 1;
        sb.append(padRight(content, size));
        if (printEndOfRow) {
            sb.append("|")
                    .append(System.lineSeparator());
        }
    }

    private static void printCellBorder(StringBuilder sb, int sizeOfCell, boolean printEndOfRow) {
        sb.append("+");
        if (printEndOfRow) sizeOfCell -= 1;
        for (int i = 0; i < sizeOfCell - 1; i++) {
            sb.append("-");
        }
        if (printEndOfRow) sb.append("+").append(System.lineSeparator());
    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

}
