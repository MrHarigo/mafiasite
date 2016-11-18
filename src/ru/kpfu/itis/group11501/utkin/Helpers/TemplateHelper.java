package ru.kpfu.itis.group11501.utkin.Helpers;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.kpfu.itis.group11501.utkin.Configs.TemplateConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by user on 12.10.2016.
 */
public class TemplateHelper {

    public static void render(HttpServletRequest req,HttpServletResponse resp, String template, Map root) throws IOException {
        Template tmpl = TemplateConfig.getConfig(
                req.getServletContext()
        ).getTemplate(template);
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void render(HttpServletRequest req,HttpServletResponse resp, String template) throws IOException {
        Template tmpl = TemplateConfig.getConfig(
                req.getServletContext()
        ).getTemplate(template);
        try {
            tmpl.process(null, resp.getWriter());
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}
