package jp.washix78.kabocha.parts;

import org.junit.Assert;
import org.junit.Test;

import jp.washix78.kabocha.parts.KabochaTitlePart;

public class KabochaTitlePartTest {

  @Test
  public void test() {
    KabochaTitlePart part = new KabochaTitlePart();
    part.add(null);
    part.add("");
    part.add("1");
    part.add("2");
    part.add("");
    StringBuffer sb = new StringBuffer();
    for (String line : part.getList(0, 0)) {
      sb.append(line);
    }
    String expect = "<span>１</span><br><span>２</span>";
    Assert.assertEquals(expect, sb.toString());

    StringBuffer sb2 = new StringBuffer();
    for (String line : part.getList(2, 1)) {
      sb2.append(line);
    }
    String expect2 = "  <span>１</span>  <br>  <span>２</span>";
    Assert.assertEquals(expect2, sb2.toString());
  }

}
