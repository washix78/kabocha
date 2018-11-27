package jp.washix78.kabocha;

public class KabochaUtility {

  public static String trim(String src) {
    if (0 == src.length()) {
      return src;
    }

    int headI = 0;
    while (headI <= src.length() - 1 && (' ' == src.charAt(headI) || '　' == src.charAt(headI))) {
      headI += 1;
    }

    int tailI = src.length() - 1;
    while (headI < tailI && (' ' == src.charAt(tailI) || '　' == src.charAt(tailI))) {
      tailI -= 1;
    }

    return src.substring(headI, tailI + 1);
  }

  public static String getSpaceIndent(int length, int level) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length * level; i++) {
      sb.append(' ');
    }
    return sb.toString();
  }

}
