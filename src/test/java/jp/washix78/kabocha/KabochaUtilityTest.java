package jp.washix78.kabocha;

import org.junit.Assert;
import org.junit.Test;

public class KabochaUtilityTest {

  @Test
  public void testTrim() {
    Assert.assertEquals("", KabochaUtility.trim(""));
    Assert.assertEquals("", KabochaUtility.trim(" "));
    Assert.assertEquals("", KabochaUtility.trim("　"));
    Assert.assertEquals("", KabochaUtility.trim(" 　"));
    Assert.assertEquals("A", KabochaUtility.trim("A"));
    Assert.assertEquals("A", KabochaUtility.trim(" A"));
    Assert.assertEquals("A", KabochaUtility.trim("A "));
    Assert.assertEquals("A", KabochaUtility.trim("　A"));
    Assert.assertEquals("A", KabochaUtility.trim("A　"));
    Assert.assertEquals("A", KabochaUtility.trim(" 　A 　"));
    Assert.assertEquals("A 　A", KabochaUtility.trim("A 　A"));
  }

  @Test
  public void testGetIndent() {
    Assert.assertEquals("", KabochaUtility.getSpaceIndent(0, 0));
    Assert.assertEquals("", KabochaUtility.getSpaceIndent(1, 0));
    Assert.assertEquals(" ", KabochaUtility.getSpaceIndent(1, 1));
    Assert.assertEquals("  ", KabochaUtility.getSpaceIndent(1, 2));
    Assert.assertEquals("", KabochaUtility.getSpaceIndent(2, 0));
    Assert.assertEquals("  ", KabochaUtility.getSpaceIndent(2, 1));
    Assert.assertEquals("    ", KabochaUtility.getSpaceIndent(2, 2));
  }

}
