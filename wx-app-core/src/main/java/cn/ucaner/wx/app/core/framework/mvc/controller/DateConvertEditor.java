/**
 *<html>
 *<body>
 *	<P> Copyright  ● JasonInternational </p>
 *  <p> All rights reserved.</p>
 *  <p> Created by Jason see https://github.com/Jasonandy/springboot-wx </p>
 *</body>
 *</html>
 */
package cn.ucaner.wx.app.core.framework.mvc.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.util.StringUtils;

/**
* @Package：cn.ucaner.wx.app.core.framework.mvc.controller   
* @ClassName：DateConvertEditor   
* @Description：   <p> DateConvertEditor</p>
* @Author： - Jason   
* @CreatTime：2018年10月23日 下午7:52:49   
* @Modify By：   
* @ModifyTime：  2018年10月23日
* @Modify marker：   
* @version    V1.0
 */
public class DateConvertEditor extends PropertyEditorSupport {
	
	private SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			try {
				if (text.indexOf(":") == -1 && text.length() == 10) {
					setValue(this.dateFormat.parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 19) {
					setValue(this.datetimeFormat.parse(text));
				} else if (text.indexOf(":") > 0 && text.length() == 21) {
					text = text.replace(".0", "");
					setValue(this.datetimeFormat.parse(text));
				} else {
					throw new IllegalArgumentException(
							"Could not parse date, date format is error ");
				}
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		} else {
			setValue(null);
		}
	}
}
