package editor;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
/**
 * 	文档监听器
 * 	实时监听文档统计信息
 *
 */
public class EditorStatListener implements DocumentListener {
	private JLabel infoLbl;

	public EditorStatListener(JLabel infoLbl) {
		this.infoLbl = infoLbl;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// 属性集改变时
		System.out.println("===changedUpdate==");
	}

	/**
	 * 	文档增加字符是执行这里
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		info(e);

	}

	/**
	 * 	文档在删除字符时，执行这里
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		info(e);
	}

	/**
	 * 	实时显示文档内容字符数和总行数
	 */
	private void info(DocumentEvent e) {
		try {
			int len = e.getDocument().getLength();
			String content = e.getDocument().getText(0, len);
			infoLbl.setText("总字数：" + len + "  \t 总行数：" + getLineCount(content));

		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 *	 根据换行符统计字符串行数
	 * @param str
	 * @return
	 */
	private int getLineCount(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '\n') {
				count++;
			}
		}
		return count + 1;
	}
}
