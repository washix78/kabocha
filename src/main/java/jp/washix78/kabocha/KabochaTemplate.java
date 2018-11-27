package jp.washix78.kabocha;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class KabochaTemplate {

  private Path path;
  private List<String> lineList;

  public KabochaTemplate(Path path) {
    this.path = path;
  }
  
  public KabochaTemplate load() throws IOException {
    try (
      BufferedReader in = Files.newBufferedReader(path);
    ) {
      lineList = new ArrayList<>();
      String line = null;
      while ((line = in.readLine()) != null) {
        lineList.add(line);
      }
      return this;
    }
  }
  
  public List<String> get() {
    List<String> list = new ArrayList<>();
    for (String line : lineList) {
      list.add(new String(line));
    }
    return list;
  }

}
