package jp.washix78.kabocha.parts;

import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.text.Transliterator;

import jp.washix78.kabocha.KabochaUtility;

public class KabochaCommentPart implements KabochaPart {

  private List<List<String>> paragraphList = new ArrayList<>();

  @Override
  public String getDirective() {
    return "COMMENT";
  }

  @Override
  public void add(String src) {
    if (paragraphList.size() == 0) {
      paragraphList.add(new ArrayList<>());
    }
    List<String> paragraph = paragraphList.get(paragraphList.size() - 1);
    if (src == null || src.length() == 0) {
      if (paragraph.size() != 0) {
        paragraphList.add(new ArrayList<>());
      }
    } else {
      paragraph.add(Transliterator.getInstance("Halfwidth-Fullwidth").transliterate(src));
    }
  }

  @Override
  public List<String> getList(int indentSize, int indentLevel) {
    String indent = KabochaUtility.getSpaceIndent(indentSize, indentLevel);
    String openP = new StringBuffer().append(indent).append("<p>").toString();
    String closeP = new StringBuffer().append(indent).append("</p>").toString();
    String nextIndent = KabochaUtility.getSpaceIndent(indentSize, indentLevel + 1);
    String br = new StringBuffer().append(nextIndent).append("<br>").toString();

    List<String> list = new ArrayList<>();
    for (List<String> paragraph : paragraphList) {

      if (paragraph.size() != 0) {
        list.add(openP);

        for (int i = 0; i < paragraph.size(); i++) {
          if (0 < i) {
            list.add(br);
          }

          String spanLine = new StringBuffer().append(nextIndent).append("<span>").append(paragraph.get(i))
              .append("</span>").toString();
          list.add(spanLine);
        }

        list.add(closeP);
      }
    }
    return list;
  }

}
