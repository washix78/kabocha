package jp.washix78.kabocha.parts;

import org.junit.Assert;

import org.junit.Test;

import jp.washix78.kabocha.parts.KabochaCommentPart;

public class KabochaCommentPartTest {

  @Test
  public void test() {
    KabochaCommentPart part = new KabochaCommentPart();
    part.add(null);
    part.add("");
    part.add("1");
    part.add("");
    part.add("2");
    part.add("");
    part.add("");
    part.add("3");
    part.add("4");
    part.add("");
    part.add("");

    StringBuffer sb = new StringBuffer();
    for (String line : part.getList(0, 0)) {
      sb.append(line);
    }
    String expected = "<p><span>１</span></p><p><span>２</span></p><p><span>３</span><br><span>４</span></p>";
    Assert.assertEquals(expected, sb.toString());

    StringBuffer sb2 = new StringBuffer();
    for (String line : part.getList(2, 1)) {
      sb2.append(line);
    }
    String expected2 = "  <p>    <span>１</span>  </p>  <p>    <span>２</span>  </p>  <p>    <span>３</span>    <br>    <span>４</span>  </p>";
    Assert.assertEquals(expected2, sb2.toString());
  }

}
