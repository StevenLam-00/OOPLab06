package lab6;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField textProductName;
	private Product product;
	private List<Product> productList;
	private storeData storeData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search frame = new Search();
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
	public Search() {
		storeData = new storeData();
        productList = storeData.read();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearch.setBounds(156, 24, 101, 25);
		contentPane.add(lblSearch);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(27, 56, 147, 34);
		contentPane.add(lblNewLabel);
		
		textProductName = new JTextField();
		textProductName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textProductName.setBounds(194, 59, 200, 28);
		contentPane.add(textProductName);
		textProductName.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblAmount.setBounds(27, 118, 101, 25);
		contentPane.add(lblAmount);
		
		JLabel lblAmountNum = new JLabel();
		lblAmountNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAmountNum.setBounds(194, 118, 200, 23);
		contentPane.add(lblAmountNum);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescription.setBounds(27, 172, 121, 25);
		contentPane.add(lblDescription);
		
		JLabel lblDescriptionText = new JLabel();
		lblDescriptionText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDescriptionText.setBounds(194, 172, 200, 23);
		contentPane.add(lblDescriptionText);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 product =null;
				
			        int size = productList.size();
			        for (int i = 0; i < size; i++) {
			            if (productList.get(i).getProductName().equalsIgnoreCase(textProductName.getText())==true) {
			                product = productList.get(i);
			                lblAmountNum.setText(String.valueOf(product.getAmount()));
			                lblDescriptionText.setText(product.getDescription());
//			                JOptionPane.showMessageDialog(null,"Product Name: "+ product.getProductName()+
//			                		", Amount: "+product.getAmount()+", Description: "+product.getDescription()
//			                		);
			               
			                
			                break;
			            }
			        }
			        if (product != null) {
			            
			        } else {
			            JOptionPane.showMessageDialog(null, "Not found the Product");
			        }
					
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSearch.setBounds(445, 56, 133, 32);
		contentPane.add(btnSearch);
	}
}
