package jp.washix78.kabocha;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KabochaMain {

  public static void main(String[] args) {
    try {
      if (args.length < 1) {
        throw new RuntimeException("Please specify a properties file path.");
      }
      Properties prop = getproperties(Paths.get(args[0]));

      Path webDirPath = Paths.get(prop.getProperty("web.directory.path"));
      if (!Files.isDirectory(webDirPath, LinkOption.NOFOLLOW_LINKS)) {
        throw new RuntimeException("Please specify or make a web directory.");
      }

      int indentSize = Integer.parseInt(prop.getProperty("indent.space.size"));
      String eol = prop.getProperty("eol.type").equals("crlf") ? "\r\n" : "\n";

      KabocharTemplateManager templateManager = new KabocharTemplateManager();

      List<Path> pagePathList = getPagePathList();
      for (Path pagePath : pagePathList) {
        KabochaSource source = new KabochaSource().load(pagePath);
        KabochaTemplate template = templateManager.getTemplate(source.getTemolateName());
        Path outputPath = webDirPath.resolve(source.getToPath());
        new KabochaHTMLBuilder(source, template, outputPath, indentSize, eol).build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Properties getproperties(Path filePath) throws Exception {
    try (InputStream in = Files.newInputStream(filePath);) {
      Properties prop = new Properties();
      prop.load(in);
      return prop;
    }
  }

  private static List<Path> getPagePathList() throws Exception {
    Path pageDirPath = Paths.get("./resources/sources");
    List<Path> pagePathList = new ArrayList<>();

    Files.walkFileTree(pageDirPath, new FileVisitor<Path>() {

      @Override
      public FileVisitResult preVisitDirectory(Path dirPath, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dirPath, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
        pagePathList.add(filePath);
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFileFailed(Path filePath, IOException e) throws IOException {
        return FileVisitResult.CONTINUE;
      }
    });

    System.out.println(pagePathList.size()); // TODO log
    return pagePathList;
  }

}
