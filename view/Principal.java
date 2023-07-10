package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Control;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;
	private JTextField jtxfCantidadInicial;
	private JTextField jtxfCantidadFinal;
	private JComboBox<String> jcmbDesde = new JComboBox<String>();
	private JComboBox<String> jcmbHacia = new JComboBox<String>();
	private final ButtonGroup jbtngrpConversores = new ButtonGroup();
	private JRadioButton jrbtnDivisas;
	private JRadioButton jrbtnTemperaturas;
	private JRadioButton jrbtnDistancias;
	private Control control = new Control();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/media/Logo.png")));
		setTitle("Alura Conversor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 442);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Elija la opción a usar");
		lblNewLabel.setBounds(10, 11, 195, 14);
		contentPane.add(lblNewLabel);

		jrbtnDivisas = new JRadioButton("Divisas");
		
		Principal principal = this;
		
		jrbtnDivisas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				control.inicializarDivisas(principal);
			}
		});
		jbtngrpConversores.add(jrbtnDivisas);
		jrbtnDivisas.setBounds(20, 37, 109, 23);
		contentPane.add(jrbtnDivisas);

		jrbtnTemperaturas = new JRadioButton("Temperaturas");
		jrbtnTemperaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				control.inicializarTemperaturas(principal);
			}
		});
		jbtngrpConversores.add(jrbtnTemperaturas);
		jrbtnTemperaturas.setBounds(141, 37, 109, 23);
		contentPane.add(jrbtnTemperaturas);

		jrbtnDistancias = new JRadioButton("Distancias");
		jrbtnDistancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				control.inicializarDistancias(principal);
			}
		});
		jbtngrpConversores.add(jrbtnDistancias);
		jrbtnDistancias.setBounds(281, 37, 96, 23);
		contentPane.add(jrbtnDistancias);

		JLabel lblNewLabel_1 = new JLabel("Elija la unidad inicial y final");
		lblNewLabel_1.setBounds(10, 95, 272, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Desde");
		lblNewLabel_2.setBounds(20, 141, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Hacia");
		lblNewLabel_2_1.setBounds(215, 141, 46, 14);
		contentPane.add(lblNewLabel_2_1);

		jcmbDesde.setBounds(64, 137, 115, 22);
		contentPane.add(jcmbDesde);

		jcmbHacia.setBounds(254, 137, 115, 22);
		contentPane.add(jcmbHacia);

		JLabel lblNewLabel_3 = new JLabel("Ingrese cantidad a convertir:");
		lblNewLabel_3.setBounds(45, 227, 195, 14);
		contentPane.add(lblNewLabel_3);

		jtxfCantidadInicial = new JTextField();
		validarContenido(jtxfCantidadInicial);
		jtxfCantidadInicial.setBounds(228, 219, 109, 28);
		contentPane.add(jtxfCantidadInicial);
		jtxfCantidadInicial.setColumns(10);

		JButton jbtnConvertir = new JButton("Convertir");
		jbtnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control control = new Control();
				if( control.validar(principal) ) {
					control.ejectutar(principal);
				}
			}
		});
		jbtnConvertir.setBounds(161, 269, 89, 23);
		contentPane.add(jbtnConvertir);

		jtxfCantidadFinal = new JTextField();
		jtxfCantidadFinal.setFocusable(false);
		jtxfCantidadFinal.setBounds(215, 311, 132, 28);
		contentPane.add(jtxfCantidadFinal);
		jtxfCantidadFinal.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("La cantidad final es:");
		lblNewLabel_4.setBounds(45, 319, 147, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton jbtnLimpiar = new JButton("Limpiar");
		jbtnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control control = new Control();
				control.limpiar(principal);
			}
		});
		jbtnLimpiar.setBounds(64, 361, 90, 28);
		contentPane.add(jbtnLimpiar);
		
		JButton jbtnCerrar = new JButton("Cerrar");
		jbtnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Control control = new Control();
				control.cerrarVentana(principal);
			}
		});
		jbtnCerrar.setBounds(254, 361, 90, 28);
		contentPane.add(jbtnCerrar);
	}

	private void validarContenido(JTextField jTextField) {
		
		jTextField.addKeyListener(new KeyAdapter() {
				
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) && c != '.') {
					e.consume();
				}
				if(c == '.' && jTextField.getText().contains(".")) {
					e.consume();
				}
			}
		});
	}

	public JComboBox<String> getJcmbDesde() {
		return jcmbDesde;
	}

	public void setJcmbDesde(JComboBox<String> jcmbDesde) {
		this.jcmbDesde = jcmbDesde;
	}

	public JComboBox<String> getJcmbHacia() {
		return jcmbHacia;
	}

	public void setJcmbHacia(JComboBox<String> jcmbHacia) {
		this.jcmbHacia = jcmbHacia;
	}

	public JTextField getJtxfCantidadInicial() {
		return jtxfCantidadInicial;
	}

	public void setJtxfCantidadInicial(JTextField jtxfCantidadInicial) {
		this.jtxfCantidadInicial = jtxfCantidadInicial;
	}

	public JTextField getJtxfCantidadFinal() {
		return jtxfCantidadFinal;
	}

	public void setJtxfCantidadFinal(JTextField jtxfCantidadFinal) {
		this.jtxfCantidadFinal = jtxfCantidadFinal;
	}

	public JRadioButton getJrbtnDivisas() {
		return jrbtnDivisas;
	}

	public JRadioButton getJrbtnTemperaturas() {
		return jrbtnTemperaturas;
	}

	public JRadioButton getJrbtnDistancias() {
		return jrbtnDistancias;
	}

	public ButtonGroup getJbtngrpConversores() {
		return jbtngrpConversores;
	}
	
	
}
