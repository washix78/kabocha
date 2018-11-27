package jp.washix78.kabocha;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;

import jp.washix78.kabocha.parts.KabochaPart;

public class KabochaHTMLBuilder {

  private KabochaSource source;
  private KabochaTemplate template;
  private Path outputPath;
  private int indentSize;
  private String eol;

  public KabochaHTMLBuilder(KabochaSource source, KabochaTemplate template, Path outputPath, int indentSize,
      String eol) {
    this.source = source;
    this.template = template;
    this.outputPath = outputPath;
    this.indentSize = indentSize;
    this.eol = eol;
  }

  public KabochaHTMLBuilder build() throws IOException {
    try (BufferedWriter out = Files.newBufferedWriter(outputPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING);) {

      Iterator<KabochaPart> partIterator = source.getPartList().iterator();
      KabochaPart part = partIterator.hasNext() ? partIterator.next() : null;

      List<String> lineList = template.get();
      for (int i = 0; i < lineList.size(); i++) {
        String line = lineList.get(i);

        if (part != null && line.contains(part.getDirective())) {
          int indentLevel = getIndentLevel(line);
          String[] tags = getTags(line, part.getDirective(), KabochaUtility.getSpaceIndent(indentSize, indentLevel));
          out.write(tags[0]);
          out.write(eol);

          for (String partLine : part.getList(indentSize, indentLevel + 1)) {
            out.write(partLine);
            out.write(eol);
          }

          out.write(tags[1]);
          out.write(eol);
          part = partIterator.hasNext() ? partIterator.next() : null;
        } else {
          out.write(line);
          out.write(eol);
        }
      }

      out.flush();
      return this;
    }
  }

  private int getIndentLevel(String line) {
    int i = 0;
    while (line.charAt(i) == ' ') {
      i += 1;
    }
    return i / indentSize;
  }

  private String[] getTags(String line, String directive, String indent) {
    String[] tags = new String[2];
    int headI = line.indexOf(directive);
    int tailI = headI + directive.length();

    tags[0] = line.substring(0, headI);
    tags[1] = indent + line.substring(tailI);
    return tags;
  }

}
