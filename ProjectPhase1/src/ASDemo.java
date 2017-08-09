import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ASDemo {


  public static void main(String[] args) throws ScriptException {
    String script = "tell application \"Kinoni Barcode Reader\" to launch";
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("AppleScript");
    engine.eval(script);
  }
}
