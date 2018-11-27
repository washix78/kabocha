package jp.washix78.kabocha.parts;

import java.util.ArrayList;
import java.util.List;

import jp.washix78.kabocha.KabochaUtility;

public class KabochaVerticalImagesPart implements KabochaPart {

  private List<String> srcList = new ArrayList<>();

  @Override
  public String getDirective() {
    return "IMAGES_VERTICAL";
  }

  @Override
  public void add(String src) {
    if (src != null && src.length() != 0) {
      srcList.add(src);
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
      String line = new StringBuffer().append(indent).append("<img src=\"").append(srcList.get(i)).append("\">")
          .toString();
      list.add(line);
    }
    return list;
  }

}
