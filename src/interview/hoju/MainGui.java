package interview.hoju;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MainGui extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static final String _7_GUI초기화 = "7.GUI초기화";
	private static final String _6_Z와Q가같아져야함 = "6.Z와Q가같아져야함?[미구현]";
	private static final String _5_밤사이에T가Z로변함 = "5.밤사이에T가Z로변함.그후로밤낮변환";
	private static final String _4_만남 = "4.만남";
	private static final String _3_일단우선모두X이고Q로간주 = "3.일단우선모두X이고Q로간주";
	private static final String _2_당신은T다 = "2.당신은T다";
	private static final String _1_섬의_인원수입력 = "1. 섬의 인원수입력";

	private T_recognition t_recognition = new T_recognition();
	private JPanel north = new JPanel();
	private JPanel center = new JPanel();
	private JPanel south = new JPanel();
	private JTextField jtfNumPeople = new JTextField(10);
	private JTextArea[] jta = new JTextArea[6];
	private String[] groupName= {"h", "t", "a(b)", "z", "x", "q"};
	private Set<H_Base> human = new TreeSet<H_Base>();
	private static H_Base onlyOne=new H_Base("사람0");
	private boolean isDaytime=true;
	
	public MainGui() {
		jtfNumPeople.setText(40+"");
		//북쪽
		north.add(new JLabel("인원수 입력"));
		north.add(jtfNumPeople);
		JButton[] jButtons= new JButton[7];
		jButtons[0] = new JButton(_1_섬의_인원수입력);
		jButtons[1] = new JButton(_2_당신은T다);
		jButtons[2] = new JButton(_3_일단우선모두X이고Q로간주);
		jButtons[3] = new JButton(_4_만남);
		jButtons[4] = new JButton(_5_밤사이에T가Z로변함);
		jButtons[5] = new JButton(_6_Z와Q가같아져야함);
		jButtons[6] = new JButton(_7_GUI초기화);
		for (int i=0;i<jButtons.length;i++) {
			jButtons[i].addActionListener(this);
			if(i<=3) north.add(jButtons[i]);
		}
		add(north, BorderLayout.NORTH);
		
		//센터
		for (int i = 0; i < jta.length; i++) {
			jta[i] = new JTextArea(25, 10);
			JScrollPane scrollPane = new JScrollPane(jta[i], 
					JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds((i)*120+20, 20, 100, 500);
			JLabel label  = new JLabel(groupName[i]);
			label.setBounds((i)*120+30, 0, 100, 20);
			center.add(label);
			center.add(scrollPane);
		}
		center.setLayout(null);
		JScrollPane scrPane = new JScrollPane(center);
		add(scrPane, BorderLayout.CENTER); 
		
		//남쪽
		south.add(jButtons[4]);
		south.add(jButtons[5]);
		south.add(jButtons[6]);
		add(south, BorderLayout.SOUTH);
		
		
		//기본세팅
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200, 200, 800, 800);
		setTitle("ABC랜드");
	}

	public static void main(String[] args) {
		new MainGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case _1_섬의_인원수입력:
			init_addHuman();
			t_recognition.plusMe(onlyOne);
			initH();
			break;
		case _2_당신은T다 :
			initT();
			break;
		case _3_일단우선모두X이고Q로간주 :
			initXandQ();
			break;
		case _4_만남:
			for (H_Base humanOne : human) 
				t_recognition.randomMeet(humanOne);
			initXandQ();
			initAandZ();
			break;
		case _5_밤사이에T가Z로변함:
			if(isDaytime){
				t_recognition.comesNight(onlyOne);
				isDaytime=false;
			}else{
				t_recognition.comesDay(onlyOne);
				isDaytime=true;
			}
			initT();
			initAandZ();
			break;
		case _7_GUI초기화:
			for (JTextArea jtaOne : jta) {
				jtaOne.setText("");
			}
			break;
		default:
			break;
		}
		
		
	}

	public void init_addHuman() {
		t_recognition.clearAll();
		for (int i = 1; i <= parse(jtfNumPeople.getText()); i++) {
			H_Base h_Base = new H_Base("사람"+i);
			human.add(h_Base);
			
			t_recognition.plusX(h_Base);
			
		}
	}

	public void initT() {
		jta[1].setText("");
		jta[1].append("T인원");
		jta[1].append(t_recognition.showList(t_recognition.getT()));
	}

	public void initH() {
		jta[0].setText("");
		jta[0].append("h인원");
		jta[0].append(t_recognition.showList(t_recognition.getH()));
	}

	//===============================
	public void initAandZ() {
		jta[2].setText("");
		jta[3].setText("");
		jta[2].append("A인원");
		jta[2].append(t_recognition.showList(t_recognition.getA()));
		jta[3].append("Z인원");
		jta[3].append(t_recognition.showList(t_recognition.getZ()));
	}

	public void initXandQ() {
		jta[4].setText("");
		jta[5].setText("");
		jta[4].append("x인원");
		jta[4].append(t_recognition.showList(t_recognition.getX()));
		jta[5].append("q인원");
		jta[5].append(t_recognition.showList(t_recognition.getQ()));
	}
	private int parse(String string){
		return Integer.parseInt(string);
	}
}
