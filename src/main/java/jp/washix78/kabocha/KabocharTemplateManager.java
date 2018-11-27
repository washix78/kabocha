package jp.washix78.kabocha;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class KabocharTemplateManager {

  private Map<String, KabochaTemplate> templateMap = new HashMap<>();

  public KabochaTemplate getTemplate(String key) throws IOException {
    KabochaTemplate template = templateMap.get(key);
    if (template == null) {
      return load(key);
    } else {
      return template;
    }
  }

  private KabochaTemplate load(String name) throws IOException {
    Path path = Paths.get("./resources/template", name);
    if (!Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
      throw new RuntimeException();
    }

    KabochaTemplate template = new KabochaTemplate(path).load();
    templateMap.put(name, template);
    return template;
  }

}
