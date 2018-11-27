package jp.washix78.kabocha;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jp.washix78.kabocha.parts.KabochaCommentPart;
import jp.washix78.kabocha.parts.KabochaHorizontalImagesPart;
import jp.washix78.kabocha.parts.KabochaPart;
import jp.washix78.kabocha.parts.KabochaTitlePart;
import jp.washix78.kabocha.parts.KabochaVerticalImagesPart;

public class KabochaSource {

  private Path toPath;
  private String templateName;
  private List<KabochaPart> partList = new ArrayList<>();

  public KabochaSource load(Path srcTextPath) throws Exception {
    // TODO log src text path
    try (BufferedReader in = Files.newBufferedReader(srcTextPath);) {
      load(in, null);
      return this;
    }
  }

  public Path getToPath() {
    return toPath;
  }

  public String getTemolateName() {
    return templateName;
  }

  public List<KabochaPart> getPartList() {
    return partList;
  }

  private void load(BufferedReader in, String directive) throws Exception {
    String line = null;
    List<String> lineList = new ArrayList<>();
    while ((line = in.readLine()) != null && !line.startsWith(":")) {
      lineList.add(line.trim());
    }

    if ("template".equals(directive)) {

      templateName = lineList.stream().filter(str -> {
        return 0 < str.length();
      }).findFirst().orElse(null);

    } else if ("to".equals(directive)) {

      String to = lineList.stream().filter(str -> {
        return 0 < str.length();
      }).findFirst().orElse(null);
      toPath = Paths.get(to);

    } else if (directive != null) {

      KabochaPart part = "title".equals(directive) ? new KabochaTitlePart()
          : ("images".equals(directive) || "images-horizontal".equals(directive)) ? new KabochaHorizontalImagesPart()
              : "images-vertical".equals(directive) ? new KabochaVerticalImagesPart()
                  : "comment".equals(directive) ? new KabochaCommentPart() : null;
      if (part == null) {
        throw new RuntimeException();
      }
      partList.add(part);
      for (String src : lineList) {
        part.add(src);
      }

    }

    if (line != null && line.startsWith(":")) {
      String nextDirective = line.replaceFirst(": *", "").replaceFirst(" +", "");
      load(in, nextDirective);
    }
  }

}
