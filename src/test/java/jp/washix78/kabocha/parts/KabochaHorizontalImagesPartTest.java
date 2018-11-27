package jp.washix78.kabocha.parts;

import org.junit.Assert;
import org.junit.Test;

public class KabochaHorizontalImagesPartTest {

  @Test
  public void test() {
    KabochaHorizontalImagesPart part = new KabochaHorizontalImagesPart();
    part.add(null);
    part.add("");
    part.add("image-1.png");
    part.add("");
    part.add("/image-2.png");
    part.add("");

    StringBuffer sb = new StringBuffer();
    for (String line : part.getList(0, 0)) {
      sb.append(line);
    }

    String expected = "<img src=\"image-1.png\"><img src=\"/image-2.png\">";
    Assert.assertEquals(expected, sb.toString());

    StringBuffer sb2 = new StringBuffer();
    for (String line : part.getList(2, 1)) {
      sb2.append(line);
    }

    String expected2 = "  <img src=\"image-1.png\">  <img src=\"/image-2.png\">";
    Assert.assertEquals(expected2, sb2.toString());
  }

}
