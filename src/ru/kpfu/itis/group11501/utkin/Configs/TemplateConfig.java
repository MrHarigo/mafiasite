package ru.kpfu.itis.group11501.utkin.Configs;
/**
 * Created by user on 02.10.2016.
 */
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;

public class TemplateConfig {
    private static Configuration cfg = null;
    public static Configuration getConfig(ServletContext sc) {
        if (cfg == null){
            cfg = new Configuration();
            cfg.setServletContextForTemplateLoading(
                    sc,
                    "/WEB-INF/templates"
            );
            cfg.setTemplateExceptionHandler(
                    TemplateExceptionHandler.HTML_DEBUG_HANDLER
            );
        }
        return cfg;
    }
}
