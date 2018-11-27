package jp.washix78.kabocha.parts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.text.Transliterator;

import jp.washix78.kabocha.KabochaUtility;

public class KabochaTitlePart implements KabochaPart {

  private List<String> srcList = new ArrayList<>();

  @Override
  public String getDirective() {
    return "TITLE";
  }

  @Override
  public void add(String src) {
    if (src != null && 0 < src.length()) {
      srcList.add(Transliterator.getInstance("Halfwidth-Fullwidth").transliterate(src));
    }
  }

  @Override
  public List<String> getList(int indentSize, int indentLevel) {
    String indent = KabochaUtility.getSpaceIndent(indentSize, indentLevel);
    String br = new StringBuffer().append(indent).append("<br>").toString();

    List<String> list = new ArrayList<>();
    for (int i = 0; i < srcList.size(); i++) {
      if (0 < i) {
        list.add(br);
      }
      String line = new StringBuffer().append(indent).append("<span>").append(srcList.get(i)).append("</span>")
          .toString();
      list.add(line);
    }
    return list;
  }

}
