package jp.washix78.kabocha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class KabochaHTMLBuilderTest {

  @Test
  public void test() throws IOException, Exception {
    KabochaSource source = new KabochaSource().load(Paths.get("src/test/resources/source.txt"));
    KabochaTemplate template = new KabochaTemplate(Paths.get("src/test/resources/template.html")).load();
    Path outputPath = Paths.get("src/test/resources/test.html");
    int indentSize = 2;
    String eol = "\n";
    new KabochaHTMLBuilder(source, template, outputPath, indentSize, eol).build();

    byte[] expected = Files.readAllBytes(Paths.get("src/test/resources/expected.html"));
    byte[] actual = Files.readAllBytes(outputPath);
    Assert.assertArrayEquals(expected, actual);
  }

  @Test
  public void testSample() throws IOException, Exception {
    KabochaSource source = new KabochaSource().load(Paths.get("resources/sources/sample.txt"));
    KabochaTemplate template = new KabochaTemplate(Paths.get("resources/templates/sample.html")).load();
    Path outputPath = Paths.get("target.html");
    int indentSize = 2;
    String eol = "\n";
    new KabochaHTMLBuilder(source, template, outputPath, indentSize, eol).build();
  }

}
