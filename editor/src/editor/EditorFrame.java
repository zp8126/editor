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
		setTitle("文本编辑器");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200, 100, 400, 500);

		editor = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(editor);
		add(scrollPane, BorderLayout.CENTER);

		infoLbl = new JLabel("总字数：0", JLabel.LEFT);
		add(infoLbl, BorderLayout.SOUTH);

		// 菜单
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("文件");
		JMenuItem newItem = new JMenuItem("新建");
		JMenuItem openItem = new JMenuItem("打开");
		JMenuItem saveItem = new JMenuItem("保存");
		JMenuItem saveAsItem = new JMenuItem("另存为");

		bar.add(menu);
		menu.add(newItem);
		menu.add(openItem);
		menu.add(saveItem);
		menu.add(saveAsItem);

		// 设置
		JMenu settings = new JMenu("设置");
		JMenu bgColor = new JMenu("背景色");
		JMenuItem yellow = new JMenuItem("黄色");
		JMenuItem green = new JMenuItem("绿色");
		JMenuItem red = new JMenuItem("红色");
		red.setBackground(Color.RED);
		green.setBackground(Color.GREEN);
		yellow.setBackground(Color.YELLOW);

		bar.add(settings);
		settings.add(bgColor);
		bgColor.add(yellow);
		bgColor.add(red);
		bgColor.add(green);

		// 关于
		JMenu about = new JMenu("关于");
		JMenuItem item = new JMenuItem("技术支持");
		bar.add(about);
		about.add(item);

		//设置窗体菜单栏
		setJMenuBar(bar);

		// 添加监听事件
		newItem.addActionListener(this);
		openItem.addActionListener(this);
		saveItem.addActionListener(this);
		saveAsItem.addActionListener(this);
		item.addActionListener(this);
		
		//设置菜单中条目
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
			case "打开":
				open();
				break;
			case "新建":
				newFile();
				break;
			case "保存":
				saveFile();
				break;
			case "另存为":
				saveAsFile();
				break;
			case "技术支持":
				JOptionPane.showMessageDialog(this, "文都智链@zhp.com");
				break;
			case "红色":
				editor.setBackground(Color.RED);
				break;
			case "绿色":
				editor.setBackground(Color.GREEN);
				break;
			case "黄色":
				editor.setBackground(Color.YELLOW);
				break;
			default:
				break;
			}

		}
	}

	/**
	 * 保存文件 注意：这里区分新建文件和打开文件 如果 为新建文件内容，保存时，需要弹出文件选择器 如果为打开文件内容，则直接保存到原有文件即可
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
	 * 新建
	 */
	private void newFile() {
		editor.setText("");
		saveFile = null;
	}

	/**
	 * 点击打开
	 */
	private void open() {
		File file = openFile();
		if (file != null) {
			// 记忆当前内容为打开内容，不是新建内容
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
	 * 文件选择器
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
