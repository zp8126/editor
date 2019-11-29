package editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
  
public class EditorFrame extends JFrame implements ActionListener {
	private JTextArea editor;
	private JLabel infoLbl;
	private File saveFile;

	public EditorFrame() {
		setTitle("�ı��༭��");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200, 100, 400, 500);

		editor = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(editor);
		add(scrollPane, BorderLayout.CENTER);

		infoLbl = new JLabel("��������0", JLabel.LEFT);
		add(infoLbl, BorderLayout.SOUTH);

		// �˵�
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("�ļ�");
		JMenuItem newItem = new JMenuItem("�½�");
		JMenuItem openItem = new JMenuItem("��");
		JMenuItem saveItem = new JMenuItem("����");
		JMenuItem saveAsItem = new JMenuItem("���Ϊ");

		bar.add(menu);
		menu.add(newItem);
		menu.add(openItem);
		menu.add(saveItem);
		menu.add(saveAsItem);

		// ����
		JMenu settings = new JMenu("����");
		JMenu bgColor = new JMenu("����ɫ");
		JMenuItem yellow = new JMenuItem("��ɫ");
		JMenuItem green = new JMenuItem("��ɫ");
		JMenuItem red = new JMenuItem("��ɫ");
		red.setBackground(Color.RED);
		green.setBackground(Color.GREEN);
		yellow.setBackground(Color.YELLOW);

		bar.add(settings);
		settings.add(bgColor);
		bgColor.add(yellow);
		bgColor.add(red);
		bgColor.add(green);

		// ����
		JMenu about = new JMenu("����");
		JMenuItem item = new JMenuItem("����֧��");
		bar.add(about);
		about.add(item);

		//���ô���˵���
		setJMenuBar(bar);

		// ��Ӽ����¼�
		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveAsItem.addActionListener(this);
		item.addActionListener(this);
		
		//���ò˵�����Ŀ
		yellow.addActionListener(this);
		green.addActionListener(this);
		red.addActionListener(this);

		editor.getDocument().addDocumentListener(new EditorStatListener(infoLbl));

		setVisible(true);
	}

	public static void main(String[] args) {
		new EditorFrame();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem item = (JMenuItem) e.getSource();
			String itemTxt = item.getText();
			switch (itemTxt) {
			case "��":
				open();
				break;
			case "�½�":
				newFile();
				break;
			case "����":
				saveFile();
				break;
			case "���Ϊ":
				saveAsFile();
				break;
			case "����֧��":
				JOptionPane.showMessageDialog(this, "�Ķ�����@zhp.com");
				break;
			case "��ɫ":
				editor.setBackground(Color.RED);
				break;
			case "��ɫ":
				editor.setBackground(Color.GREEN);
				break;
			case "��ɫ":
				editor.setBackground(Color.YELLOW);
				break;
			default:
				break;
			}

		}
	}

	/**
	 * �����ļ� ע�⣺���������½��ļ��ʹ��ļ� ��� Ϊ�½��ļ����ݣ�����ʱ����Ҫ�����ļ�ѡ���� ���Ϊ���ļ����ݣ���ֱ�ӱ��浽ԭ���ļ�����
	 */
	public void saveFile() {
		if (saveFile == null) {
			JFileChooser jc = new JFileChooser();

			int op = jc.showSaveDialog(this);
			if (op == JFileChooser.APPROVE_OPTION) {
				IOUtils.write(jc.getSelectedFile(), editor.getText());
			}
		} else {
			IOUtils.write(saveFile, editor.getText());
		}
	}

	public void saveAsFile() {
		JFileChooser jc = new JFileChooser();
		int op = jc.showSaveDialog(this);
		if (op == JFileChooser.APPROVE_OPTION) {
			IOUtils.write(jc.getSelectedFile(), editor.getText());
		}
	}

	/**
	 * �½�
	 */
	private void newFile() {
		editor.setText("");
		saveFile = null;
	}

	/**
	 * �����
	 */
	private void open() {
		File file = openFile();
		if (file != null) {
			// ���䵱ǰ����Ϊ�����ݣ������½�����
			saveFile = file;
			try {
				String content = IOUtils.read(file);
				editor.setText(content);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * �ļ�ѡ����
	 */
	private File openFile() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int op = fc.showOpenDialog(this);
		if (op == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		}
		return null;
	}

}
