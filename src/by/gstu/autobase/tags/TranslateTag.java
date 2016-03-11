package by.gstu.autobase.tags;

import by.gstu.autobase.util.TranslateUtility;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Alexandr Kolymago on 07.12.2015.
 */
public class TranslateTag extends TagSupport {

    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(TranslateUtility.getInstance().getValue(key));
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }
}
