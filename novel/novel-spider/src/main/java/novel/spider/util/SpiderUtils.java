package novel.spider.util;

import org.apache.commons.lang3.StringUtils;

import javax.script.*;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangxiaodan on 2018/8/1.
 */
public class SpiderUtils {

    /**
     * 调用groovy脚本的方法并返回结果
     *
     * @param script     脚本
     * @param methodName 要调用的脚本方法名称
     * @param params     脚本方法需要的参数
     * @return
     */
    public static Object engineEval(String script, Map<String, Object> bindVars, String methodName, Object... params) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = groovyEngine(script, bindVars);
        Object result = ((Invocable) engine).invokeFunction(methodName, params);
        return result;
    }

    /**
     * 根据正则URL获取真正要采集的URL
     *
     * @param urlPattern
     * @param params
     * @return
     */
    public static String realTargetUrl(String urlPattern, String... params) {
        if (StringUtils.isBlank(urlPattern)) {
            return null;
        }
        return String.format(urlPattern, params);
    }

    /**
     * 根据编码名称获取编码对象
     * @param charsetName
     * @return
     */
    public static Charset charsetForName(String charsetName) {
        Charset charset = null;
        try {
            charset = Charset.forName(charsetName);
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        }
        return charset;
    }

    /**
     * 获取执行引擎
     *
     * @param script
     * @param bindVars
     * @return
     * @throws ScriptException
     */
    public static ScriptEngine groovyEngine(String script, Map<String, Object> bindVars) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        ScriptContext scriptContext = engine.getContext();
        Bindings bindings = scriptContext.getBindings(ScriptContext.ENGINE_SCOPE);
        if (bindVars != null && bindVars.size() > 0) {
            for (Map.Entry<String, Object> entry : bindVars.entrySet()) {
                bindings.put(entry.getKey(), entry.getValue());
            }
        }
        engine.eval(script, bindings);
        return engine;
    }

    /**
     * 生成随机uuid
     * @return
     */
    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
