package jp.washix78.kabocha.parts;

import java.util.List;

public interface KabochaPart {

  public String getDirective();

  public void add(String src);

  public List<String> getList(int indentSize, int indentLevel);

}
