package editor;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
/**
 * 	�ĵ�������
 * 	ʵʱ�����ĵ�ͳ����Ϣ
 *
 */
public class EditorStatListener implements DocumentListener {
	private JLabel infoLbl;

	public EditorStatListener(JLabel infoLbl) {
		this.infoLbl = infoLbl;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// ���Լ��ı�ʱ
		System.out.println("===changedUpdate==");
	}

	/**
	 * 	�ĵ������ַ���ִ������
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		info(e);

	}

	/**
	 * 	�ĵ���ɾ���ַ�ʱ��ִ������
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		info(e);
	}

	/**
	 * 	ʵʱ��ʾ�ĵ������ַ�����������
	 */
	private void info(DocumentEvent e) {
		try {
			int len = e.getDocument().getLength();
			String content = e.getDocument().getText(0, len);
			infoLbl.setText("��������" + len + "  \t ��������" + getLineCount(content));

		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 *	 ���ݻ��з�ͳ���ַ�������
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
